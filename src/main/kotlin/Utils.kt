import java.text.SimpleDateFormat
import java.util.Date


const val GREETING =
    "Добро пожаловать! Я Ваш ассистент ответственного хранения наиценнейших мыслей :0) \nДля начала предлагаю создать свой \"Колумбарий одиноких идей\"\n(шутка)"
const val AQUA_BOLD = "\u001b[36;1m"
const val RED_ITALICS = "\u001b[31;3m"

fun getCurrentDateAndTime(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    return sdf.format(Date())
}

fun printlnWithDecoration(
    message: String,
    parameter: String
) {
    printlnWithColor("\n${("-*").repeat(40)}-", parameter)
    println(message)
    printlnWithColor("${("-*").repeat(40)}-\n", parameter)
}

fun printlnWithColor(
    message: String,
    fontParameters: String
) {
    println("$fontParameters$message\u001b[0m")
}