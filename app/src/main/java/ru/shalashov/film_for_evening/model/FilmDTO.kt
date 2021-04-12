package ru.shalashov.film_for_evening.model

data class FilmDTO(
    val id: Int,
    val title: String,
    val genres: List<Genres>,
    val overview: String?,
    val runtime: Int?,
    val production_countries: List<Production_countries>,
    val vote_average: Float?
)

data class Genres(
    val id: Int,
    val name: String
)

data class Production_countries(
    val iso_3166_1: String,
    val name: String
)
