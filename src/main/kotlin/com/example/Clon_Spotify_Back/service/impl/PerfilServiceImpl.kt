package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.mappers.MapearUsuario
import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import com.example.Clon_Spotify_Back.service.PerfilService
import com.example.Clon_Spotify_Back.wrappers.request.ContraseniaRequest
import com.example.Clon_Spotify_Back.wrappers.response.UsuarioResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class PerfilServiceImpl(

    private val usuarioJPA: UsuarioJPA,
    private val passwordEncoder: PasswordEncoder,
    private val mapearUsuario: MapearUsuario
) : PerfilService{


    override fun cambiarContrasenia(
        usuario: String,
        request: ContraseniaRequest
    ) {

        val usuarioObtenido = usuarioJPA.findByUsuario(usuario)
                                ?: throw RuntimeException("No se encontro el usuario")

        if (!passwordEncoder.matches(request.antigua, usuarioObtenido.contrasenia)) {
            throw RuntimeException("La contraseña antigua es incorrecta")
        }

        val nuevoHash = passwordEncoder.encode(request.nueva)

        usuarioObtenido.contrasenia = nuevoHash
        usuarioJPA.save(usuarioObtenido)

    }

    override fun obtenerUsuario(usuario: String): UsuarioResponse {
        val usuarioObtenido = usuarioJPA.findByUsuario(usuario)
            ?: throw RuntimeException("No se encontro el usuario")
        return mapearUsuario.toResponse(usuarioObtenido)
    }


}