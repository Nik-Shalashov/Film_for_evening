package ru.shalashov.film_for_evening.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shalashov.film_for_evening.model.Repository
import ru.shalashov.film_for_evening.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel (private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(), private val repositoryImpl: Repository = RepositoryImpl()) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getFilms() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getAllFilmsFromServer()))
        }.start()
    }
}