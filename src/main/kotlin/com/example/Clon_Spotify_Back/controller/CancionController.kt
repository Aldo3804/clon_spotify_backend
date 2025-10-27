package com.example.Clon_Spotify_Back.controller

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.entity.Cancion
import com.example.Clon_Spotify_Back.entity.Genero
import com.example.Clon_Spotify_Back.service.CancionService
import com.example.Clon_Spotify_Back.service.GeneroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/canciones")
class CancionController {

    @Autowired
    private lateinit var cancionService: CancionService

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @GetMapping("/listar")
    fun listar(): ResponseEntity<List<CancionDTO>> {
        val lista: List<CancionDTO> = cancionService.listar()
        return ResponseEntity.status(HttpStatus.CREATED).body(lista)
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/agregar")
    fun agregar(cancionDTO: CancionDTO): ResponseEntity<Unit> {
        val cancion = cancionService.agregarCancion(cancionDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(cancion)
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @GetMapping("/aleatorio")
    fun aleatorio(): ResponseEntity<CancionDTO> {
        val cancionRandom = cancionService.randomCancion()
        return if (cancionRandom != null) {
            ResponseEntity.ok(cancionRandom)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @GetMapping("id/{idCancion}")
    fun buscarPorId(@PathVariable idCancion:Long) : ResponseEntity<CancionDTO>{
        val cancion = cancionService.buscarPorId(idCancion)
        return if (cancion!= null) {
            ResponseEntity.ok(cancion)
        } else {
            ResponseEntity.noContent().build()
        }

    }


}