package com.example.Clon_Spotify_Back.mappers

import com.example.Clon_Spotify_Back.wrappers.request.FavoritoRequest
import com.example.Clon_Spotify_Back.entity.Favorito
import com.example.Clon_Spotify_Back.projection.FavoritoVistaProjection
import com.example.Clon_Spotify_Back.repository.CancionJPA
import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import com.example.Clon_Spotify_Back.wrappers.response.FavoritoResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component


@Component
class MapearFavorito(

    private val cancionJPA: CancionJPA,
    private val usuarioJPA: UsuarioJPA

) {


    fun toEntity(favoritoRequest: FavoritoRequest,authentication: Authentication) : Favorito{

        val cancion = cancionJPA.findById(favoritoRequest.idCancion)
            .orElseThrow {
                IllegalArgumentException("Canción con ID ${favoritoRequest.idCancion} no encontrada")
            }

        val usuario = usuarioJPA.findByUsuario(authentication.name)

        return Favorito(
            idFavorito = 0,
            idCancion = cancion,
            idUsuario = usuario
        )

    }

    fun toDTOVista(projection: FavoritoVistaProjection) : FavoritoResponse{

        return FavoritoResponse(
            idFavorito = projection.getIdFavorito(),
            idUsuario = projection.getIdUsuario(),
            idCancion = projection.getIdCancion(),
            nombre = projection.getNombre() ?: "Nombre Desconocido",
            duracion = projection.getDuracion() ?: 0L,
            url = projection.getUrl() ?: "",
            genero = projection.getGenero() ?: "Género Desconocido",
            autor = projection.getAutor() ?: "Autor Desconocido"
        )

    }

}