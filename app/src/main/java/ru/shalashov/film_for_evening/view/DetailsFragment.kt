package ru.shalashov.film_for_evening.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import ru.shalashov.film_for_evening.databinding.FragmentDetailsBinding
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.model.FilmDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val YOUR_API_KEY = "14d8ed808b564c91f81edffb7d01a16e"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmBundle: Film

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { it -> setData(it) }
    }

    private fun setData(film: Film) {
        binding.tvFragDetailsFilmName.text = film.film
        binding.tvFragDetailsGenreValue.text = film.genre
        binding.tvFragDetailsCountryValue.text = film.country
        binding.tvFragDetailsDurationValue.text = film.duration.toString()
        binding.tvFragDetailsDescriptionValue.text = film.description
    }

    private fun displayFilm(filmDTO: FilmDTO) {
        with(binding) {
            detailsView.visibility = View.VISIBLE
            FLFragMainLoading.visibility = View.GONE
            tvFragDetailsFilmName.text = filmDTO.title
            tvFragDetailsGenreValue.text = filmDTO.genres.toString()
            tvFragDetailsCountryValue.text = filmDTO.production_countries.toString()
            tvFragDetailsDurationValue.text = filmDTO.runtime.toString()
            tvFragDetailsDescriptionValue.text = filmDTO.overview
            tvFragDetailsRatingValue.text = filmDTO.vote_average.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFilm() {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/${filmBundle.id}?api_key=${YOUR_API_KEY}&language=en-US")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 20000
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val filmDTO: FilmDTO = Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}