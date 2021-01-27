package exercise2

import java.net.InetSocketAddress
import java.nio.channels.AsynchronousSocketChannel

suspend fun runServer(){
    val server = asyncOpenServer()
    server.asyncBind(InetSocketAddress("127.0.0.1", 1250))
    val worker: AsynchronousSocketChannel =  server.asyncAccept()
    val instructions = "Write a equation in the format 1 + 1, then i'll give you the answer, end with enter"
    sendMessage(instructions, worker)
    var msg = readMessage(worker)
    while(msg != ""){
        val answer = calc(msg.split(" ".toRegex()).toTypedArray())
        sendMessage(answer, worker)
        msg = readMessage(worker)
    }
}
 suspend fun main(){
    runServer()
}

