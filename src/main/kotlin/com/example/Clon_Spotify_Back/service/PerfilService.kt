package com.example.Clon_Spotify_Back.service

import com.example.Clon_Spotify_Back.wrappers.request.ContraseniaRequest
import com.example.Clon_Spotify_Back.wrappers.response.UsuarioResponse
import org.springframework.security.core.Authentication

interface PerfilService {

    fun cambiarContrasenia(usuario:String,request: ContraseniaRequest)

    fun obtenerUsuario(usuario:String) : UsuarioResponse

}