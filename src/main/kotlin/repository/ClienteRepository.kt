package repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Cliente
import org.bson.conversions.Bson

class ClienteRepository(private val collClientes: MongoCollection<Cliente>) {

    /**
     * Inserta el usuario a la base de datos
     */
    fun altaUsuario(usuario:Cliente) { collClientes.insertOne(usuario) }

    /**
     * Devuelve un Boolean si el nick del usuario existe o no
     */
    fun comprobarUsuario(nick:String):Boolean = nick == (collClientes.find(Filters.eq("nick", nick)).first()?.nick ?: "")

    /**
     * Devuelve un Boolean si el correo del usuario existe o no
     */
    fun comprobarCorreo(correo:String):Boolean = correo == (collClientes.find(Filters.eq("_id", correo)).first()?._id ?: "")

    /**
     * Devuelve un Boolean si el usuario no tiene ninguna noticia
     */
    fun comprobarNoticiaUsuario(filtroClienteNick: Bson):Boolean  = collClientes.find(filtroClienteNick).toList().isNotEmpty()

    /**
     * Inserta una lista de usuarios
     */
    fun insertarClientes(listaClientes:List<Cliente>) { collClientes.insertMany(listaClientes) }

    /**
     * Comprueba si el usuario que intenta registrarse existe en la base de datos
     */
    fun comprobaRegister(filtroClienteNick: Bson, nick: String):Boolean = collClientes.find(filtroClienteNick).toList().first().nick == nick

}