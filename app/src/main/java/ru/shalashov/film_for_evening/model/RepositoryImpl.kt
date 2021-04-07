package ru.shalashov.film_for_evening.model

class RepositoryImpl : Repository {

    override fun getFilmFromServer() = Film("film", "comedy", "USA", 90, "blabla", 15)

    override fun getAllFilmsFromServer() = getAllFilms()
}