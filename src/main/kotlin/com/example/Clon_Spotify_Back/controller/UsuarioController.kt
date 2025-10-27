package com.example.Clon_Spotify_Back.controller

import com.example.Clon_Spotify_Back.dto.LoginDTO
import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.service.UsuarioService
import com.example.Clon_Spotify_Back.wrappers.request.RefreshRequest
import com.example.Clon_Spotify_Back.wrappers.response.LoginResponse
import com.example.Clon_Spotify_Back.wrappers.response.RefreshResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService


    @PostMapping("/registrar")
    fun registrar(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        val user = usuarioService.registrarUsuario(usuarioDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }


    @PutMapping("/login")
    fun iniciarSesion(@RequestBody loginDTO: LoginDTO): ResponseEntity<LoginResponse>{
        val token: LoginResponse = usuarioService.iniciarSesion(loginDTO)
        return ResponseEntity.ok(token)
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping("/refresh")
    fun refrescarToken(@RequestBody refreshRequest: RefreshRequest): ResponseEntity<RefreshResponse> {
        val response = usuarioService.refreshToken(refreshRequest)
        return ResponseEntity.ok(response)
    }



}