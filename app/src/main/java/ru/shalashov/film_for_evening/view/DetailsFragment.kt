package ru.shalashov.film_for_evening.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.shalashov.film_for_evening.R
import ru.shalashov.film_for_evening.databinding.FragmentDetailsBinding
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.utils.showSnackBar
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
        filmBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Film()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, { renderData(it) })
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
                        getString(R.string.reload),
                {
                    viewModel.getFilmFromRemoteSource(
                            filmBundle.id,
                            YOUR_API_KEY,
                            "ru"
                    )
                })
            }
        }
    }

    private fun displayFilm(film: Film) {
        with(binding) {
            detailsView.visibility = View.VISIBLE
            FLFragMainLoading.visibility = View.GONE
            tvFragDetailsFilmName.text = film.film
            tvFragDetailsGenreValue.text = film.genre
            tvFragDetailsCountryValue.text = film.country
            tvFragDetailsDurationValue.text = film.duration.toString()
            tvFragDetailsDescriptionValue.text = film.description
            tvFragDetailsRatingValue.text = film.rating.toString()
            Picasso
                    .get()
                    .load(film.poster_path)
                    .into(ivFragDetailsPoster)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
