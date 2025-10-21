package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Autor
import com.example.Clon_Spotify_Back.entity.Lista
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ListaJPA: JpaRepository<Lista,Long> {

    @Query("SELECT l.autor FROM Lista l WHERE l.cancion.idCancion = :idCancion")
    fun findAutorByCancionId(@Param("idCancion") idCancion: Long): Optional<Autor>

}