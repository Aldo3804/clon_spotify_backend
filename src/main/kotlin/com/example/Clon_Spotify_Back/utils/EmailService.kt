package com.example.Clon_Spotify_Back.utils

import com.google.api.client.util.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Service
class EmailService(

    private val mailSender: MailSender,


) {

    @Async
    fun enviar(para:String,asunto:String,cuerpo:String){
        try{
            val mensaje = SimpleMailMessage()

            mensaje.from = "chindrinol@gmail.com"
            mensaje.setTo(para)
            mensaje.subject = asunto
            mensaje.text = cuerpo

            mailSender.send(mensaje)
            println("Correo de prueba enviado exitosamente a: $para")

        }catch (e: Exception){
            println("Error al enviar el correo: ${e.message}")
            throw RuntimeException("Error al enviar el correo", e)
        }
    }


}
