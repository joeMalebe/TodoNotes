package com.example.todo.notes

import com.example.todo.notes.app
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NO_CONTENT
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TodonotesTest {

    @Test
    fun `Ping test`() {
        assertEquals(Response(OK).body("pong"), app(Request(GET, "/ping")))
    }

    @Test
    fun `return ok when get default route`() {
       val res =  app(Request(method = GET,"/"))
        assertEquals(OK,res.status)
    }

    @Test
    fun `when post todos is successful then return no content`() {
        val res  =  app(Request(method = Method.POST,"/post"))

        assertEquals(NO_CONTENT, res.status)
    }

    @Test
    fun `when post with no todo object then return bad request`() {
        val res = app(Request(method = Method.POST, "/post"))

        assertEquals(BAD_REQUEST, res.status)
    }
}
