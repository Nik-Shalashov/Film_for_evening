package ru.shalashov.film_for_evening.model

data class FilmDTO(
    val id: Int,
    val title: String,
    val genres: Map<Int, String>,
    val overview: String?,
    val runtime: Int?,
    val production_countries: List<String>,
    val vote_average: Float?
)
