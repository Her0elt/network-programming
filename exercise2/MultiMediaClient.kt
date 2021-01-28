package exercise2

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    val ds = DatagramSocket()
    var receive = ByteArray(6000)
    var msg = " "
    val buf: ByteArray = msg.toByteArray()
    val dpSend = DatagramPacket(buf, buf.size, InetAddress.getLocalHost(), 1250)
    ds.send(dpSend)
    while (true) {
        val dpReceive = DatagramPacket(receive, receive.size, InetAddress.getLocalHost(), 1250)
        ds.receive(dpReceive)
        msg = dataB(receive).toString()
        println(msg)
        receive = ByteArray(6000)
        if(msg.isBlank())break
    }


}
fun dataB(a: ByteArray): StringBuilder {
    val ret = StringBuilder()
    for (element in a){
        ret.append(element.toChar())
    }
    return ret
}