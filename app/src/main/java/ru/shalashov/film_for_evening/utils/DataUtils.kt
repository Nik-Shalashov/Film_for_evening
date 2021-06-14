package ru.shalashov.film_for_evening.utils

import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.model.FilmDTO

fun convertDtoToModel(filmDTO: FilmDTO): List<Film> {
    var genre: String = filmDTO.genres[0].name
    for (i in 1 until filmDTO.genres.count()) {
        genre += "\n${filmDTO.genres[i].name}"
    }
    var country: String = filmDTO.production_countries[0].name
    for (i in 1 until filmDTO.production_countries.count()) {
        country += "\n${filmDTO.production_countries[i].name}"
    }
    return listOf(Film(filmDTO.title, genre, country, filmDTO.runtime!!, filmDTO.overview!!, filmDTO.vote_average!!, filmDTO.id, filmDTO.poster_path!!))
}

//