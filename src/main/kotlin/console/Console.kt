package console

class Console {

    /**
     * Muestra el menú
     */
    fun showMenu() {
        showMessage("---------MENU----------")
        showMessage("1- Publicar una noticia")
        showMessage("2- Hacer un comentario")
        showMessage("3- Registrar un usuario")
        showMessage("4- Listar noticias de un usuario")
        showMessage("5- Listar comentarios de una noticia")
        showMessage("6- Buscar noticias por tag")
        showMessage("7- Listar las últimas 10 noticias")
        showMessage("8- Salir")
    }

    /**
     * Imprime por pantalla un mensaje
     */
    fun showMessage(message:String, breakLine:Boolean = true) {
        if (breakLine) println(message)
        else print(message)
    }

    /**
     * Obtiene una opción válida y la devuelve para ejecutar el menú.
     */
    fun getOption(options:Int):Int {
        var option:Int
        do {
            showMessage(">> Selecciona una opción: ", false)
            option = readln().toIntOrNull() ?: -1
            if (option !in (1..options)) showMessage("ERROR - Opción errónea.")
        } while (option !in (1..options))
        return  option
    }


}
