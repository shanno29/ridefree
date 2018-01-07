package com.wiscosoft.ridefree.provider.redux

import android.util.Log
import redux.api.enhancer.Middleware
import redux.logger.Logger
import redux.logger.createLoggerMiddleware

object Middlewares {

  val logger: Middleware<State> = createLoggerMiddleware(Logger { Log.d("Redux", it.toString()) })

}
