
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

    // Se crean la consola y la conexión con la base de datos
    val console = Console()
    val database = ConexionMongo.getDatabase("dbada")

    // Se crean todas las conexiones con las tablas correspondientes
    val collClientes: MongoCollection<Cliente> = database.getCollection("coll_usuarios", Cliente::class.java)
    val collNoticias: MongoCollection<Noticia> = database.getCollection("coll_noticias", Noticia::class.java)
    val collComentarios: MongoCollection<Comentario> = database.getCollection("coll_comentarios", Comentario::class.java)

    // Se crean los repositorios
    val clienteRepository = ClienteRepository(collClientes)
    val comentarioRepository = ComentarioRepository(collComentarios)
    val noticiaRepository = NoticiaRepository(collNoticias)

    // Se crean los service
    val clienteService = ClienteService(clienteRepository, console)
    val comentarioService = ComentarioService(comentarioRepository, noticiaRepository, console)
    val noticiaService = NoticiaService(noticiaRepository, clienteRepository, console)

    // Se crea el blogManager que va a ejecutar el menu y contiene las llamadas necesarias
    val blogManager = BlogManager(noticiaService, clienteService, comentarioService, console)

    // Vaciamos la tabla de clientes y los insertamos para reiniciar los clientes
    // collClientes.drop()
    // blogManager.insertarClientes()

    // iniciamos sesión con el blogManager
    val userNick = blogManager.iniciarSesion()
    var option:Int

    // Comenzamos la aplicación
    do {
        console.showMenu()
        option = console.getOption(8)
        blogManager.ejecutarMenu(option,userNick)
    } while (option != 8)

    // Se cierra sesión con la base de datos
    ConexionMongo.close()
}




