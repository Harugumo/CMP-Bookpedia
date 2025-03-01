package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.IUiText

data class BookListState(
    /** @property searchQuery Search query */
    val searchQuery: String = "Kotlin",

    /** @property searchResults Results from search */
    val searchResults: List<Book> = fakeBooks,

    /** @property favoriteBooks List of the favourite books */
    val favoriteBooks: List<Book> = emptyList(),

    /** @property isLoading Indicates whether the data is loading */
    val isLoading: Boolean = false,

    /** @property selectedTabIndex Index of the selected tab */
    val selectedTabIndex: Int = 0,

    /** @property errorMessage Contain the error message if there is one */
    val errorMessage: IUiText? = null
)

val fakeBooks = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Nihim"),
        description = "description",
        languages = listOf("ENG", "FR"),
        firstPublishYear = null,
        averageRating = 4.6785,
        ratingCount = 4,
        numEdition = 3,
    )
}
