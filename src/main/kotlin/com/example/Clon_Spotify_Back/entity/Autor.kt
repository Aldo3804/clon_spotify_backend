package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "autor")
data class Autor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    val nombre:String
)
