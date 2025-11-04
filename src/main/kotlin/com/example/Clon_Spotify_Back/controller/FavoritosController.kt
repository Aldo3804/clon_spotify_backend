package com.example.Clon_Spotify_Back.controller

import com.example.Clon_Spotify_Back.service.FavoritoService
import com.example.Clon_Spotify_Back.service.PerfilService
import com.example.Clon_Spotify_Back.service.UsuarioService
import com.example.Clon_Spotify_Back.wrappers.request.FavoritoRequest
import com.example.Clon_Spotify_Back.wrappers.response.FavoritoResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/favorito")
class FavoritosController (

    private val favoritoService: FavoritoService,
    private val perfilService: PerfilService

){

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @PutMapping("/agregar")
    fun agregarFavorito(@RequestBody favoritoRequest: FavoritoRequest, authentication: Authentication) : ResponseEntity<Map<String,String>> {
        favoritoService.agregarFavorito(favoritoRequest, authentication)
        return ResponseEntity.ok(mapOf("mensaje" to "Canción agregada a la lista de favoritos"))
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @DeleteMapping("/eliminar")
    fun eliminarFavorito(@RequestBody favoritoRequest: FavoritoRequest, authentication: Authentication) : ResponseEntity<Map<String,String>> {
        favoritoService.eliminarFavorito(favoritoRequest, authentication)
        return ResponseEntity.ok(mapOf("mensaje" to "Canción eliminada de la lista de favoritos"))
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @GetMapping("/listar")
    fun listarFavoritos(authentication: Authentication): ResponseEntity<List<FavoritoResponse>> {
        val lista: List<FavoritoResponse> = favoritoService.listarFavorito(authentication)
        return ResponseEntity.status(HttpStatus.OK).body(lista)
    }
}