package ru.shalashov.film_for_evening.model

class RepositoryImpl: Repository {
    override fun getFilmFromServer(): Film {
        return Film("film", "comedy", "USA", 90, "blabla")
    }

    override fun getAllFilmsFromServer(): List<Film> {
        return getAllFilms()
    }
}