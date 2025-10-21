package com.example.Clon_Spotify_Back.service

import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Usuario

interface UsuarioService {

    fun registrarUsuario(usuarioDTO: UsuarioDTO): UsuarioDTO

    fun iniciarSesion(usuario:String,contrasenia:String) : UsuarioDTO

}