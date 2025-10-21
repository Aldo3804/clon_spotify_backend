package com.example.Clon_Spotify_Back.dto


data class CancionDTO(

    val idCancion:Long,

    val nombre: String,

    val duracion:Long,

    val url:String,

    val genero:String,

    val autor:String
)
