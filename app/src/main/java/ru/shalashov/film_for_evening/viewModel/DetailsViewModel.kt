package ru.shalashov.film_for_evening.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.shalashov.film_for_evening.model.FilmDTO
import ru.shalashov.film_for_evening.repository.DetailsRepository
import ru.shalashov.film_for_evening.repository.DetailsRepositoryImpl
import ru.shalashov.film_for_evening.repository.RemoteDataSource
import ru.shalashov.film_for_evening.utils.convertDtoToModel

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
): ViewModel() {

    fun getFilmFromRemoteSource(id: Int, api_key: String, language: String) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getFilmDetailsFromServer(id, api_key, language, callback)
    }

    private val callback = object :
        Callback<FilmDTO> {
        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message?: REQUEST_ERROR)))
        }
        private fun checkResponse(serverResponse: FilmDTO): AppState {
            return if (serverResponse.overview.isNullOrEmpty()) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(serverResponse))
            }
        }
    }
}