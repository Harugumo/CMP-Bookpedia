package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface IRoute {
    @Serializable
    data object BookGraph : IRoute

    @Serializable
    data object BookList : IRoute

    @Serializable
    data class BookDetail(val bookId: String) : IRoute
}