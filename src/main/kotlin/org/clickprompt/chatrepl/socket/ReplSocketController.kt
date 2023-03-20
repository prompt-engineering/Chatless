package org.clickprompt.chatrepl.socket

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.clickprompt.chatrepl.repl.api.InterpreterRequest
import org.clickprompt.chatrepl.repl.KotlinInterpreter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

object ReplService {
    var interpreter: KotlinInterpreter = KotlinInterpreter()
}

@ServerEndpoint(value = "/api/repl")
@Controller
class ReplSocketController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var replServer: KotlinInterpreter = ReplService.interpreter

    @OnOpen
    fun onOpen(session: Session) {
        logger.info("onOpen WebSocket")
    }

    @OnClose
    fun onClose(session: Session) {
        logger.info("onClose WebSocket")
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        val request: InterpreterRequest = Json.decodeFromString(message)
        val result = replServer.eval(request)
        session.asyncRemote.sendText(Json.encodeToString(result))
    }

    @OnError
    fun onError(session: Session?, error: Throwable) {
    }
}
