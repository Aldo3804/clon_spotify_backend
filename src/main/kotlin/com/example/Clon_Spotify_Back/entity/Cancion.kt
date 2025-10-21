package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "cancion")
data class Cancion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCancion:Long,

    val nombre: String,

    val duracion:Long,

    val url:String,

    @ManyToOne
    @JoinColumn(name ="id_genero")
    val idGenero:Genero
)
