package ru.shalashov.film_for_evening.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmBundle = arguments?.getParcelable(BUNDLE_EXTRA)?: Film()
        binding.detailsView.visibility = View.GONE
        binding.FLFragMainLoading.visibility = View.VISIBLE
        loadFilm()
    }

    private fun displayFilm(filmDTO: FilmDTO) {
        with(binding) {
            detailsView.visibility = View.VISIBLE
            FLFragMainLoading.visibility = View.GONE
            tvFragDetailsFilmName.text = filmDTO.title
            var genre: String = filmDTO.genres[0].name
            for (i in 1 until filmDTO.genres.count()) {
                genre += "\n${filmDTO.genres[i].name}"
            }
            var country: String = filmDTO.production_countries[0].name
            for (i in 1 until filmDTO.production_countries.count()) {
                country += "\n${filmDTO.production_countries[i].name}"
            }
            tvFragDetailsGenreValue.text = genre
            tvFragDetailsCountryValue.text = country
            tvFragDetailsDurationValue.text = filmDTO.runtime.toString()
            tvFragDetailsDescriptionValue.text = filmDTO.overview
            tvFragDetailsRatingValue.text = filmDTO.vote_average.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFilm() {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/${filmBundle.id}?api_key=${YOUR_API_KEY}&language=ru")
            val handler = Handler()
            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 20000
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val filmDTO: FilmDTO = Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                    handler.post { displayFilm(filmDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URL", e)
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}