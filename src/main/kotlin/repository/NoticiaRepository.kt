package repository

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import model.Noticia
import org.bson.conversions.Bson

class NoticiaRepository(private val collNoticia: MongoCollection<Noticia>, ) {

    /**
     * Inserta una noticia en la base de datos
     */
    fun publicarNoticia(noticia: Noticia) { collNoticia.insertOne(noticia) }

    /**
     * Comprueba si la noticia existe o no
     */
    fun comprobarNoticia(titulo:String):Boolean  = titulo != (collNoticia.find(Filters.eq("titulo", titulo)).first()?.titulo ?: "")

    /**
     * Devuelve una lista de noticias por usuario
     */
    fun listarNoticiaUsuario(filtroNoticiaNick: Bson): FindIterable<Noticia> = collNoticia.find(filtroNoticiaNick)

    /**
     * Comprueba si el tag está en alguna noticia o no
     */
    fun comprobarNoticiaTag(filtroTag:Bson):Boolean  = collNoticia.find(filtroTag).toList().isNotEmpty()

    /**
     * Devuelve una lista de noticias por tag
     */
    fun noticiasPorTag(filtroTag: Bson): FindIterable<Noticia> = collNoticia.find(filtroTag)

    /**
     * Devuleve todas las noticias
     */
    fun listarUltimasNoticias(): FindIterable<Noticia> = collNoticia.find()

}