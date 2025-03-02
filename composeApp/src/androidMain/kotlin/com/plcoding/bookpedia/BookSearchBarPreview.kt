package com.plcoding.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(backgroundColor = 0xFFF0EAE2, showBackground = true)
@Composable
private fun BookSearchBarPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            BookSearchBar(
                searchQuery = "",
                onSearchQueryChange = { },
                onImeSearch = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview()
@Composable
private fun BookListScreenPreview(

) {
    BookListScreen(
        state = BookListState(
            searchResults = fakeBooks,
        ),
        onAction = { },
    )
}

private val fakeBooks = (1..100).map {
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
        numPages = 10,
        numEditions = 3,
    )
}