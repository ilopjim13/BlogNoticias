package service

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import console.Console
import model.Noticia
import repository.ClienteRepository
import repository.NoticiaRepository
import java.util.*

class NoticiaService(private val noticiaRepository: NoticiaRepository, private val clienteRepository: ClienteRepository, private val console: Console) {

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

    fun listarNoticiaUsuario() {
        console.showMessage("Introduce el nick del usuario: ", false)
        val nick = readln()

        val filtroClienteNick = Filters.eq("nick", nick)
        val filtroNoticiaNick = Filters.eq("user", nick)
        if (clienteRepository.comprobarNoticiaUsuario(filtroClienteNick)) {
            noticiaRepository.listarNoticiaUsuario(filtroNoticiaNick).forEach {
                console.showMessage(it.toString())
            }
        } else console.showMessage("No existe este usuario")
    }

    fun noticiasPorTag() {
        console.showMessage("Introduce un tag: ", false)
        val tag = readln()
        val filtroTag = Filters.eq("tag", tag)
        if (noticiaRepository.comprobarNoticiaTag(filtroTag)) {
            noticiaRepository.noticiasPorTag(filtroTag).forEach {
                console.showMessage(it.toString())
            }
        }
    }

    fun listarUltimasNoticias() {
        console.showMessage("10 ULTIMAS NOTICIAS PUBLICADAS")
        val sortDescending = Sorts.descending("fechaPub")
        noticiaRepository.listarUltimasNoticias()
            .sort(sortDescending)
            .limit(10)
            .forEach {
                console.showMessage(it.toString())
            }
    }

}