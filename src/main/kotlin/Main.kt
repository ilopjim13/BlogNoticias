
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

    val clienteService = ClienteService(clienteRepository)
    val comentarioService = ComentarioService(comentarioRepository)
    val noticiaService = NoticiaService(noticiaRepository)

    val blogManager = BlogManager(collNoticias, collClientes,collComentarios, console)

    collClientes.drop()

    blogManager.insertClientes(collClientes)

    val userNick = blogManager.register(collClientes)

    var option:Int

    do {
        console.showMenu()
        option = console.getOption(8)
        executeMenu(option,userNick, blogManager)
    } while (option != 8)

    ConexionMongo.close()
}

fun executeMenu(option:Int,usuario:String, blogManager: BlogManager) {
    when (option) {
        1 -> blogManager.publicarNoticia(usuario)
        2 -> blogManager.escribirComentario(usuario)
        3 -> blogManager.registrarUsuario()
        4 -> blogManager.listarNoticiasUsuario()
        5 -> blogManager.listarComentariosNoticia()
        6 -> blogManager.noticiasPorTag()
        7 -> blogManager.listarUltimasNoticias()
    }
}


