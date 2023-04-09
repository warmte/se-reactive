import com.mongodb.rx.client.MongoClient
import com.mongodb.rx.client.MongoClients
import com.mongodb.rx.client.MongoCollection
import com.mongodb.rx.client.MongoDatabase
import com.mongodb.rx.client.Success
import model.Goods
import model.User
import org.bson.Document
import rx.Observable

object ShopStorage {
    private val mongoClient: MongoClient = MongoClients.create("mongodb://localhost:27017")
    private val mongoDatabase: MongoDatabase = mongoClient.getDatabase("shop")

    private fun users(): MongoCollection<Document> {
        return mongoDatabase.getCollection("users")
    }

    private fun goods(): MongoCollection<Document> {
        return mongoDatabase.getCollection("goods")
    }

    fun addUser(user: Document): Observable<Success> {
        return users().insertOne(user)
    }

    fun getUser(id: String): Observable<User> {
        return users().find().toObservable().filter { it["user_id"] == (id) }.map { User(it) }
    }

    fun addGoods(goods: Document): Observable<Success> {
        return goods().insertOne(goods)
    }

    fun getGoods(): Observable<Goods> {
        return goods().find().toObservable().map { Goods(it) }
    }
}
