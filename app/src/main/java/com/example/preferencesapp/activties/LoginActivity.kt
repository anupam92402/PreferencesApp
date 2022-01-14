package com.example.preferencesapp.activties

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.preferencesapp.databinding.ActivityLoginBinding
import com.example.preferencesapp.utils.Validation.Companion.isValidEmail
import com.example.preferencesapp.utils.Validation.Companion.isValidPassword

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding//view binding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.btnLogin.setOnClickListener {
            val email = binding.TVEmail.text.toString()//email entered by user
            val password = binding.TVPassword.text.toString()//password entered by user
            //check credential entered by user
            val isEmailValid = isValidEmail(email)
            val isPasswordValid = isValidPassword(password)

            if (isEmailValid && isPasswordValid) {
                loginUser(email)
            } else {
                showErrorToast(isEmailValid, isPasswordValid)
            }
        }
    }

    private fun showErrorToast(isEmailValid: Boolean, isPasswordValid: Boolean) {
        //show toast
        if (!isEmailValid) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG).show()
        }

        if (!isPasswordValid) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG).show()
        }
    }


    override fun onStart() {
        super.onStart()
        checkedLogin()
    }

    private fun checkedLogin() {
        val userEmail = getUserEmailFromSharedPreference()
        if (!userEmail.equals("")) {
            //intent to go to main activity
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserEmailFromSharedPreference(): String {
        return sharedPreferences.getString("email", "")!!
    }

    private fun setUserEmailFromSharedPreference(email: String) {
        editor.putString("email", email)
        editor.apply()
    }

    //login task of user
    private fun loginUser(email: String) {

        //clearing the edit text
        binding.TVEmail.text?.clear()
        binding.TVPassword.text?.clear()

        //store data in shared preference
        setUserEmailFromSharedPreference(email)

        //intent to go to main activity
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
    }

}