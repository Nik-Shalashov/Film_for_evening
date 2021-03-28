package ru.shalashov.film_for_evening.model

data class Film(
    val film: String,
    val genre: String,
    val country: String,
    val duration: Int,
    val description: String
)
