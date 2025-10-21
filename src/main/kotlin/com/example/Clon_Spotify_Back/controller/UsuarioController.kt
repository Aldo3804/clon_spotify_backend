package com.example.Clon_Spotify_Back.controller

import com.example.Clon_Spotify_Back.dto.LoginDTO
import com.example.Clon_Spotify_Back.dto.UsuarioDTO
import com.example.Clon_Spotify_Back.entity.Usuario
import com.example.Clon_Spotify_Back.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService


    @PostMapping("/registrar")
    fun registrar(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        usuarioService.registrarUsuario(usuarioDTO)
        val usuarioLogueado = usuarioService.iniciarSesion(usuarioDTO.usuario, usuarioDTO.contrasenia)

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioLogueado)
    }


    @PutMapping("/sesion")
    fun iniciarSesion(@RequestBody loginDTO: LoginDTO): ResponseEntity<UsuarioDTO>{
        val usuarioDTO: UsuarioDTO = usuarioService.iniciarSesion(loginDTO.usuario,loginDTO.contrasenia)
        return ResponseEntity.ok(usuarioDTO)
    }




}