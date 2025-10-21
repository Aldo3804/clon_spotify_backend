package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.entity.Cancion
import com.example.Clon_Spotify_Back.projection.CancionVistaProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CancionJPA: JpaRepository<Cancion, Long> {

    @Query(
        value = "SELECT id AS idCancion, nombre, duracion, url, genero, autor FROM vista_canciones",
        nativeQuery = true
    )
    fun findAllFromVista(): List<CancionVistaProjection>

    @Query(
        value = "SELECT id AS idCancion, nombre, duracion, url, genero, autor FROM vista_canciones ORDER BY RANDOM() LIMIT 1",
        nativeQuery = true
    )
    fun findRandomCancion(): CancionVistaProjection?

    @Query(
        value = "SELECT ID AS idCancion,nombre , duracion, url, genero, autor FROM vista_canciones where id=:idCancion",
        nativeQuery = true
    )
    fun findCancionById(@Param("idCancion") idCancion:Long) : CancionVistaProjection?

}
