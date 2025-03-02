package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.network.IRemoteBookDataSource
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.IBookRepository
import com.plcoding.bookpedia.core.domain.IDataError
import com.plcoding.bookpedia.core.domain.IResult
import com.plcoding.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: IRemoteBookDataSource,
//    private val favoriteBookDao: FavoriteBookDao
) : IBookRepository {
    override suspend fun searchBooks(query: String): IResult<List<Book>, IDataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

//    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
//        val localResult = favoriteBookDao.getFavoriteBook(bookId)
//
//        return if (localResult == null) {
//            remoteBookDataSource
//                .getBookDetails(bookId)
//                .map { it.description }
//        } else {
//            Result.Success(localResult.description)
//        }
//    }
//
//    override fun getFavoriteBooks(): Flow<List<Book>> {
//        return favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.map { it.toBook() }
//            }
//    }
//
//    override fun isBookFavorite(id: String): Flow<Boolean> {
//        return favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.any { it.id == id }
//            }
//    }
//
//    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
//        return try {
//            favoriteBookDao.upsert(book.toBookEntity())
//            Result.Success(Unit)
//        } catch (e: SQLiteException) {
//            Result.Error(DataError.Local.DISK_FULL)
//        }
//    }
//
//    override suspend fun deleteFromFavorites(id: String) {
//        favoriteBookDao.deleteFavoriteBook(id)
//    }
}