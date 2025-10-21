package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.*


@Entity
@Table(name = "genero")
data class Genero(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idGenero:Long,

    val nombre:String

)
