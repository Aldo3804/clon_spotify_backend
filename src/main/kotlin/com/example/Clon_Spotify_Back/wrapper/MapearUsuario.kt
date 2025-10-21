package com.example.Clon_Spotify_Back.wrapper

import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Rol
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.repository.RolJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class MapearUsuario {

    @Autowired
    private lateinit var rolJPA: RolJPA


    fun toDTO(usuario: Usuario) : UsuarioDTO{

        return UsuarioDTO(
            idUsuario =usuario.idUsuario,
            nombre=usuario.nombre,
            apellidos=usuario.apellidos,
            telefono=usuario.telefono,
            correo = usuario.correo,
            usuario = usuario.usuario,
            contrasenia = usuario.contrasenia,
            rol=usuario.idRol.nombre
        )

    }

    fun toEntity(usuarioDTO: UsuarioDTO):Usuario{

        val rol:Rol = rolJPA.findByNombre(usuarioDTO.rol)
            .orElseThrow{ RuntimeException("No se encontro el rol ${usuarioDTO.rol}") }


        return Usuario(
            idUsuario =usuarioDTO.idUsuario,
            nombre=usuarioDTO.nombre,
            apellidos=usuarioDTO.apellidos,
            telefono=usuarioDTO.telefono,
            correo = usuarioDTO.correo,
            usuario = usuarioDTO.usuario,
            contrasenia = usuarioDTO.contrasenia,
            idRol=rol
        )


    }

}