package com.example.Clon_Spotify_Back


import com.example.Clon_Spotify_Back.jwt.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component

@EnableAsync
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

	private val jwtService: JwtService
): CommandLineRunner {
	override fun run(vararg args: String?) {

		println(jwtService.generarRefreshToken("wqw"))

	}
}