package com.example.Clon_Spotify_Back.service

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.wrappers.request.FavoritoRequest
import com.example.Clon_Spotify_Back.wrappers.response.FavoritoResponse
import org.springframework.security.core.Authentication

interface FavoritoService {

    fun agregarFavorito(favoritoRequest: FavoritoRequest,authentication: Authentication)

    fun eliminarFavorito(favoritoRequest: FavoritoRequest,authentication: Authentication)

    fun listarFavorito(authentication: Authentication): List<FavoritoResponse>
}