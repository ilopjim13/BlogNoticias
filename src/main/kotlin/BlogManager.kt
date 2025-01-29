
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import console.Console
import model.Cliente
import model.Comentario
import model.Direccion
import model.Noticia
import java.util.*

class BlogManager(
    private val collNoticia: MongoCollection<Noticia>,
    private val collClientes: MongoCollection<Cliente>,
    private val collComentarios: MongoCollection<Comentario>,
    private val console:Console
) {

    fun publicarNoticia(usuario:String) {
        console.showMessage("Introduce el titulo de la noticia: ", false)
        val titulo = readln()
        print("Introduce el cuerpo de la noticia: ")
        val cuerpo = readln()
        console.showMessage("Introduce los tags separado por , (salud,deporte,...): ", false)
        val tag = readln().split(",").map { it.trim() }
        val noticia = Noticia(titulo, cuerpo, Date(), tag, usuario)
        collNoticia.insertOne(noticia)
    }

    fun escribirComentario(usuario: String) {
        var titulo:String
        do {
            console.showMessage("Introduce el titulo de la noticia: ", false)
            titulo = readln()
            if (titulo != (collNoticia.find(Filters.eq("titulo", titulo)).first()?.titulo ?: "")) {
                console.showMessage("Esta noticia no existe")
                titulo = ""
            }
        } while (titulo == "")
        console.showMessage("Introduce el comentario: ", false)
        val texto = readln()
        val comentario = Comentario(usuario, titulo, texto, Date())
        collComentarios.insertOne(comentario)
    }

    fun registrarUsuario() {
        try {
            console.showMessage("Introduce el correo del usuario: ", false)
            val correo = readln()
            console.showMessage("Introduce el nombre completo del usuario: ", false)
            val nombre = readln()
            var nick:String
            do {
                console.showMessage("Introduce el nick único del usuario: ", false)
                nick = readln()
                if (nick == (collClientes.find(Filters.eq("nick", nick)).first()?.nick ?: "")) {
                    console.showMessage("Este nick ya existe")
                    nick = ""
                }
            } while (nick == "")
            val direccion = registrarDireccion()
            console.showMessage("Introduce los números de teléfono separados por , (766554433,888776644,...): ")
            val tlfns = readln().split(",")
            val cliente = Cliente(correo, nombre, nick, true, tlfns, direccion)
            collClientes.insertOne(cliente)
        } catch (e:Exception) {
            console.showMessage("Clave duplicada.")
        }

    }

    private fun registrarDireccion():Direccion {
        console.showMessage("Introduce la calle: ", false)
        val calle = readln()
        console.showMessage("Introduce el número: ", false)
        val num = readln()
        console.showMessage("Introduce la puerta: ", false)
        val puerta = readln()
        console.showMessage("Introduce el CP: ", false)
        val cp = readln()
        console.showMessage("Introduce la ciudad: ", false)
        val ciudad = readln()

        return Direccion(calle, num, puerta, cp, ciudad)
    }

    fun listarNoticiasUsuario() {
        console.showMessage("Introduce el nick del usuario: ", false)
        val nick = readln()

        val filtroClienteNick = Filters.eq("nick", nick)
        val filtroNoticiaNick = Filters.eq("user", nick)

        if (collClientes.find(filtroClienteNick).toList().isNotEmpty()) {
            collNoticia.find(filtroNoticiaNick).forEach {
                console.showMessage(it.toString())
            }
        } else console.showMessage("No existe este usuario")
    }

    fun listarComentariosNoticia() {
        var titulo:String
        do {
            console.showMessage("Introduce el titulo de la noticia: ", false)
            titulo = readln()
            if (titulo != (collNoticia.find(Filters.eq("titulo", titulo)).first()?.titulo ?: "")) {
                console.showMessage("Esta noticia no existe")
                titulo = ""
            }
        } while (titulo == "")
        val filtroNoticia = Filters.eq("noticia", titulo)
        collComentarios.find(filtroNoticia).forEach {
            console.showMessage(it.toString())
        }
    }

    fun noticiasPorTag() {
        console.showMessage("Introduce un tag: ", false)
        val tag = readln()
        val filtroTag = Filters.eq("tag", tag)
        collNoticia.find(filtroTag).forEach {
            console.showMessage(it.toString())
        }

    }

    fun listarUltimasNoticias() {
        println("10 ULTIMAS NOTICIAS PUBLICADAS")
        val sortDescending = Sorts.descending("fechaPub")
        collNoticia.find()
            .sort(sortDescending)
            .limit(10)
            .forEach {
                console.showMessage(it.toString())
            }
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

}