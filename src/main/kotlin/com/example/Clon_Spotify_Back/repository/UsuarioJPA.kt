package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Rol
import com.example.Clon_Spotify_Back.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UsuarioJPA: JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario=:usuario and u.contrasenia=:contrasenia")
    fun findByUserAndPass(@Param("usuario") usuario: String,@Param("contrasenia") contrasenia: String): Usuario

}