package com.rmarin17.nbaplayers.common.logger

import android.util.Log
import javax.inject.Inject

/**
 * Implementation of Logger interface.
 */
class LoggerImpl @Inject constructor() : Logger {
    override fun logMessage(tag: String, message: String, level: Logger.Level) {
        when (level) {
            Logger.Level.VERBOSE -> Log.v(tag, message)
            Logger.Level.DEBUG -> Log.d(tag, message)
            Logger.Level.INFO -> Log.i(tag, message)
            Logger.Level.WARN -> Log.w(tag, message)
            Logger.Level.ERROR -> Log.e(tag, message)
            Logger.Level.ASSERT -> Log.wtf(tag, message)
        }
    }
}
