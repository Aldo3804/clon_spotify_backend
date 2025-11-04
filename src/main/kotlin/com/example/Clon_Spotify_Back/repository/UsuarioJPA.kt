package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Rol
import com.example.Clon_Spotify_Back.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UsuarioJPA: JpaRepository<Usuario, Long> {

    @Query("SELECT u.idUsuario FROM Usuario u WHERE u.usuario = :usuario")
    fun findIdByUsuario(@Param("usuario") usuario: String) : Long
    fun findByUsuario(usuario: String): Usuario?
}