package com.plcoding.bookpedia.core.data

import com.plcoding.bookpedia.core.domain.IDataError
import com.plcoding.bookpedia.core.domain.IResult
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): IResult<T, IDataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return IResult.Error(IDataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return IResult.Error(IDataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        // fix to ensure that the parent coroutine is not cancelled
        coroutineContext.ensureActive()
        return IResult.Error(IDataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): IResult<T, IDataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                IResult.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                IResult.Error(IDataError.Remote.SERIALIZATION)
            }
        }

        408 -> IResult.Error(IDataError.Remote.REQUEST_TIMEOUT)
        429 -> IResult.Error(IDataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> IResult.Error(IDataError.Remote.SERVER)
        else -> IResult.Error(IDataError.Remote.UNKNOWN)
    }
}