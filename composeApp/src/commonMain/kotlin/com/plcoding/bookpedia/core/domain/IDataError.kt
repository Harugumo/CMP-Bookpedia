package com.plcoding.bookpedia.core.domain

sealed interface IDataError : IError {
    enum class Remote : IDataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : IDataError {
        DISK_FULL,
        UNKNOWN
    }
}