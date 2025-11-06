package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.dto.LoginDTO
import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.jwt.JwtService
import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import com.example.Clon_Spotify_Back.service.UsuarioService
import com.example.Clon_Spotify_Back.mappers.MapearUsuario
import com.example.Clon_Spotify_Back.utils.EmailService
import com.example.Clon_Spotify_Back.wrappers.request.RefreshRequest
import com.example.Clon_Spotify_Back.wrappers.response.LoginResponse
import com.example.Clon_Spotify_Back.wrappers.response.RefreshResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class UsuarioServiceImpl(

    private val usuarioJPA: UsuarioJPA,
    private val mapearUsuario: MapearUsuario,
    private val jwtService: JwtService,
    private val emailService: EmailService,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService
): UsuarioService {


    override fun registrarUsuario(usuarioDTO: UsuarioDTO): UsuarioDTO {
        val usuario: Usuario = mapearUsuario.toEntity(usuarioDTO)

        emailService.enviar(usuario.correo,"Gracias por registrarte","""
            Gracias por registrarte en mi aplicacion, espero la 
            pruebes y me comentes tu experiencia enviandome un email a este 
            mismo correo. 
            
            Usuario : ${usuario.usuario}
            
            Contraseña : ${usuario.contrasenia}
            
            Saludos!!!
        """.trimIndent())

        val nuevoUsuario = mapearUsuario.toDTO(usuarioJPA.save(usuario))

        return nuevoUsuario
    }

    override fun iniciarSesion(loginDTO: LoginDTO): LoginResponse {

        try{
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginDTO.usuario,loginDTO.contrasenia)
            )
            val accessToken = jwtService.generarAccessToken(loginDTO.usuario)
            val refreshToken = jwtService.generarRefreshToken(loginDTO.usuario)

            return LoginResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }catch (e: AuthenticationException){
            throw RuntimeException("Usuario o contraseña incorrectos $e")
        }

    }

    override fun refreshToken(refreshRequest: RefreshRequest): RefreshResponse {
        val refreshToken = refreshRequest.refreshToken

        try {

            val tipoToken = jwtService.obtenerClaim(refreshToken,"token_type")

            if (tipoToken != "REFRESH") {
                throw RuntimeException("Token inválido: Se esperaba un Refresh Token.")
            }

            val usuario = jwtService.obtenerUsuarioPorToken(refreshToken)
            val userDetails = userDetailsService.loadUserByUsername(usuario)

            if(jwtService.tokenValido(refreshToken,userDetails)){
                val nuevo = jwtService.generarAccessToken(usuario)
                return RefreshResponse(accessToken = nuevo)
            }else{
                throw RuntimeException("Token expirado")
            }
        }catch (e: Exception){
            throw RuntimeException("Refresh Token inválido: ${e.message}")
        }

    }


}