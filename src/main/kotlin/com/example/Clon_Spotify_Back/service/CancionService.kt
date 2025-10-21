package com.example.Clon_Spotify_Back.service

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.entity.Cancion

interface CancionService {

    fun listar() : List<CancionDTO>

    fun agregarCancion(cancionDTO:CancionDTO)

    fun randomCancion(): CancionDTO?

    fun buscarPorId(idCancion:Long) : CancionDTO?
}
