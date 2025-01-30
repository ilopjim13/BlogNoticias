package service

import console.Console
import model.Noticia
import repository.NoticiaRepository
import java.util.*

class NoticiaService(private val noticiaRepository: NoticiaRepository, private val console: Console) {

    fun publicarNoticia(usuario:String) {

        console.showMessage("Introduce el titulo de la noticia: ", false)
        val titulo = readln()
        print("Introduce el cuerpo de la noticia: ")
        val cuerpo = readln()
        console.showMessage("Introduce los tags separado por , (salud,deporte,...): ", false)
        val tag = readln().split(",").map { it.trim() }
        val noticia = Noticia(titulo, cuerpo, Date(), tag, usuario)

        noticiaRepository.publicarNoticia(noticia)
    }

}