package repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Noticia

class NoticiaRepository(private val collNoticia: MongoCollection<Noticia>) {

    fun publicarNoticia(noticia: Noticia) {
        collNoticia.insertOne(noticia)
    }

    fun comprobarNoticia(titulo:String):Boolean {
        return titulo != (collNoticia.find(Filters.eq("titulo", titulo)).first()?.titulo ?: "")
    }

}