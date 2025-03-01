package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book

sealed interface IBookListAction {
    data class OnSearchQueryChange(val query: String) : IBookListAction
    data class OnBookClick(val book: Book) : IBookListAction
    data class OnTabSelected(val index: Int) : IBookListAction
}