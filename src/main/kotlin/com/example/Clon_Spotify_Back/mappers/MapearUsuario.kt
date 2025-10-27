package com.example.Clon_Spotify_Back.mappers


import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Rol
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.repository.RolJPA
import com.example.Clon_Spotify_Back.wrappers.response.UsuarioResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class MapearUsuario {

    @Autowired
    private lateinit var rolJPA: RolJPA

    @Lazy
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder


    fun toDTO(usuario: Usuario) : UsuarioDTO{

        return UsuarioDTO(
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
            .orElseThrow{ RuntimeException("No se encontro el rol ${usuarioDTO.rol.lowercase()}") }

        val contraseniaHash = passwordEncoder.encode(usuarioDTO.contrasenia)

        return Usuario(
            nombre=usuarioDTO.nombre,
            apellidos=usuarioDTO.apellidos,
            telefono=usuarioDTO.telefono,
            correo = usuarioDTO.correo,
            usuario = usuarioDTO.usuario,
            contrasenia = contraseniaHash,
            idRol=rol
        )


    }

    fun toResponse(usuario: Usuario): UsuarioResponse{
        return UsuarioResponse(
            nombre = usuario.nombre,
            apellidos = usuario.apellidos,
            telefono = usuario.telefono,
            correo = usuario.correo,
            usuario = usuario.usuario,
            rol = usuario.idRol.nombre
        )
    }

}