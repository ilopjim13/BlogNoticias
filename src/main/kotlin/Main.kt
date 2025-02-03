
import com.mongodb.client.MongoCollection
import console.Console
import database.ConexionMongo
import model.Cliente
import model.Comentario
import model.Noticia
import repository.ClienteRepository
import repository.ComentarioRepository
import repository.NoticiaRepository
import service.ClienteService
import service.ComentarioService
import service.NoticiaService

fun main() {

    val console = Console()
    val database = ConexionMongo.getDatabase("dbada")

    val collClientes: MongoCollection<Cliente> = database.getCollection("coll_usuarios", Cliente::class.java)
    val collNoticias: MongoCollection<Noticia> = database.getCollection("coll_noticias", Noticia::class.java)
    val collComentarios: MongoCollection<Comentario> = database.getCollection("coll_comentarios", Comentario::class.java)

    val clienteRepository = ClienteRepository(collClientes)
    val comentarioRepository = ComentarioRepository(collComentarios)
    val noticiaRepository = NoticiaRepository(collNoticias)

    val clienteService = ClienteService(clienteRepository, console)
    val comentarioService = ComentarioService(comentarioRepository, noticiaRepository, console)
    val noticiaService = NoticiaService(noticiaRepository, clienteRepository, console)

    val blogManager = BlogManager(noticiaService, clienteService, comentarioService, console)

    collClientes.drop()
    blogManager.insertClientes()

    val userNick = blogManager.register()
    var option:Int

    do {
        console.showMenu()
        option = console.getOption(8)
        blogManager.ejecutarMenu(option,userNick)
    } while (option != 8)

    ConexionMongo.close()
}




