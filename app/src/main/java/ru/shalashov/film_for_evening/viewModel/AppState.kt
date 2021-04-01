package ru.shalashov.film_for_evening.viewModel

import ru.shalashov.film_for_evening.model.Film

sealed class AppState {
    data class Success(val filmData: List<Film>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
