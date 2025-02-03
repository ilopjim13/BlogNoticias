
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
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun escribirComentario(usuario: String) {
        try {
            comentarioService.escribirComentario(usuario)
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
        }
    }

    private fun registrarUsuario() {
        try {
            clienteService.registrarUsuario()
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

    fun register():String {
        try {
            return clienteService.register()
        } catch (e:Exception) {
            console.showMessage(e.message.toString())
            return ""
        }
    }

    fun insertClientes() {
        try {
            clienteService.insertClientes()
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