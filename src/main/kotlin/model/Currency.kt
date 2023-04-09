package model

enum class Currency(val title: String, val rate: Double) {
    RUB("rub", 1.00),
    USD("usd", 80.00),
    EUR("eur", 88.00);

    companion object {
        fun convert(from: Currency, to: Currency, amount: Double): Double {
            return (from.rate / to.rate) * amount
        }
    }
}
