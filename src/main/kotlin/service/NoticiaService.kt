package service

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import console.Console
import model.Noticia
import repository.ClienteRepository
import repository.NoticiaRepository
import java.util.*

class NoticiaService(private val noticiaRepository: NoticiaRepository, private val clienteRepository: ClienteRepository, private val console: Console) {

    /**
     * Publica una noticia con los datos necesarios de las noticias y la inserta en la base de datos
     */
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

    /**
     * Obtiene una lista de noticias por el nick de los usuarios y las muestra por pantalla
     */
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

    /**
     * Obtiene una lista de noticias por el tag introducido y las muestra por pantalla
     */
    fun noticiasPorTag() {
        console.showMessage("Introduce un tag: ", false)
        val tag = readln()
        val filtroTag = Filters.eq("tag", tag)
        if (noticiaRepository.comprobarNoticiaTag(filtroTag)) {
            noticiaRepository.noticiasPorTag(filtroTag).forEach {
                console.showMessage(it.toString())
            }
        } else console.showMessage("No se ha encontrado ninguna noticia con este tag.")
    }

    /**
     * Obtiene las 10 últimas noticias publicadas por los usuarios
     */
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