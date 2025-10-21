package com.example.Clon_Spotify_Back.service.impl

import com.example.Clon_Spotify_Back.service.ChatService
import com.google.genai.Client
import com.google.genai.types.GenerateContentResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class ChatServiceImpl(

    @Value("\${api.gemini.key}")
    private val apiKey: String

) : ChatService {

    private val cliente: Client = Client.builder()
        .apiKey(apiKey)
        .build()

    override fun chat(request: String): String {
        val response: GenerateContentResponse = cliente
            .models.generateContent(
                "gemini-2.5-flash",
                request,
                null
            )

        return response.text()
    }
}