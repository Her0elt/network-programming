package exercise2

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousServerSocketChannel
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun data(a: ByteArray): StringBuilder {
    val ret = StringBuilder()
    for (element in a){
        ret.append(element.toChar())
    }
    return ret
}

fun calc(array: Array<String>): String {
    if (array.size != 3) return "Wrong format"
     if (array[1] == "/" && array[2] == "0")return "You can't divide by zero"
    when(array[1]){
        "+" -> return (array[0].toDouble() + array[2].toDouble()).toString()
        "-" -> return (array[0].toDouble() - array[2].toDouble()).toString()
        "*" -> return (array[0].toDouble() * array[2].toDouble()).toString()
        "/" -> return (array[0].toDouble() / array[2].toDouble()).toString()
    }
    return "Wrong format"
}
 suspend fun AsynchronousSocketChannel.asyncConnect(hostAddress: InetSocketAddress):Void {
    return suspendCoroutine {
        it.resume(this.connect(hostAddress).get())
    }
}
suspend fun AsynchronousServerSocketChannel.asyncBind(hostAddress: InetSocketAddress): InetSocketAddress {
    return suspendCoroutine {
        this.bind(hostAddress)
        it.resume(hostAddress)
    }
}

suspend fun AsynchronousServerSocketChannel.asyncAccept(): AsynchronousSocketChannel {
    return suspendCoroutine {
        it.resume(this.accept().get())
    }
}
suspend fun asyncOpen(): AsynchronousSocketChannel {
    return suspendCoroutine {
        it.resume(AsynchronousSocketChannel.open())
    }
}
suspend fun asyncOpenServer(): AsynchronousServerSocketChannel {
    return suspendCoroutine {
        it.resume(AsynchronousServerSocketChannel.open())
    }
}


 suspend fun AsynchronousSocketChannel.asyncWrite(buffer: ByteBuffer): Int {
    return suspendCoroutine { continuation ->
        this.write(buffer, continuation, ReadCompletionHandler)
    }
}

 suspend fun AsynchronousSocketChannel.asyncRead(buffer: ByteBuffer): Int {
    return suspendCoroutine { continuation ->
        this.read(buffer, continuation, ReadCompletionHandler)
    }
}

object ReadCompletionHandler : CompletionHandler<Int, Continuation<Int>> {
    override fun completed(result: Int, attachment: Continuation<Int>) {
        attachment.resume(result)
    }

    override fun failed(exc: Throwable, attachment: Continuation<Int>) {
        attachment.resumeWithException(exc)
    }
}

suspend fun sendMessage(msg: String, client:AsynchronousSocketChannel) {
    println(msg)
    val byteMsg: ByteArray = msg.toByteArray()
    val buffer = ByteBuffer.wrap(byteMsg)
    client.asyncWrite(buffer)
    buffer.clear()
}
suspend fun readMessage(client:AsynchronousSocketChannel): String {
    val buffer: ByteBuffer = ByteBuffer.allocate(128)
    client.asyncRead(buffer)
    val echo = String(buffer.array()).trim { it <= ' ' }
    return echo
}