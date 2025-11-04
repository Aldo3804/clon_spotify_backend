package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Favorito
import com.example.Clon_Spotify_Back.projection.FavoritoVistaProjection
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FavoritoJPA: JpaRepository<Favorito,Long> {

    @Query(
        value = "SELECT id_favorito AS id_favorito,id_usuario,id_cancion,nombre_cancion, duracion, url, genero, autor FROM vista_favoritos where id_usuario=:idUsuario",
        nativeQuery = true
    )
    fun findAllByIdUsuario(@Param("idUsuario")idUsuario:Long) : List<FavoritoVistaProjection>

    @Query("SELECT f FROM Favorito f WHERE f.idUsuario.idUsuario = :idUsuario AND f.idCancion.idCancion = :idCancion")
    fun findByUsuarioAndCancion(
        @Param("idUsuario") idUsuario: Long,
        @Param("idCancion") idCancion: Long
    ): Favorito?
}