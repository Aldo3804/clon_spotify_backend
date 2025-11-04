package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.wrappers.request.FavoritoRequest
import com.example.Clon_Spotify_Back.mappers.MapearCancion
import com.example.Clon_Spotify_Back.mappers.MapearFavorito
import com.example.Clon_Spotify_Back.repository.CancionJPA
import com.example.Clon_Spotify_Back.repository.FavoritoJPA
import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import com.example.Clon_Spotify_Back.service.FavoritoService
import com.example.Clon_Spotify_Back.wrappers.response.FavoritoResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service


@Service
class FavoritoServiceImpl(

    private val cancionJPA: CancionJPA,
    private val usuarioJPA: UsuarioJPA,
    private val favoritoJPA: FavoritoJPA,
    private val mapearCancion: MapearCancion,
    private val mapearFavorito: MapearFavorito

): FavoritoService {

    override fun agregarFavorito(favoritoRequest: FavoritoRequest, authentication: Authentication) {
        val idUsuario = usuarioJPA.findIdByUsuario(authentication.name)

        val existe = favoritoJPA.findByUsuarioAndCancion(idUsuario, favoritoRequest.idCancion)

        if (existe == null) {

            val favorito = mapearFavorito.toEntity(favoritoRequest, authentication)
            favoritoJPA.save(favorito)
        } else {
            throw IllegalStateException("Esta canción ya está en favoritos")
        }
    }

    override fun eliminarFavorito(favoritoRequest: FavoritoRequest, authentication: Authentication) {
        val idUsuario = usuarioJPA.findIdByUsuario(authentication.name)

        val favoritoExistente = favoritoJPA.findByUsuarioAndCancion(idUsuario, favoritoRequest.idCancion)
            ?: throw IllegalArgumentException("Esa canción no está en tus favoritos") // Error si no lo encuentra

        favoritoJPA.delete(favoritoExistente)
    }

    override fun listarFavorito(authentication: Authentication): List<FavoritoResponse> {
        val idUsuario = usuarioJPA.findIdByUsuario(authentication.name)
        return favoritoJPA.findAllByIdUsuario(idUsuario)
            .map { mapearFavorito.toDTOVista(it) }
    }
}