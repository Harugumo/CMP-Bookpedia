package com.plcoding.bookpedia.book.domain

data class Book(
    /** @property id  id of the book */
    val id: String,

    /** @property title book s' title */
    val title: String,

    /** @property imageUrl url of book's cover */
    val imageUrl: String,

    /** @property authors book authors */
    val authors: List<String>,

    /** @property description book's description */
    val description: String?,

    /** @property languages book's languages */
    val languages: List<String>,

    /** @property firstPublishYear first publish date of the book */
    val firstPublishYear: String?,

    /** @property averageRating rating of the book */
    val averageRating: Double?,

    /** @property ratingCount rating count of the book */
    val ratingCount: Int?,

    /** @property numEdition book's edition number */
    val numEdition: Int
)
