package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.repository.UsuarioJPA
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws


@Service
class UserDetailsServiceImpl(
    private val usuarioJPA: UsuarioJPA
): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val usuario = usuarioJPA.findByUsuario(username)
            ?: throw UsernameNotFoundException("Usuario '$username' no encontrado")

        val authorities = listOf<GrantedAuthority>(
            SimpleGrantedAuthority("ROLE_${usuario.idRol.nombre.uppercase()}")
        )

        return User(
            usuario.usuario,
            usuario.contrasenia,
            authorities
        )
    }


}