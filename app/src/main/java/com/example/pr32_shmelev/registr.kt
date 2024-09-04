package com.example.pr32_shmelev

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class registr : AppCompatActivity() {

    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var button: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registr)

        login = findViewById(R.id.input_login)
        password = findViewById(R.id.input_password)
        button = findViewById(R.id.butonns)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        button.setOnClickListener {
            val enlog = login.text.toString()
            val enpas = password.text.toString()

            if (enlog.isNullOrEmpty() || enpas.isNullOrEmpty()) {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Ошибка")
                    .setMessage("Введите логин и пароль")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            } else {
                val savedLogin = sharedPreferences.getString("login", null)
                val savedPassword = sharedPreferences.getString("password", null)

                if (savedLogin != null && savedPassword != null) {
                    if (enlog == savedLogin && enpas == savedPassword) {
                        val intent = Intent(this, Credit::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Неверный логин и пароль", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (enlog == "ects" && enpas == "ects2023") {
                        val editor = sharedPreferences.edit()
                        editor.putString("login", enlog)
                        editor.putString("password", enpas)
                        editor.apply()

                        val intent = Intent(this, Credit::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Неверный логин и пароль", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
