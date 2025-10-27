package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.repository.CancionJPA
import com.example.Clon_Spotify_Back.repository.ListaJPA
import com.example.Clon_Spotify_Back.service.CancionService
import com.example.Clon_Spotify_Back.mappers.MapearCancion
import org.springframework.stereotype.Service


@Service
class CancionServiceImpl(

    private val cancionJPA: CancionJPA,
    private val listaJPA: ListaJPA,
    private val mapearCancion: MapearCancion

): CancionService {


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