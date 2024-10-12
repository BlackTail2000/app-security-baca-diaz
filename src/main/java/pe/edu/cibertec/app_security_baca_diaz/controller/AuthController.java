package pe.edu.cibertec.app_security_baca_diaz.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.app_security_baca_diaz.dto.UsuarioReponseDto;
import pe.edu.cibertec.app_security_baca_diaz.dto.UsuarioRequestDto;
import pe.edu.cibertec.app_security_baca_diaz.model.Usuario;
import pe.edu.cibertec.app_security_baca_diaz.service.IUsuarioService;
import pe.edu.cibertec.app_security_baca_diaz.service.impl.DetalleUsuarioService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final DetalleUsuarioService detalleUsuarioService;
    private final AuthenticationManager authenticationManager;
    private final IUsuarioService usuarioService;
    @Value("${custom.clave}")
    private String clave;

    @PostMapping("/login")
    public ResponseEntity<UsuarioReponseDto> userAuthentication(
            @RequestBody UsuarioRequestDto usuarioRequestDto
            ) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuarioRequestDto.getUsername(), usuarioRequestDto.getPassword()));
            if(authentication.isAuthenticated()) {
                Usuario authUsuario = usuarioService.obtenerPorUsername(usuarioRequestDto.getUsername());
                String token = generarToken(authUsuario);
                return new ResponseEntity<>(
                        UsuarioReponseDto.builder()
                                .idusuario(authUsuario.getId())
                                .username(authUsuario.getUsername())
                                .email(authUsuario.getEmail())
                                .token(token)
                                .build(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generarToken(Usuario usuario) {
        List<GrantedAuthority> authorityList = detalleUsuarioService.obtenerRolUsuario(usuario.getRol());
        return Jwts.builder()
                .setId(usuario.getId().toString())
                .setSubject(usuario.getUsername())
                .claim("email", usuario.getEmail())
                .claim("rol",
                        authorityList.stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 180000))
                .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                .compact();
    }
}
