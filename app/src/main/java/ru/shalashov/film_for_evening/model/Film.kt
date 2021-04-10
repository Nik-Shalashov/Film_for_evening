package ru.shalashov.film_for_evening.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val film: String,
    val genre: String,
    val country: String,
    val duration: Int,
    val description: String
): Parcelable

fun getAllFilms(): List<Film> {
    return listOf(
        Film("Spudi Man", "Fantasy", "USA", 120, "Friendly neighborhood"),
        Film("Mimino", "Comedy", "USSR", 90, "Chito-drito-margarito"),
        Film("Sinister", "Horror", "USA", 125, "Let's see the films!"),
        Film("Shrek", "Cartoon", "USA", 121, "Somebody was told me..."),
        Film("Sherlock Holms", "Detective", "England", 100, "Elementary, Watson!"),
        Film("7", "Thriller", "Canada", 130, "7 sins"),
        Film("Leon", "Drama", "France", 110, "Don't kill women and kids"),
        Film("Breaking Bad's", "Serial", "USA", 99999, "Science, bitch!"),
        Film("News on 1 chanel", "Fairy tails", "Russia", 99999999, "All is good, god with us"),
        Film("The Butterfly Effect", "Psychology", "USA", 113, "WTF?"),
    )
}
