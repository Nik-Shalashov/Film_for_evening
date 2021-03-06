package ru.shalashov.film_for_evening.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import ru.shalashov.film_for_evening.R
import ru.shalashov.film_for_evening.adapter.MainFragmentAdapter
import ru.shalashov.film_for_evening.databinding.FragmentMainBinding
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.viewModel.AppState
import ru.shalashov.film_for_evening.viewModel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            activity?.supportFragmentManager?.apply {
                beginTransaction().add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                    putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
                }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMainFragHorrors.adapter = adapter
        binding.rvMainFragCartoons.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getFilms()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.FLFragDetailsLoading.visibility = View.GONE
                adapter.setFilms(appState.filmData)
            }
            is AppState.Loading -> {
                binding.FLFragDetailsLoading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.FLFragDetailsLoading.visibility = View.GONE
                mainView.showSnackBar(getString(R.string.error), getString(R.string.reload), {viewModel.getFilms()})
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }
}