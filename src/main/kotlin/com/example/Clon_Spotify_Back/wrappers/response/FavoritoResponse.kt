package com.example.Clon_Spotify_Back.wrappers.response

data class FavoritoResponse(

    val idFavorito:Long,

    val idUsuario:Long,

    val idCancion:Long,

    val nombre: String,

    val duracion:Long,

    val url:String,

    val genero:String,

    val autor:String

)
