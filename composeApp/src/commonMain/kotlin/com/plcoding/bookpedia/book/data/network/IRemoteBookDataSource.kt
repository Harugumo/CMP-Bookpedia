package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.SearchResponseDto
import com.plcoding.bookpedia.core.domain.IDataError
import com.plcoding.bookpedia.core.domain.IResult

interface IRemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): IResult<SearchResponseDto, IDataError.Remote>

//    suspend fun getBookDetails(bookWorkId: String): IResult<BookWorkDto, IDataError.Remote>
}