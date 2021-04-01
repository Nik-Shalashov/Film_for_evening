package ru.shalashov.film_for_evening.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.shalashov.film_for_evening.R
import ru.shalashov.film_for_evening.model.Film
import ru.shalashov.film_for_evening.view.MainFragment

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?):
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var filmData: List<Film> = listOf()

    fun setFilms(data: List<Film>) {
        filmData = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(filmData[position])
    }

    override fun getItemCount(): Int {
        return filmData.size
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(film: Film) {
            itemView.findViewById<TextView>(R.id.tv_item_film_name).text = film.film
            itemView.findViewById<TextView>(R.id.tv_item_genre).text = film.genre
            itemView.setOnClickListener{
                onItemViewClickListener?.onItemViewClick(film)
            }
        }
    }

}
