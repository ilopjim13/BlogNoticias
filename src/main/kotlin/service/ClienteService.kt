package service

import console.Console
import model.Cliente
import model.Direccion
import repository.ClienteRepository

class ClienteService(private val clienteRepository: ClienteRepository, private val console: Console) {

    fun registrarUsuario() {
        console.showMessage("Introduce el correo del usuario: ", false)
        val correo = readln()
        console.showMessage("Introduce el nombre completo del usuario: ", false)
        val nombre = readln()
        var nick:String
        do {
            console.showMessage("Introduce el nick único del usuario: ", false)
            nick = readln()
            if (clienteRepository.comprobarUsuario(nick)) {
                console.showMessage("Este nick ya existe")
                nick = ""
            }
        } while (nick == "")
        val direccion = registrarDireccion()
        console.showMessage("Introduce los números de teléfono separados por , (766554433,888776644,...): ")
        val tlfns = readln().split(",")
        val cliente = Cliente(correo, nombre, nick, true, tlfns, direccion)
        clienteRepository.registrarUsuario(cliente)
    }

    private fun registrarDireccion(): Direccion {
        console.showMessage("Introduce la calle: ", false)
        val calle = readln()
        console.showMessage("Introduce el número: ", false)
        val num = readln()
        console.showMessage("Introduce la puerta: ", false)
        val puerta = readln()
        console.showMessage("Introduce el CP: ", false)
        val cp = readln()
        console.showMessage("Introduce la ciudad: ", false)
        val ciudad = readln()

        return Direccion(calle, num, puerta, cp, ciudad)
    }

}