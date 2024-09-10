package com.example.pr32_shmelev

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Credit : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var seektext: SeekBar
    private lateinit var srok: EditText
    private lateinit var sum: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        text = findViewById(R.id.sliderValue)
        seektext = findViewById(R.id.seekbar)
        button = findViewById(R.id.buton2)
        srok = findViewById(R.id.srokcredit)
        sum = findViewById(R.id.sum)

        button.setOnClickListener {
            val srokText = srok.text.toString()

            if (proverka_na_bukvi(srokText)) {
                Toast.makeText(this, "Неверный формат срока", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val srokValue = srokText.toInt()
                    val creditSum = seektext.progress

                    val result = when {
                        srokValue < 12 -> {
                            val s1 = (creditSum / srokValue) + creditSum * 0.059
                            s1.toInt()
                        }
                        srokValue in 12..24 -> {
                            val s1 = (creditSum / srokValue) + (creditSum - (creditSum / 12)) * 0.051
                            s1.toInt()
                        }
                        else -> {
                            val s1 = (creditSum / srokValue) + (creditSum - (creditSum / srokValue)) * 0.042
                            s1.toInt()
                        }
                    }

                    sum.text = result.toString()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Неверный формат срока", Toast.LENGTH_SHORT).show()
                }
            }
        }

        seektext.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress
                text.text = value.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Не используется
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Не используется
            }
        })
    }

    fun proverka_na_bukvi(text: String): Boolean {
        return text.any { it.isLetter() }
    }
}
