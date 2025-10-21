package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import com.example.Clon_Spotify_Back.service.UsuarioService
import com.example.Clon_Spotify_Back.wrapper.MapearUsuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl: UsuarioService {

    @Autowired
    private lateinit var usuarioJPA: UsuarioJPA

    @Autowired
    private lateinit var mapearUsuario: MapearUsuario


    override fun registrarUsuario(usuarioDTO: UsuarioDTO): UsuarioDTO {
        val usuario: Usuario = mapearUsuario.toEntity(usuarioDTO)

        val nuevoUsuario = mapearUsuario.toDTO(usuarioJPA.save(usuario))

        return nuevoUsuario
    }

    override fun iniciarSesion(usuario: String, contrasenia: String): UsuarioDTO {
        val usuario:Usuario = usuarioJPA.findByUserAndPass(usuario,contrasenia)
        return mapearUsuario.toDTO(usuario)
    }


}