package com.example.Clon_Spotify_Back.mappers

import com.example.Clon_Spotify_Back.dto.CancionDTO
import com.example.Clon_Spotify_Back.entity.Autor
import com.example.Clon_Spotify_Back.entity.Cancion
import com.example.Clon_Spotify_Back.entity.Genero
import com.example.Clon_Spotify_Back.projection.CancionVistaProjection
import com.example.Clon_Spotify_Back.repository.GeneroJPA
import com.example.Clon_Spotify_Back.repository.ListaJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class MapearCancion {

    @Autowired
    private lateinit var listaJPA: ListaJPA

    @Autowired
    private lateinit var generoJPA: GeneroJPA

    fun toDTO(cancion: Cancion,autor:Autor): CancionDTO {
        return CancionDTO(
            idCancion = cancion.idCancion,
            nombre = cancion.nombre,
            duracion = cancion.duracion,
            url = cancion.url,
            genero = cancion.idGenero.nombre,
            autor = autor.nombre
        )
    }

    fun toEntity(cancionDTO: CancionDTO) : Cancion{
        // Cambio aquí: usar findByNombre en lugar de findIdByGenero
        val idGeneros: Genero = generoJPA.findIdByGenero(cancionDTO.genero)
            .orElseThrow { RuntimeException ("No se encontro el genero") }

        return Cancion(
            idCancion = cancionDTO.idCancion,
            nombre = cancionDTO.nombre,
            duracion = cancionDTO.duracion,
            url = cancionDTO.url,
            idGenero = idGeneros
        )
    }

    fun toDTOVista(projection: CancionVistaProjection): CancionDTO {
        return CancionDTO(
            idCancion = projection.getIdCancion(),
            nombre = projection.getNombre(),
            duracion = projection.getDuracion(),
            url = projection.getUrl(),
            genero = projection.getGenero(),
            autor = projection.getAutor()
        )
    }
}