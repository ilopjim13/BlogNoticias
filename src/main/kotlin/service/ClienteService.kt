package service

import com.mongodb.client.model.Filters
import console.Console
import model.Cliente
import model.Direccion
import repository.ClienteRepository

class ClienteService(private val clienteRepository: ClienteRepository, private val console: Console) {

    fun registrarUsuario() {
        var correo:String
        do {
            console.showMessage("Introduce el correo del usuario: ", false)
            correo = readln()
            if (clienteRepository.comprobarCorreo(correo)) {
                console.showMessage("Este correo ya existe")
                correo = ""
            }
        } while (correo == "")
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

    fun register():String {
        var nick:String
        do {
            var accept = false
            print("Introduce tu nickName para registrarte: ")
            nick = readln()

            val filtroNick = Filters.eq("nick", nick)
            if (clienteRepository.comprobarNoticiaUsuario(filtroNick)) {
                if (clienteRepository.comprobaRegister(filtroNick, nick)) {
                    accept = true
                }
            }
        } while (!accept)

        return nick
    }

    fun insertClientes() {
        val direccion = Direccion("alamo", "24","", "04638", "Mojacar")
        val direccion2 = Direccion("desconocida", "24","", "04003", "Almeria")
        val direccion3 = Direccion("principal", "2","", "04003", "Almeria")
        val direccion4 = Direccion("principal", "1","", "04003", "Almeria")
        val cliente = Cliente("maria@gmail.com", "Maria", "mar14", true, listOf("950475656", "666888999"), direccion)
        val cliente2 = Cliente("pedro@gmail.com", "Pedro", "periko", true, listOf("950475656", "666888999"), direccion2)
        val cliente3 = Cliente("ana@gmail.com", "Ana", "anuski", true, listOf("950475656", "666888999"), direccion3)
        val cliente4 = Cliente("antonio@gmail.com", "Antonio", "toni", true, listOf("950475658", "666888999"), direccion4)
        val cliente5 = Cliente("agustin@gmail.com", "Agustin", "agus", true, listOf("950475656", "666888999"), direccion4)
        val listaClientes = listOf(cliente, cliente2, cliente3, cliente4, cliente5)
        clienteRepository.insertClientes(listaClientes)

    }

}