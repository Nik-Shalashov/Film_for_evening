package ru.shalashov.film_for_evening.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class Film(
    val film: String?,
    val genre: String?,
    val country: String?,
    val duration: Int,
    val description: String?,
    val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    companion object : Parceler<Film> {

        override fun Film.write(parcel: Parcel, flags: Int) {
            parcel.writeString(film)
            parcel.writeString(genre)
            parcel.writeString(country)
            parcel.writeInt(duration)
            parcel.writeString(description)
        }

        override fun create(parcel: Parcel): Film {
            return Film(parcel)
        }
    }
}

fun getAllFilms() = listOf(
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

