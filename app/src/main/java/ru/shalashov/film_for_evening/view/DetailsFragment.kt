package ru.shalashov.film_for_evening.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.shalashov.film_for_evening.databinding.FragmentDetailsBinding
import ru.shalashov.film_for_evening.model.Film

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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
        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { it -> setData(it) }
        /*val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        if (film != null) {
            setData(film)
        }*/
        
    }

    private fun setData(film: Film) {
        binding.tvFragDetailsFilmName.text = film.film
        binding.tvFragDetailsGenreValue.text = film.genre
        binding.tvFragDetailsCountryValue.text = film.country
        binding.tvFragDetailsDurationValue.text = film.duration.toString()
        binding.tvFragDetailsDescriptionValue.text = film.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}