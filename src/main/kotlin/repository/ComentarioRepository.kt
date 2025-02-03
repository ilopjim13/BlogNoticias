package repository

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import model.Comentario
import org.bson.conversions.Bson

class ComentarioRepository(private val collComentarios: MongoCollection<Comentario>) {

    /**
     * Inserta un comentario en la base de datos
     */
    fun escribirComentario(comentario: Comentario) { collComentarios.insertOne(comentario) }

    /**
     * Devuelve una lista de comentarios por noticia
     */
    fun listarComentarioNoticia(filtroNoticia:Bson): FindIterable<Comentario> = collComentarios.find(filtroNoticia)

}