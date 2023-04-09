import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpResponseStatus
import io.reactivex.netty.protocol.http.server.HttpServer
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import rx.Observable

fun main() {
    HttpServer.newServer(8081).start { request, response ->
        object {
            fun process(
                request: HttpServerRequest<ByteBuf>,
                response: HttpServerResponse<ByteBuf>
            ): Observable<Void> {
                return try {
                    val param = request.queryParameters
                    val message = when (request.decodedPath) {
                        "/addUser" -> AppController.addUser(param)
                        "/getUser" -> AppController.getUser(param)
                        "/addGoods" -> AppController.addGoods(param)
                        "/getGoods" -> AppController.getGoods(param)
                        else -> throw AppController.ControllerException("unsupported")
                    }
                    response.status = HttpResponseStatus.OK
                    response.writeString(message)
                } catch (e: Exception) {
                    response.status = HttpResponseStatus.BAD_REQUEST
                    response.writeString(Observable.just(e.message))
                }
            }
        }.process(request, response)
    }.awaitShutdown()
}
