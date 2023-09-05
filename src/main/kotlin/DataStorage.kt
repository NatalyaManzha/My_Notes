import java.util.Scanner

object DataStorage {
    var tableOfArchives: MutableMap<Int, Archive> = mutableMapOf()
    var currentArchiveNumber: Int? = null


    fun createArchive() {
        val heading = getTextIn(TypeOfUnit.ARCHIVE, PartOfUnit.HEADING)
        val timeOfCreating = getCurrentDateAndTime()
        println(
            "Архив '$heading'  добавлен в Хранилище " +
                    timeOfCreating.substring(1, timeOfCreating.length - 9) +
                    " в " +
                    timeOfCreating.substring(timeOfCreating.length - 8, timeOfCreating.length)
        )
        tableOfArchives[tableOfArchives.size + 1] = Archive(timeOfCreating, heading)
        return
    }

    fun <T : StoresData> showMyDataUnits(typeOfUnit: TypeOfUnit, tableOfUnits: MutableMap<Int, T>) {
        println(
            if (typeOfUnit == TypeOfUnit.NOTE) "Архив '${tableOfArchives[currentArchiveNumber]!!.heading}'\nЗаметки:"
            else "Хранилище содержит следующие архивы:"
        )
        tableOfUnits.forEach { (key, value) ->
            println("№ $key. (${value.timeOfCreating}) '${value.heading}' ")
        }
    }

    fun createNote(currentArchiveNumber: Int) {
        val heading = getTextIn(TypeOfUnit.NOTE, PartOfUnit.HEADING)
        val content = getTextIn(TypeOfUnit.NOTE, PartOfUnit.CONTENT)
        val timeOfCreating = getCurrentDateAndTime()
        tableOfArchives[currentArchiveNumber]!!.tableOfContents[
            tableOfArchives[currentArchiveNumber]!!.tableOfContents.size + 1
        ] = Note(timeOfCreating, heading, content)
        println(
            "Заметка '$heading' добавлена в архив '${tableOfArchives[currentArchiveNumber]!!.heading}' " +
                    timeOfCreating.substring(1, timeOfCreating.length - 9) +
                    " в " +
                    timeOfCreating.substring(timeOfCreating.length - 8, timeOfCreating.length)
        )
    }

    private fun getTextIn(typeOfUnit: TypeOfUnit, partOfUnit: PartOfUnit): String {
        var text: String
        do {
            println(
                "Введите ${
                    if (partOfUnit == PartOfUnit.HEADING) "название" else "текст"
                } ${
                    if (typeOfUnit == TypeOfUnit.NOTE) "заметки" else "архива"
                }"
            )
            text = Scanner(System.`in`).nextLine()
        } while (text == "")
        return text
    }

    fun readNote() {
        val numberOfNote = if (tableOfArchives[currentArchiveNumber]!!.tableOfContents.size == 1) 1
        else getNumberOfUnit(
            TypeOfUnit.NOTE,
            tableOfArchives[currentArchiveNumber]!!.tableOfContents
        )
        println("${tableOfArchives[currentArchiveNumber]!!.tableOfContents[numberOfNote]}")
        return
    }

    fun <T> getNumberOfUnit(
        typeOfUnit: TypeOfUnit,
        map: Map<Int, T>
    ): Int { //получить команду
        inputLoop@ while (true) { // не пройдя проверку на любом из этапов, сообщаем об ошибке и запрашиваем ввод заново
            println(
                "Введите ${
                    when (typeOfUnit) {
                        TypeOfUnit.NOTE -> "номер заметки"
                        TypeOfUnit.ARCHIVE -> "номер архива"
                        else -> "команду (одной цифрой)"
                    }
                }"
            )
            val temp: String = Scanner(System.`in`).nextLine()

            if (temp.isEmpty()) { //проверка всех возможных ошибок ввода команды: 1) ничего не введено
                printlnWithColor("Странно, но я не вижу команду... Попробуем еще раз", RED_ITALICS)
                continue@inputLoop
            }
            var numberOfNotDigits = 0 //определим, есть ли во вводе НЕцифры
            temp.toCharArray().forEach {
                if (!it.isDigit()) {
                    numberOfNotDigits++
                }
            }
            if (numberOfNotDigits > 0) { // 2) введены не цифры
                printlnWithColor(
                    "Почти правильно. Не буквы, а цифры, пожалуйста) Попробуем еще раз",
                    RED_ITALICS
                )
                continue@inputLoop
            }
            if (temp.toInt() !in map.keys) { // 3) число выходит за рамки количества заданных вариантов
                printlnWithColor(
                    "Цифры - да, но не те. Нет ${
                        when (typeOfUnit) {
                            TypeOfUnit.NOTE -> "такой заметки."
                            TypeOfUnit.ARCHIVE -> "такого архива."
                            else -> "такой команды."
                        }
                    } Попробуем еще раз. Внимательнее", RED_ITALICS
                )
                continue@inputLoop
            } else return temp.toInt()  //в итоге после всех проверок (во избежание null ошибок) получаем номер типа Int
        }
    }
}


