package com.example.pr32_shmelev

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class rashet : AppCompatActivity() {

    private lateinit var text1:TextView
    private lateinit var text2:TextView
    private lateinit var text3:TextView
    private lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rashet)

        val credit = intent.getStringExtra("ключ_1")
        val srokk = intent.getStringExtra("ключ_2")
        val resultatik = intent.getStringExtra("ключ_3")

        text1 = findViewById(R.id.summacred)
        text2 = findViewById(R.id.srokcredit)
        text3 = findViewById(R.id.sum)
        button = findViewById(R.id.buton2)

        text1.text = credit
        text2.text = srokk
        text3.text = resultatik

        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("login")
            editor.remove("password")
            editor.apply()

            val intent = Intent(this, registr::class.java)
            startActivity(intent)
        }
    }
}