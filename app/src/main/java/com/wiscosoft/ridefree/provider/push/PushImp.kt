package com.wiscosoft.ridefree.provider.push

import android.os.Handler
import android.os.HandlerThread
import com.google.gson.Gson
import com.wiscosoft.ridefree.core.Constants
import com.wiscosoft.ridefree.core.log
import okhttp3.OkHttpClient
import java.net.Socket

class PushImp(private val client: OkHttpClient, private val gson: Gson) : Push {

  private lateinit var thread: HandlerThread
  private lateinit var handler: Handler
  private lateinit var socket: Socket

  override fun setup() {
    thread = HandlerThread(javaClass.name).apply { start() }
    handler = Handler(thread.looper)
  }

  override fun openSocket() {
    handler.post {
      client.sslSocketFactory().createSocket()
      socket = client.sslSocketFactory().createSocket(Constants.PushUrl, Constants.PushPort)
      log("Opened Socket")
    }
  }

  override fun writeToSocket(text: String) {
    handler.post {
      val buffer = text.toByteArray()
      socket.getOutputStream().write(buffer, 0, buffer.size)
      log("Wrote To Socket: $text")
    }
  }

  override fun readFromSocket(callback: (Event) -> Unit) {
    handler.post {
      val buffer = ByteArray(1024)
      val size = socket.getInputStream().read(buffer, 0, 1024)
      val data = buffer.copyOfRange(0, size).toString()
      callback(gson.fromJson(data, Event::class.java))
      log("Read From Socket $data")
    }
  }

  override fun closeSocket() {
    handler.post {
      socket.close()
      thread.quitSafely()
      log("Closed Socket")
    }
  }
}