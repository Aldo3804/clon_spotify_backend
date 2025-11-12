package com.example.Clon_Spotify_Back.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(

    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService

) : OncePerRequestFilter(){

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)

        try {
            val usuario = jwtService.obtenerUsuarioPorToken(jwt)

            if (SecurityContextHolder.getContext().authentication == null) {
                val tipoToken = jwtService.obtenerClaim(jwt, "token_type")

                if (tipoToken.equals("ACCESS")) {
                    val userDetails = this.userDetailsService.loadUserByUsername(usuario)
                    if (jwtService.tokenValido(jwt, userDetails)) {
                        val authToken = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.authorities
                        )
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            }

            filterChain.doFilter(request, response)

        } catch (e: ExpiredJwtException) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Token de acceso expirado ${e.message}")
            return

        } catch (e: JwtException) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Token inválido: ${e.message}")
            return

        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR // 500
            response.writer.write("Error ${e.message}")
            return
        }
    }


}