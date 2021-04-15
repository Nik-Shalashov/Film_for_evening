package ru.shalashov.film_for_evening.repository

import ru.shalashov.film_for_evening.model.FilmDTO

interface DetailsRepository {
    fun getFilmDetailsFromServer(
        id: Int,
        api_key: String,
        language: String,
        callback: retrofit2.Callback<FilmDTO>
    )
}