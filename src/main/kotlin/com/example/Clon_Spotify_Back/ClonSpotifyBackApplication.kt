package com.example.Clon_Spotify_Back


import com.example.Clon_Spotify_Back.jwt.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class ClonSpotifyBackApplication  {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<ClonSpotifyBackApplication>(*args)
		}
	}
}

@Component
class Config(
	@Value("\${var.secret}")
	private val varSecret: String,
	private val jwtService: JwtService
): CommandLineRunner {
	override fun run(vararg args: String?) {

		println(varSecret)
		println(jwtService.generarRefreshToken("wqw"))

	}
}