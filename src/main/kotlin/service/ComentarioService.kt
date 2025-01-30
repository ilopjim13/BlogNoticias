package service

import com.mongodb.client.model.Filters
import console.Console
import model.Comentario
import repository.ComentarioRepository
import repository.NoticiaRepository
import java.util.*

class ComentarioService(private val comentarioRepository: ComentarioRepository, private val noticiaRepository: NoticiaRepository, private val console: Console) {

    fun escribirComentario(usuario:String) {
        var titulo:String
        do {
            console.showMessage("Introduce el titulo de la noticia: ", false)
            titulo = readln()
            if (noticiaRepository.comprobarNoticia(titulo)) {
                console.showMessage("Esta noticia no existe")
                titulo = ""
            }
        } while (titulo == "")
        console.showMessage("Introduce el comentario: ", false)
        val texto = readln()
        val comentario = Comentario(usuario, titulo, texto, Date())

        comentarioRepository.escribirComentario(comentario)

    }

}