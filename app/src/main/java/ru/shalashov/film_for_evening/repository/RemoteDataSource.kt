package ru.shalashov.film_for_evening.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.shalashov.film_for_evening.model.FilmDTO

private const val YOUR_API_KEY = "14d8ed808b564c91f81edffb7d01a16e"

class RemoteDataSource {

    private val filmAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun getFilmDetails(id: Int, api_key: String, language: String, callback: Callback<FilmDTO>) {
        filmAPI.getFilm(id, api_key, language).enqueue(callback)
    }
}