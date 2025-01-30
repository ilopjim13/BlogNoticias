package repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Cliente

class ClienteRepository(private val collClientes: MongoCollection<Cliente>) {

    fun registrarUsuario(usuario:Cliente){
        collClientes.insertOne(usuario)
    }

    fun comprobarUsuario(nick:String) = nick == (collClientes.find(Filters.eq("nick", nick)).first()?.nick ?: "")

}