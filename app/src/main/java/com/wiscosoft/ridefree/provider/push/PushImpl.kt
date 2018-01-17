package com.wiscosoft.ridefree.provider.push

import android.os.Handler
import android.os.HandlerThread
import com.google.gson.Gson
import com.wiscosoft.ridefree.core.app.Constants
import com.wiscosoft.ridefree.core.app.debugLog
import okhttp3.OkHttpClient
import java.net.Socket

class PushImpl(client: OkHttpClient, gson: Gson) : Push {

  private lateinit var thread: HandlerThread
  private lateinit var handler: Handler
  private lateinit var socket: Socket

  override val setup: () -> Unit = {
    thread = HandlerThread(javaClass.name).apply { start() }
    handler = Handler(thread.looper)
  }

  override val openSocket: () -> Unit = {
    handler.post {
      client.sslSocketFactory().createSocket()
      socket = client.sslSocketFactory().createSocket(Constants.PushUrl, Constants.PushPort)
      debugLog("Opened Socket")
    }
  }

  override val writeToSocket: (String) -> Unit = {
    handler.post {
      val buffer = it.toByteArray()
      socket.getOutputStream().write(buffer, 0, buffer.size)
      debugLog("Wrote To Socket: $it")
    }
  }

  override val readFromSocket: ((Event) -> Unit) -> Unit = {
    handler.post {
      val buffer = ByteArray(1024)
      val size = socket.getInputStream().read(buffer, 0, 1024)
      val data = buffer.copyOfRange(0, size).toString()
      it(gson.fromJson(data, Event::class.java))
      debugLog("Read From Socket $data")
    }
  }

  override val closeSocket: () -> Unit = {
    handler.post {
      socket.close()
      thread.quit()
      debugLog("Closed Socket")
    }
  }
}