package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.entity.Cancion
import com.example.Clon_Spotify_Back.repository.CancionJPA
import com.example.Clon_Spotify_Back.repository.ListaJPA
import com.example.Clon_Spotify_Back.service.CancionService
import com.example.Clon_Spotify_Back.wrapper.MapearCancion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.streams.toList


@Service
class CancionServiceImpl: CancionService {

    @Autowired
    private lateinit var cancionJPA: CancionJPA

    @Autowired
    private lateinit var listaJPA: ListaJPA

    @Autowired
    private lateinit var mapearCancion: MapearCancion


    override fun listar(): List<CancionDTO> {
        return cancionJPA.findAllFromVista()
            .map { mapearCancion.toDTOVista(it) }
    }


    override fun agregarCancion(cancionDTO: CancionDTO) {
        TODO("Not yet implemented")
    }

    override fun randomCancion(): CancionDTO? {
        val randomCancion = cancionJPA.findRandomCancion()
        return randomCancion?.let { mapearCancion.toDTOVista(it) }
    }

    override fun buscarPorId(idCancion:Long): CancionDTO? {
        val cancion = cancionJPA.findCancionById(idCancion)
        return cancion?.let { mapearCancion.toDTOVista(it) }
    }


}