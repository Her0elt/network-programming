package exercise2

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


fun main() {
    val ds = DatagramSocket(1250)
    var receive = ByteArray(5)
    var msg = " "
    while (msg != "") {
        val dpReceive = DatagramPacket(receive, receive.size)
        ds.receive(dpReceive)
        msg = data(receive).toString()
        print(msg)
        val buf: ByteArray = calc(msg.split(" ".toRegex()).toTypedArray()).toByteArray()
        val dpSend = DatagramPacket(buf, buf.size,dpReceive.address,dpReceive.port)
        ds.send(dpSend)
        receive = ByteArray(5)
    }
}



