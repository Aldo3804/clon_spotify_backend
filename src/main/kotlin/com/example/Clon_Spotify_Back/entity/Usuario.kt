package com.example.Clon_Spotify_Back.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idUsuario:Long = 0,

    val nombre:String,

    val apellidos:String,

    val telefono:String,

    val correo:String,

    val usuario: String,

    val contrasenia:String,

    @OneToOne
    @JoinColumn(name = "id_rol")
    val idRol:Rol

)
