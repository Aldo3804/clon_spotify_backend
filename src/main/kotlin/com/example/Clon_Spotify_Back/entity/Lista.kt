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
@Table(name = "lista")
data class Lista(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idLista : Long,

    @ManyToOne
    @JoinColumn(name = "id_cancion")
    val cancion: Cancion,

    @ManyToOne
    @JoinColumn(name = "id_autor")
    val autor:Autor

)
