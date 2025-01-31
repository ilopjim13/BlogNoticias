package service

import com.mongodb.client.model.Filters
import console.Console
import model.Comentario
import repository.ComentarioRepository
import repository.NoticiaRepository
import java.util.*

class ComentarioService(
    private val comentarioRepository: ComentarioRepository,
    private val noticiaRepository: NoticiaRepository,
    private val console: Console) {

    fun escribirComentario(usuario:String) {
        val titulo = obtenerTitulo()
        console.showMessage("Introduce el comentario: ", false)
        val texto = readln()
        val comentario = Comentario(usuario, titulo, texto, Date())

        comentarioRepository.escribirComentario(comentario)
    }

    fun listarComentarioNoticia(){
        val titulo = obtenerTitulo()
        val filtroNoticia = Filters.eq("noticia", titulo)
        comentarioRepository.listarComentarioNoticia(filtroNoticia).forEach {
            console.showMessage(it.toString())
        }
    }

    private fun obtenerTitulo() :String {
        var titulo:String
        do {
            console.showMessage("Introduce el título de la noticia: ", false)
            titulo = readln()
            if (noticiaRepository.comprobarNoticia(titulo)) {
                console.showMessage("Esta noticia no existe")
                titulo = ""
            }
        } while (titulo == "")
        return titulo
    }

}