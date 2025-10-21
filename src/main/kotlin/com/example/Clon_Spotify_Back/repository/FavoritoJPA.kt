package com.example.Clon_Spotify_Back.repository

import com.example.Clon_Spotify_Back.entity.Favorito
import org.springframework.data.jpa.repository.JpaRepository

interface FavoritoJPA: JpaRepository<Favorito,Long> {
}