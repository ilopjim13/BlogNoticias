package repository

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Noticia
import org.bson.conversions.Bson

class NoticiaRepository(private val collNoticia: MongoCollection<Noticia>, ) {

    fun publicarNoticia(noticia: Noticia) { collNoticia.insertOne(noticia) }

    fun comprobarNoticia(titulo:String):Boolean  = titulo != (collNoticia.find(Filters.eq("titulo", titulo)).first()?.titulo ?: "")

    fun listarNoticiaUsuario(filtroNoticiaNick: Bson): FindIterable<Noticia> = collNoticia.find(filtroNoticiaNick)

    fun comprobarNoticiaTag(filtroTag:Bson):Boolean  = collNoticia.find(filtroTag).toList().isNotEmpty()

    fun noticiasPorTag(filtroTag: Bson): FindIterable<Noticia> = collNoticia.find(filtroTag)

    fun listarUltimasNoticias(): FindIterable<Noticia> = collNoticia.find()

}