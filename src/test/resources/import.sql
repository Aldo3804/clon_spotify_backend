-- 1. INSERTAR DATOS MAESTROS (Sin dependencias)
-- Usamos 'usuario' (minúsculas) para que coincida con tu DTO de prueba.
INSERT INTO rol (id_rol, nombre) VALUES (1, 'usuario');
INSERT INTO rol (id_rol, nombre) VALUES (2, 'administrador');
INSERT INTO genero (id_genero, nombre) VALUES (1, 'Rock');
INSERT INTO genero (id_genero, nombre) VALUES (2, 'Pop');
INSERT INTO autor (id, nombre) VALUES (1, 'Queen');
INSERT INTO autor (id, nombre) VALUES (2, 'Michael Jackson');

-- 2. INSERTAR DATOS QUE DEPENDEN DE LOS MAESTROS
-- (Asegúrate de que la contraseña hasheada sea correcta si tu test de login la usa)
INSERT INTO usuario (id_usuario, nombre, apellidos, telefono, correo, usuario, contrasenia, id_rol) VALUES (1, 'Usuario', 'DePrueba', '999888777', 'test@user.com', 'testuser', '$2a$10$yqI.3.B4.W1z/n.V.vNl9eS0.E.1.p.C3.t.T.T.W.G.I.E.w.O', 1);
INSERT INTO cancion (id_cancion, nombre, duracion, url, id_genero) VALUES (1, 'Bohemian Rhapsody', 355, 'http://example.com/queen.mp3', 1);
INSERT INTO cancion (id_cancion, nombre, duracion, url, id_genero) VALUES (2, 'Billie Jean', 294, 'http://example.com/mj.mp3', 2);

-- 3. INSERTAR DATOS DE UNIÓN (Dependen de los anteriores)
INSERT INTO lista (id_lista, id_cancion, id_autor) VALUES (1, 1, 1);
INSERT INTO lista (id_lista, id_cancion, id_autor) VALUES (2, 2, 2);
INSERT INTO favorito (id_favorito, id_usuario, id_cancion) VALUES (1, 1, 1);