package ru.shalashov.film_for_evening.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val film: String = "",
    val genre: String = "",
    val country: String = "",
    val duration: Int = 0,
    val description: String = "",
    val id: Int = 0
) : Parcelable

fun getAllFilms() = listOf(
    Film("Spudi Man", "Fantasy", "USA", 120, "Friendly neighborhood", 28),
    Film("Mimino", "Comedy", "USSR", 90, "Chito-drito-margarito", 29),
    Film("Sinister", "Horror", "USA", 125, "Let's see the films!", 30),
    Film("Shrek", "Cartoon", "USA", 121, "Somebody was told me...", 31),
    Film("Sherlock Holms", "Detective", "England", 100, "Elementary, Watson!", 32),
    Film("7", "Thriller", "Canada", 130, "7 sins",33),
    Film("Leon", "Drama", "France", 110, "Don't kill women and kids", 34),
    Film("Breaking Bad's", "Serial", "USA", 99999, "Science, bitch!",35),
    Film("News on 1 chanel", "Fairy tails", "Russia", 99999999, "All is good, god with us", 36),
    Film("The Butterfly Effect", "Psychology", "USA", 113, "WTF?", 37),
)

