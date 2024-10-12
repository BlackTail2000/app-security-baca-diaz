package pe.edu.cibertec.app_security_baca_diaz.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthFilter extends OncePerRequestFilter {

    @Value("${custom.clave}")
    private String clave;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (validarUsoDeToken(request)) {
                Claims claims = validarToken(request);
                if(claims.get("rol") != null) {
                    crearAuthToken(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void crearAuthToken(Claims claims) {
        List<String> authList = (List<String>) claims.get("rol");
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(),
                null, authList.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private Claims validarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization")
                .replace("Bearer ", "");
        return Jwts.parser().setSigningKey(clave.getBytes()).parseClaimsJws(token).getBody();
    }

    private boolean validarUsoDeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }
}
