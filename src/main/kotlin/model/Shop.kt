package model

import ShopStorage
import org.bson.types.ObjectId
import rx.Observable

object Shop {
    fun addUser(name: String, currency: String): Observable<String> {
        val id = ObjectId().toString()
        return ShopStorage.addUser(User(id, name, Currency.valueOf(currency)).toDocument()).map { id }
    }

    fun getUser(id: String): Observable<String> {
        return ShopStorage.getUser(id).map { it.toString() }
    }

    fun addGoods(name: String, price: Double): Observable<String> {
        val id = ObjectId().toString()
        return ShopStorage.addGoods(Goods(id, name, price).toDocument()).map { id }
    }

    fun getGoods(userId: String): Observable<String> {
        return ShopStorage.getUser(userId)
            .flatMap { user -> ShopStorage.getGoods().map { it.convert(user.currency) } }
    }
}
