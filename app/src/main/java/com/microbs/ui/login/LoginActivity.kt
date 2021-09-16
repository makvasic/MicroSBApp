package com.microbs.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.microbs.databinding.ActivityLoginBinding
import com.microbs.model.User
import com.microbs.ui.Repository
import com.microbs.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.loginLiveData.observe(this) { user ->
            if (user == null) {
                Toast.makeText(this, "No such user. Please register!", Toast.LENGTH_LONG).show()
                return@observe
            }

            Repository.userId = user.id

            MainActivity.start(this)
            finish()
        }

        loginViewModel.registerLiveData.observe(this) { userId ->
            if (userId == 0L) {
                Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show()
                return@observe
            }

            Repository.userId = userId

            MainActivity.start(this)
            finish()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
//            loginViewModel.login(username, password)
            loginViewModel.login("makvasic", "123123")
        }

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val user = User(0, username, password)
            loginViewModel.register(user)

        }


    }
}