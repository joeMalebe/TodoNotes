package com.example.todo.notes

import com.example.todo.notes.formats.kotlinXMessage
import com.example.todo.notes.formats.kotlinXMessageLens
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.lens.Lens
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val lens = Lens<Body,Todo>

val app: HttpHandler = routes(
    "/" bind GET to {
        Response(OK)
    },
    "/ping" bind GET to {
        Response(OK).body("pong")
    },

    "/post" bind Method.POST to {
        val resBody = it.body
        Response(Status.NO_CONTENT)
    },


    "/formats/json/kotlinx" bind GET to {
        Response(OK).with(kotlinXMessageLens of kotlinXMessage)
    }
)

fun main() {
    val printingApp: HttpHandler = PrintRequest().then(app)

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}
