import DataStorage.createArchive
import DataStorage.createNote
import DataStorage.currentArchiveNumber
import DataStorage.getNumberOfUnit
import DataStorage.readNote
import DataStorage.showMyDataUnits
import DataStorage.tableOfArchives
import kotlin.system.exitProcess

private val menuMain = mapOf(
    0 to "Создать архив",
    1 to "Открыть архив",
    2 to "Выйти из программы"
)
private val menuForArchives = mapOf(
    0 to "Создать заметку",
    1 to "Открыть заметку",
    2 to "Я передумал, хочу вернуться"
)
private val menuForNotes = mapOf(
    0 to "Прочитать заметку",
    1 to "Вернуться"
)

fun goToMainMenu() {
    while (true) {
        when (getCommand(menuMain)) {
            0 -> createArchive()
            1 -> {
                if (tableOfArchives.isEmpty()) printlnWithColor(
                    "А смотреть пока нечего. Сначала архив надо создать",
                    RED_ITALICS
                )
                else goToArchiveMenu()
            }

            2 -> {
                printlnWithDecoration("Как жаль, что вы наконец-то уходите XD \nПока!", AQUA_BOLD)
                exitProcess(0)
            }
        }
    }
}

private fun goToArchiveMenu() {
    showMyDataUnits(TypeOfUnit.ARCHIVE, tableOfArchives)
    currentArchiveNumber = if (tableOfArchives.size == 1) 1
    else getNumberOfUnit(TypeOfUnit.ARCHIVE, tableOfArchives)
    while (true) {
        when (getCommand(menuForArchives)) {
            0 -> createNote(currentArchiveNumber!!)
            1 -> {
                if (tableOfArchives[currentArchiveNumber]!!.tableOfContents.isEmpty()) printlnWithColor(
                    "Этот архив пуст. Заметок в нем еще нет",
                    RED_ITALICS
                )
                else goToNotesMenu()
            }

            2 -> {
                currentArchiveNumber = null
                return
            }
        }
    }
}

fun goToNotesMenu() {
    showMyDataUnits(TypeOfUnit.NOTE, tableOfArchives[currentArchiveNumber]!!.tableOfContents)
    while (true) {
        when (getCommand(menuForNotes)) {
            0 -> readNote()
            1 -> return
        }
    }
}

private fun getCommand(menu: Map<Int, String>): Int { //получить команду

    printlnWithColor("\nВозможные действия:", AQUA_BOLD)
    menu.forEach { (key, nameOfCommand) ->
        printlnWithColor("'$key' - $nameOfCommand", AQUA_BOLD)
    }
    return getNumberOfUnit(TypeOfUnit.COMMAND, menu)
}
