import model.Shop
import rx.Observable

object AppController {
    class ControllerException(override var message: String) : Exception(message) {
        init {
            message = "<body><h3>MESSAGE:</h3><p>$message</p></body>"
        }
    }

    fun addUser(param: Map<String, List<String>>): Observable<String> {
        return Shop.addUser(getParam(param, "name"), getParam(param, "currency"))
    }

    fun getUser(param: Map<String, List<String>>): Observable<String> {
        return Shop.getUser(getParam(param, "id"))
    }

    fun addGoods(param: Map<String, List<String>>): Observable<String> {
        return Shop.addGoods(getParam(param, "name"), getParam(param, "price").toDouble())
    }

    fun getGoods(param: Map<String, List<String>>): Observable<String> {
        return Shop.getGoods(getParam(param, "userId"))
    }

    private fun getParam(params: Map<String, List<String>>, name: String): String {
        return params[name]?.getOrNull(0) ?: throw ControllerException("parameter $name is missing")
    }
}
