package ru.shalashov.film_for_evening.repository

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.shalashov.film_for_evening.model.FilmDTO

interface FilmAPI {
    @GET("3/movie")
    fun getFilm(
        @Query("movie_id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): retrofit2.Call<FilmDTO>
}