package com.example.Clon_Spotify_Back.controller

import com.example.Clon_Spotify_Back.service.PerfilService
import com.example.Clon_Spotify_Back.wrappers.request.ContraseniaRequest
import com.example.Clon_Spotify_Back.wrappers.request.RefreshRequest
import com.example.Clon_Spotify_Back.wrappers.response.RefreshResponse
import com.example.Clon_Spotify_Back.wrappers.response.UsuarioResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/perfil")
class PerfilController(
    private val perfilService: PerfilService
) {

    @PutMapping("/contrasenia")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    fun cambiarContrasenia(authentication: Authentication,
                           @RequestBody request : ContraseniaRequest): ResponseEntity<Map<String,String>>{

        val usuario = authentication.name

        perfilService.cambiarContrasenia(usuario,request)

        return ResponseEntity.ok(mapOf("mensaje" to "Contraseña actualizada exitosamente"))
    }


    @GetMapping("/obtener")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    fun obtenerPerfil(authentication: Authentication) : ResponseEntity<UsuarioResponse>{

        val usuario = authentication.name

        val perfil = perfilService.obtenerUsuario(usuario)

        return ResponseEntity.ok(perfil)

    }
}