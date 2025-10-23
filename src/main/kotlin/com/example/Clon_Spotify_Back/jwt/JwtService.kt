package com.example.Clon_Spotify_Back.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date


@Service
class JwtService(

    @Value("\${jwt.variable}")
    private val jwtClave: String,

    @Value("\${jwt.expiration}")
    private val jwtExpiracion: Long

) {

    fun generarToken(usuario:String): String {

        val tiempo = Date()

        val expiracion = Date(tiempo.time + jwtExpiracion)

        val llave = Keys.hmacShaKeyFor(jwtClave.toByteArray())

        return Jwts.builder()
            .subject(usuario)
            .expiration(expiracion)
            .signWith(llave, Jwts.SIG.HS256)
            .compact()
    }


}