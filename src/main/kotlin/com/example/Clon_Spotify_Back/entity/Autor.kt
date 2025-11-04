package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


/**
 *
 * Clase para definir el autor de diversas canciones
 *
 */
@Entity
@Table(name = "autor")
data class Autor(

    /**
     * Identificador único para el autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    /**
     * Nombre del autor o grupo
     */
    val nombre:String
)
