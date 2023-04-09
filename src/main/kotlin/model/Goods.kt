package model

import org.bson.Document

class Goods(
    private val id: String?,
    private val name: String,
    private val price: Double
) : StorageItem {

    constructor(document: Document) : this(
        document["goods_id"] as String,
        document["name"] as String,
        document["price"] as Double
    )

    override fun toDocument(): Document {
        val document = Document()
        document.append("goods_id", id)
        document.append("name", name)
        document.append("price", price)
        return document
    }

    fun convert(currency: Currency): String {
        return "<body><h3><br>Товар <br>id: $id,<br>Название: $name,<br>Цена: ${
            Currency.convert(
                Currency.RUB,
                currency,
                price
            )
        }<br></h3></body>"
    }

    override fun toHTML(): String {
        return convert(Currency.RUB)
    }
}
