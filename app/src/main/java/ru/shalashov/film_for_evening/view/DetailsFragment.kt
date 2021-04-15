package ru.shalashov.film_for_evening.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.shalashov.film_for_evening.R
import ru.shalashov.film_for_evening.databinding.FragmentDetailsBinding
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.model.FilmDTO
import ru.shalashov.film_for_evening.viewModel.AppState
import ru.shalashov.film_for_evening.viewModel.DetailsViewModel

private const val YOUR_API_KEY = "14d8ed808b564c91f81edffb7d01a16e"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmBundle: Film
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }

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
        filmBundle = arguments?.getParcelable(BUNDLE_EXTRA)?: Film()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getFilmFromRemoteSource(filmBundle.id, YOUR_API_KEY, "ru")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.detailsView.visibility = View.VISIBLE
                binding.FLFragMainLoading.visibility = View.GONE
                displayFilm(appState.filmData[0])
            }
            is AppState.Loading -> {
                binding.detailsView.visibility = View.GONE
                binding.FLFragMainLoading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.detailsView.visibility = View.VISIBLE
                binding.FLFragMainLoading.visibility = View.GONE
                binding.detailsView.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload)
                ) {
                    viewModel.getFilmFromRemoteSource(
                            filmBundle.id,
                            YOUR_API_KEY,
                            "ru"
                    )
                }
            }
        }
    }

    private fun displayFilm(filmDTO: Film) {
        with(binding) {
            detailsView.visibility = View.VISIBLE
            FLFragMainLoading.visibility = View.GONE
            tvFragDetailsFilmName.text = filmDTO.film
            tvFragDetailsGenreValue.text = filmDTO.genre
            tvFragDetailsCountryValue.text = filmDTO.country
            tvFragDetailsDurationValue.text = filmDTO.duration.toString()
            tvFragDetailsDescriptionValue.text = filmDTO.description
            tvFragDetailsRatingValue.text = filmDTO.rating.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun ConstraintLayout.showSnackBar(string: String, string1: String, function: () -> Unit) {

}
