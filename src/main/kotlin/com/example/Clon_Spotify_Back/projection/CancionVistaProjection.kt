package com.example.Clon_Spotify_Back.projection

interface CancionVistaProjection {

    fun getIdCancion(): Long
    fun getNombre(): String
    fun getDuracion(): Long
    fun getUrl(): String
    fun getGenero(): String
    fun getAutor(): String

}