package com.example.Clon_Spotify_Back.projection

interface FavoritoVistaProjection {

    fun getIdFavorito() : Long
    fun getIdUsuario(): Long
    fun getIdCancion(): Long
    fun getNombre(): String?
    fun getDuracion(): Long? // Es buena idea hacer la duración nulable también
    fun getUrl(): String?
    fun getGenero(): String?
    fun getAutor(): String?

}