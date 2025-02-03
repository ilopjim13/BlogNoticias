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

    /**
     * Escribe un comentario de una noticia en específico y guarda en la base de datos el comentario
     * con el nombre del usuario y el titulo de la noticia
     */
    fun escribirComentario(usuario:String) {
        val titulo = obtenerTitulo()
        console.showMessage("Introduce el comentario: ", false)
        val texto = readln()
        val comentario = Comentario(usuario, titulo, texto, Date())

        comentarioRepository.escribirComentario(comentario)
    }

    /**
     * Obtiene los comentarios filtrados por el titulo de la noticia y los muestra por pantalla
     */
    fun listarComentarioNoticia(){
        val titulo = obtenerTitulo()
        val filtroNoticia = Filters.eq("noticia", titulo)
        val listaComentarios = comentarioRepository.listarComentarioNoticia(filtroNoticia)
        if (listaComentarios.toList().isNotEmpty()) {
            listaComentarios.forEach {
                console.showMessage(it.toString())
            }
        } else console.showMessage("Esta noticia no tiene comentarios.")

    }

    /**
     * Obtiene un titulo existente en la base de datos
     */
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