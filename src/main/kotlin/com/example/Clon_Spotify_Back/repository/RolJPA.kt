package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Rol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface RolJPA: JpaRepository<Rol,Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    fun findByNombre(@Param("nombre") nombre: String): Optional<Rol>


}