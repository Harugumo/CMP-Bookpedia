package com.plcoding.bookpedia.book.presentation.book_detail

import com.plcoding.bookpedia.book.domain.Book

sealed interface IBookDetailAction {
    data object OnBackClick : IBookDetailAction
    data object OnFavoriteClick : IBookDetailAction
    data class OnSelectedBookChange(val book: Book) : IBookDetailAction
}