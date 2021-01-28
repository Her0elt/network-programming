package exercise2

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*
import java.util.Collections.synchronizedList
import kotlin.concurrent.thread

fun main() {
    val connections = synchronizedList <Pair<Int,InetAddress>>(mutableListOf())
    val ds = DatagramSocket(1250)
    val sc = Scanner(System.`in`)
    var receive = ByteArray(65535)
    var c = true
    val thread1 =thread() {
        while(c){
            val packet = DatagramPacket(receive, receive.size)
            ds.receive(packet)
            if (packet.port != -1 && packet.address !=null) {
                connections.add(Pair(packet.port, packet.address))
            }
            receive = ByteArray(65535)
        }
    }
    val thread2 =thread (){
    var m = true
    while (m) {
        val msg = sc.nextLine()
        val buf: ByteArray = msg.toByteArray()
        for (con in connections){
            val dpSend = DatagramPacket(buf, buf.size, con.second, con.first)
            ds.send(dpSend)
        }
        if(msg == "")m=false
    }
    c = false
    }
    thread1.join()
    thread2.join()
}