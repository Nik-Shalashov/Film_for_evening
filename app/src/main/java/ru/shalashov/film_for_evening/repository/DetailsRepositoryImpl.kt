package ru.shalashov.film_for_evening.repository

import retrofit2.Callback
import ru.shalashov.film_for_evening.model.FilmDTO

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource): DetailsRepository {
    override fun getFilmDetailsFromServer(
        id: Int,
        api_key: String,
        language: String,
        callback: Callback<FilmDTO>
    ) {
        remoteDataSource.getFilmDetails(id, api_key, language, callback)
    }
}