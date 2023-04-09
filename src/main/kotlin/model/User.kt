package model

import org.bson.Document

class User(
    private val id: String?,
    private val name: String,
    val currency: Currency
) : StorageItem {

    constructor(document: Document) : this(
        document["user_id"] as String,
        document["name"] as String,
        Currency.valueOf(document["currency"] as String)
    )

    override fun toDocument(): Document {
        val document = Document()
        document.append("user_id", id)
        document.append("name", name)
        document.append("currency", currency.title)
        return document
    }

    override fun toHTML(): String {
        return "<body><h3><br>Пользователь <br>id: $id,<br>Имя: $name,<br>Валюта: ${currency.title}<br></h3></body>"
    }
}
