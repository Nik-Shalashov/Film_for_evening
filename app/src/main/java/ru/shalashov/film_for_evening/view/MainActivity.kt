package ru.shalashov.film_for_evening.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.shalashov.film_for_evening.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}