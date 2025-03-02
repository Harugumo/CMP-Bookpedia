package com.plcoding.bookpedia.book.domain

import com.plcoding.bookpedia.core.domain.IDataError
import com.plcoding.bookpedia.core.domain.IResult

interface IBookRepository {
    suspend fun searchBooks(query: String): IResult<List<Book>, IDataError.Remote>
//    suspend fun getBookDescription(bookId: String): IResult<String?, IDataError>
//
//    fun getFavoriteBooks(): Flow<List<Book>>
//    fun isBookFavorite(id: String): Flow<Boolean>
//    suspend fun markAsFavorite(book: Book): EmptyResult<IDataError.Local>
//    suspend fun deleteFromFavorites(id: String)
}