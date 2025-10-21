package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Genero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface GeneroJPA : JpaRepository<Genero,Long>{


    @Query("SELECT g FROM Genero g WHERE g.nombre = :nombre")
    fun findIdByGenero(@Param("nombre")nombre: String): Optional<Genero>

}