package ru.shalashov.film_for_evening.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.shalashov.film_for_evening.databinding.MainFragmentBinding
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.viewModel.AppState
import ru.shalashov.film_for_evening.viewModel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getFilms()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val filmData = appState.filmData
                binding.loadingLayout.visibility = View.GONE
                setData(filmData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE).setAction("Reload") {viewModel.getFilms()}.show()
            }
        }
    }

    private fun setData(filmData: Film) {
        binding.tvMainFragFilmName.text = filmData.film
        binding.tvMainFragGenreValue.text = filmData.genre
        binding.tvMainFragCountryValue.text = filmData.country
        binding.tvMainFragDurationValue.text = filmData.duration.toString()
        binding.tvMainFragDescriptionValue.text = filmData.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}