package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.entity.Genero
import com.example.Clon_Spotify_Back.repository.GeneroJPA
import com.example.Clon_Spotify_Back.service.GeneroService
import org.springframework.ai.tool.annotation.Tool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class GeneroServiceImpl(
    private val generoJPA: GeneroJPA
) : GeneroService {

    @Tool(description = "Ordename segun la importancia de estos generos")
    override fun listar(): List<Genero> {
        return generoJPA.findAll()
    }
}