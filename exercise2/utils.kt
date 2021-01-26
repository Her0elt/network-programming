package exercise2

fun data(a: ByteArray): StringBuilder {
    val ret = StringBuilder()
    for (element in a){
        ret.append(element.toChar())
    }
    return ret
}

fun calc(array: Array<String>): String {
    if (array.size != 3) return "Wrong format"
    when(array[1]){
        "+" -> return (array[0].toDouble() + array[2].toDouble()).toString()
        "-" -> return (array[0].toDouble() - array[2].toDouble()).toString()
        "*" -> return (array[0].toDouble() * array[2].toDouble()).toString()
        "/" -> return (array[0].toDouble() / array[2].toDouble()).toString()
    }
    return if (array[1] == "/" && array[2] == "0") "You can't divide by zero" else "Wrong format"
}