package com.example.Clon_Spotify_Back.service

import com.example.Clon_Spotify_Back.dto.LoginDTO
import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.wrappers.request.RefreshRequest
import com.example.Clon_Spotify_Back.wrappers.response.LoginResponse
import com.example.Clon_Spotify_Back.wrappers.response.RefreshResponse

interface UsuarioService {

    fun registrarUsuario(usuarioDTO: UsuarioDTO): UsuarioDTO

    fun iniciarSesion(loginDTO: LoginDTO) : LoginResponse

    fun refreshToken(refreshRequest: RefreshRequest) : RefreshResponse

}