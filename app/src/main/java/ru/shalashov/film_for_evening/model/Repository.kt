package ru.shalashov.film_for_evening.model

interface Repository {
    fun getFilmFromServer(): Film
}