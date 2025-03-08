package com.plcoding.bookpedia.book.presentation.book_detail

import com.plcoding.bookpedia.book.domain.Book

data class BookDetailState(
    /** @property book book */
    val book: Book? = null,

    /** @property isLoading Indicates that the screen is loading */
    val isLoading: Boolean = true,

    /** @property isFavorite Indicates that the book is in favorites */
    val isFavorite: Boolean = false,
)