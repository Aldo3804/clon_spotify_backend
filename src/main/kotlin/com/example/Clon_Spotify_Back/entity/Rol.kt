package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "rol")
data class Rol(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_rol:Long,

    val nombre:String

)
