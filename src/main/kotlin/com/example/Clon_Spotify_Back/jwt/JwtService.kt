package com.example.Clon_Spotify_Back.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey


@Service
class JwtService(

    @Value("\${jwt.variable}")
    private val jwtClave: String,

    @Value("\${jwt.expiration.access}")
    private val jwtExpiracionAccess: Long,

    @Value("\${jwt.expiration.refresh}")
    private val jwtExpirationRefresh:Long,


) {

    private fun llaveSecreta() : SecretKey{
        return Keys.hmacShaKeyFor(jwtClave.toByteArray())
    }

    private fun generarToken(usuario:String
                             ,expiracion:Long,tipo:String): String {

        val tiempo = Date()

        val expiracion = Date(tiempo.time + expiracion)

        return Jwts.builder()
            .subject(usuario)
            .issuedAt(tiempo)
            .expiration(expiracion)
            .claim("token_type", tipo)
            .signWith(llaveSecreta(), Jwts.SIG.HS256)
            .compact()
    }

    fun generarAccessToken(usuario:String) : String{
        return generarToken(usuario,jwtExpiracionAccess,"ACCESS")
    }

    fun generarRefreshToken(usuario: String) : String{
        return generarToken(usuario,jwtExpirationRefresh,"REFRESH")
    }
 
    private fun obtenerClaims(token:String) : Claims{
        return Jwts.parser()
            .verifyWith(llaveSecreta())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun obtenerClaim(token: String, nombre: String): String? {
        return obtenerClaims(token).get(nombre, String::class.java)
    }

    fun obtenerUsuarioPorToken(token: String) : String{
        return obtenerClaims(token).subject
    }

    fun obtenerExpiracion(token:String) : Date{
         return obtenerClaims(token).expiration
    }

    private fun isTokenExpired(token:String) : Boolean{
        return obtenerExpiracion(token).before(Date())
    }

    fun tokenValido(token: String, userDetails: UserDetails) : Boolean{
        val usuario = obtenerUsuarioPorToken(token)
        return (usuario == userDetails.username && !isTokenExpired(token))
    }

}