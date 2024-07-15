package com.upao.travel_trux.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.requestModel.LoginRequest

class MainActivity : AppCompatActivity() {

    private val userController = UserController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) // light causes internally enforce the navigation bar to be fully transparent
        )
        setContentView(R.layout.activity_main)

        val getUser = SharedPreferencesManager.getUserData(this)
        if (getUser != null) {
            val user = getUser.split(",")
            val userLogin = LoginRequest(user[0], user[1])
            userController.login(this, userLogin) { isSuccess ->
                if (isSuccess) {
                    val intent = Intent(this, MenuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        }

        val loginButton : Button = findViewById(R.id.btnSignIn)
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val user = LoginRequest(email, password)
            println(user)
            userController.login(this, user) { isSuccess ->
                if (isSuccess) {
                    val intent = Intent(this, MenuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        }

        val createAccount : TextView = findViewById(R.id.tvRegister)
        createAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}