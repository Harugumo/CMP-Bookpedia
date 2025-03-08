package com.plcoding.bookpedia.book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookEntity::class],
    version = 1 // database version, Tells to Room if the database has changed.
)
@TypeConverters(StringListTypeConverter::class)
abstract class FavoriteBookDatabase : RoomDatabase() {
    abstract val favoriteBookDao: IFavoriteBookDao

    companion object {
        const val DB_NAME = "books.db"
    }
}