package com.example.pr32_shmelev

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class Credit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        val slider = findViewById<Slider>(R.id.slider)
        val sliderValue = findViewById<TextView>(R.id.sliderValue)

        slider.addOnChangeListener { slider, value, fromUser ->
            sliderValue.text = value.toInt().toString()
        }
    }
}
