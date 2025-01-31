package repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Cliente
import org.bson.conversions.Bson

class ClienteRepository(private val collClientes: MongoCollection<Cliente>) {

    fun registrarUsuario(usuario:Cliente) { collClientes.insertOne(usuario) }

    fun comprobarUsuario(nick:String):Boolean = nick == (collClientes.find(Filters.eq("nick", nick)).first()?.nick ?: "")

    fun comprobarNoticiaUsuario(filtroClienteNick: Bson):Boolean  = collClientes.find(filtroClienteNick).toList().isNotEmpty()

}