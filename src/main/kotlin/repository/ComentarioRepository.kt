package repository

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import model.Comentario
import org.bson.conversions.Bson

class ComentarioRepository(private val collComentarios: MongoCollection<Comentario>) {

    fun escribirComentario(comentario: Comentario) { collComentarios.insertOne(comentario) }

    fun listarComentarioNoticia(filtroNoticia:Bson): FindIterable<Comentario> = collComentarios.find(filtroNoticia)

}