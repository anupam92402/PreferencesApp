package com.example.preferencesapp.activties

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.preferencesapp.R
import com.example.preferencesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding//view binding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //to retrieve stored email id in shared preference
        sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        if (email != null) {
            binding.TVWelcome.text = "Welcome \n $email"
        }

        binding.btnLogout.setOnClickListener {
            logoutUser()
        }

    }

    //logout the user
    private fun logoutUser() {
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.apply()
        Toast.makeText(this@MainActivity, "User Logout Successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.exit)
        //set message for alert dialog
        builder.setMessage(R.string.are_you_sure)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            logoutUser()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}