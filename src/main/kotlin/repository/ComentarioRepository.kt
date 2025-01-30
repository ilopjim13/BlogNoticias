package repository

import com.mongodb.client.MongoCollection
import model.Comentario

class ComentarioRepository(private val collComentarios: MongoCollection<Comentario>) {

    fun escribirComentario(comentario: Comentario) {
        collComentarios.insertOne(comentario)
    }

}