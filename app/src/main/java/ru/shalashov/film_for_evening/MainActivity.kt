package ru.shalashov.film_for_evening

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    @SuppressLint("WrongConstant")
    fun test() {
        val button: Button = findViewById(R.id.test_button)
        button.setOnClickListener {
            Toast.makeText(this, "test", 2).show()
        }
    }

}