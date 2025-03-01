package com.plcoding.bookpedia.core.domain

sealed interface IResult<out D, out E: IError> {
    data class Success<out D>(val data: D): IResult<D, Nothing>
    data class Error<out E: com.plcoding.bookpedia.core.domain.IError>(val error: E):
        IResult<Nothing, E>
}

inline fun <T, E: IError, R> IResult<T, E>.map(map: (T) -> R): IResult<R, E> {
    return when(this) {
        is IResult.Error -> IResult.Error(error)
        is IResult.Success -> IResult.Success(map(data))
    }
}

fun <T, E: IError> IResult<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

inline fun <T, E: IError> IResult<T, E>.onSuccess(action: (T) -> Unit): IResult<T, E> {
    return when(this) {
        is IResult.Error -> this
        is IResult.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: IError> IResult<T, E>.onError(action: (E) -> Unit): IResult<T, E> {
    return when(this) {
        is IResult.Error -> {
            action(error)
            this
        }
        is IResult.Success -> this
    }
}

typealias EmptyResult<E> = IResult<Unit, E>