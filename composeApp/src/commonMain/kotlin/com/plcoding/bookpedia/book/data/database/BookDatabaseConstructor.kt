package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

// Room generate automatically the "actual" code
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<FavoriteBookDatabase> {
    
    override fun initialize(): FavoriteBookDatabase
}