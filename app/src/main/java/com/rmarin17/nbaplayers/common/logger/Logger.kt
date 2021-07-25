package com.rmarin17.nbaplayers.common.logger

/**
 * Interface for manage the functions of logger.
 */
interface Logger {

    fun logMessage(
        tag: String,
        message: String,
        level: Level = Level.DEBUG
    )

    enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        ASSERT
    }
}
