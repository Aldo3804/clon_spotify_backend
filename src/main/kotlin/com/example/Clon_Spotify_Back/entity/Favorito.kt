package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "favorito")
data class Favorito(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFavorito: Long,


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    val idUsuario: Usuario?,

    @ManyToOne
    @JoinColumn(name = "id_cancion")
    val idCancion: Cancion


)
