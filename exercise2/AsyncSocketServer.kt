package exercise2

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel

suspend fun runServer(){
    val server = asyncOpenServer()
    server.asyncBind(InetSocketAddress("127.0.0.1", 1250))
    val worker: AsynchronousSocketChannel =  server.asyncAccept()
    val instructions = "Write a equation in the format 1 + 1, then i'll give you the answer, end with enter"
    worker.asyncWrite(ByteBuffer.wrap(instructions.toByteArray()))
    val buffer: ByteBuffer = ByteBuffer.allocate(128)
    worker.asyncRead(buffer)
    var msg = String(buffer.array()).trim { it <= ' ' }
    println(msg)
    buffer.clear()
    while(msg != ""){
        val answer = calc(msg.split(" ".toRegex()).toTypedArray())
        sendMessage(answer, worker)
        msg = readMessage(worker)
    }
}
 suspend fun main(){
    runServer()
}

