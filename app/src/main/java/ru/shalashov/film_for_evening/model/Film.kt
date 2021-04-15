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
        val rating: Float = 0F,
        val id: Int = 0
) : Parcelable

fun getAllFilms() = listOf(
    Film("Spudi Man", "Fantasy", "USA", 120, "Friendly neighborhood", 5F, 28),
    Film("Mimino", "Comedy", "USSR", 90, "Chito-drito-margarito", 6F,29),
    Film("Sinister", "Horror", "USA", 125, "Let's see the films!", 7F,30),
    Film("Shrek", "Cartoon", "USA", 121, "Somebody was told me...", 8F,31),
    Film("Sherlock Holms", "Detective", "England", 100, "Elementary, Watson!", 9F,32),
    Film("7", "Thriller", "Canada", 130, "7 sins", 7F,33),
    Film("Leon", "Drama", "France", 110, "Don't kill women and kids", 4F,34),
    Film("Breaking Bad's", "Serial", "USA", 99999, "Science, bitch!",5F,35),
    Film("News on 1 chanel", "Fairy tails", "Russia", 99999999, "All is good, god with us", 8F,36),
    Film("The Butterfly Effect", "Psychology", "USA", 113, "WTF?", 10F,37),
)

