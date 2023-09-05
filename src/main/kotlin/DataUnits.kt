interface StoresData {
    val timeOfCreating: String
    val heading: String
}

enum class TypeOfUnit {
    NOTE,
    ARCHIVE,
    COMMAND
}

enum class PartOfUnit {
    HEADING,
    CONTENT
}

data class Archive(
    override val timeOfCreating: String,
    override var heading: String
) : StoresData {
    var tableOfContents: MutableMap<Int, Note> = mutableMapOf()
}

data class Note(
    override val timeOfCreating: String,
    override val heading: String,
    val content: String
) : StoresData {
    override fun toString(): String {
        return "Время создания: $timeOfCreating \nНазвание: '$heading' \nТекст: $content"
    }
}


