package com.plcoding.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface IFavoriteBookDao {
    
    @Upsert
    /** Add or update a book */
    suspend fun upsert(book: BookEntity)

    // Flow allows you to observe the data as it changes, a Flow is already a suspend function
    @Query("SELECT * FROM BookEntity")
            /** Get all favorite books */
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    /** Get the favorite book */
    suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :id")
    /** Delete the favorite book */
    suspend fun deleteFavoriteBook(id: String)
}