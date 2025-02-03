
import console.Console
import service.ClienteService
import service.ComentarioService
import service.NoticiaService

class BlogManager(
    private val noticiaService: NoticiaService,
    private val clienteService: ClienteService,
    private val comentarioService: ComentarioService,
    private val console:Console
) {

    private fun publicarNoticia(usuario:String) {
        try {
            noticiaService.publicarNoticia(usuario)
            console.showMessage("Noticia publicada.")
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun escribirComentario(usuario: String) {
        try {
            comentarioService.escribirComentario(usuario)
            console.showMessage("Comentario publicado.")
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun registrarUsuario() {
        try {
            clienteService.altaUsuario()
            console.showMessage("Usuario registrado.")
        } catch (e:Exception) {
            console.showMessage("Clave duplicada.")
        }
    }

    private fun listarNoticiasUsuario() {
        try {
            noticiaService.listarNoticiaUsuario()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun listarComentariosNoticia() {
        try {
            comentarioService.listarComentarioNoticia()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun noticiasPorTag() {
        try {
            noticiaService.noticiasPorTag()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun listarUltimasNoticias() {
        try {
            noticiaService.listarUltimasNoticias()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    fun iniciarSesion():String {
        try {
            return clienteService.iniciarSesion()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
            return ""
        }
    }

    fun insertarClientes() {
        try {
            clienteService.insertarClientes()
        } catch (e: Exception) {
            println("Clave duplicada")
        }
    }

    fun ejecutarMenu(option:Int,usuario:String) {
        when (option) {
            1 -> publicarNoticia(usuario)
            2 -> escribirComentario(usuario)
            3 -> registrarUsuario()
            4 -> listarNoticiasUsuario()
            5 -> listarComentariosNoticia()
            6 -> noticiasPorTag()
            7 -> listarUltimasNoticias()
        }
    }

}