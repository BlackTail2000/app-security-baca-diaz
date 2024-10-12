Drop Database If Exists BD_Baca_Diaz_T2;
Create Database If Not Exists BD_Baca_Diaz_T2;
Use BD_Baca_Diaz_T2;

Create Table Usuario(
id Int Primary Key Auto_Increment,
username Varchar(100) Not Null,
password Varchar(60) Not Null,
email Varchar(150) Not Null,
rol Varchar(11) Not Null,
activo Boolean Not Null);

Create Table Proyecto(
id_proyecto Int Primary Key Auto_Increment,
titulo_proyecto Varchar(100) Not Null,
descripcion LongText Not Null,
activo Boolean Not Null);

Insert Into Usuario (username, password, email, rol, activo) Values 
('BlackTail2000', '$2a$10$Zlsvk4zFQkCvKcn7H11SWOq1zO3j0zbOIVbOF5ckIoehBrV4aRSzO',
'carlosgabrielbacamanrique@hotmail.com', 'GESTOR', true),
('Frutilupy22', '$2a$10$aNvb6yRuFJf4LRYuDUCWt.L0BuxETtoSRksnx8lieA1KbE0qG1Eo6',
'anilucdl22@gmail.com', 'COORDINADOR', true);

Select * From Usuario;
Select * From Proyecto;