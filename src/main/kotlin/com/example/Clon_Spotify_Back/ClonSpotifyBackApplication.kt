package com.example.Clon_Spotify_Back


import com.google.genai.Client
import com.google.genai.types.GenerateContentResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClonSpotifyBackApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<ClonSpotifyBackApplication>(*args)
		}
	}
}