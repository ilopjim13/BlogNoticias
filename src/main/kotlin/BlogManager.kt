
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import console.Console
import model.Cliente
import model.Direccion
import service.ClienteService
import service.ComentarioService
import service.NoticiaService

class BlogManager(
    private val noticiaService: NoticiaService,
    private val clienteService: ClienteService,
    private val comentarioService: ComentarioService,
    private val console:Console
) {

    fun publicarNoticia(usuario:String) {
        noticiaService.publicarNoticia(usuario)
    }

    fun escribirComentario(usuario: String) {
        comentarioService.escribirComentario(usuario)
    }

    fun registrarUsuario() {
        try {
            clienteService.registrarUsuario()
        } catch (e:Exception) {
            console.showMessage("Clave duplicada.")
        }
    }

    fun listarNoticiasUsuario() {
        noticiaService.listarNoticiaUsuario()
    }

    fun listarComentariosNoticia() {
        comentarioService.listarComentarioNoticia()
    }

    fun noticiasPorTag() {
        noticiaService.noticiasPorTag()
    }

    fun listarUltimasNoticias() {
        noticiaService.listarUltimasNoticias()
    }

    fun register(collectionClientes: MongoCollection<Cliente>):String {
        var nick:String
        do {
            var accept = false
            print("Introduce tu nickName para registrarte: ")
            nick = readln()

            val filtroNick = Filters.eq("nick", nick)
            if (collectionClientes.find(filtroNick).toList().isNotEmpty()) {
                if (collectionClientes.find(filtroNick).toList().first().nick == nick) {
                    accept = true
                }
            }

        } while (!accept)

        return nick
    }

    fun insertClientes(collectionClientes: MongoCollection<Cliente>) {
        try {
            val direccion = Direccion("alamo", "24","", "04638", "Mojacar")
            val cliente = Cliente("maria@gmail.com", "Maria", "mar14", true, listOf("950475656", "666888999"), direccion)
            collectionClientes.insertOne(cliente)
            val direccion2 = Direccion("desconocida", "24","", "04003", "Almeria")
            val direccion3 = Direccion("principal", "2","", "04003", "Almeria")
            val direccion4 = Direccion("principal", "1","", "04003", "Almeria")
            val cliente2 = Cliente("pedro@gmail.com", "Pedro", "periko", true, listOf("950475656", "666888999"), direccion2)
            val cliente3 = Cliente("ana@gmail.com", "Ana", "anuski", true, listOf("950475656", "666888999"), direccion3)
            val cliente4 = Cliente("antonio@gmail.com", "Antonio", "toni", true, listOf("950475658", "666888999"), direccion4)
            val cliente5 = Cliente("agustin@gmail.com", "Agustin", "agus", true, listOf("950475656", "666888999"), direccion4)
            val listaClientes = listOf(cliente2, cliente3, cliente4, cliente5)
            collectionClientes.insertMany(listaClientes)
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