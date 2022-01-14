package com.example.preferencesapp.utils

import android.util.Patterns
import java.util.regex.Pattern

class Validation {

    companion object {
        //verify email entered by user
        fun isValidEmail(email: String): Boolean {
            //entered email is empty or null
            if (email.isEmpty() || email.isBlank()) {
                return false
            }

            //to verify email is valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return false
            }
            return true
        }

        //verify password entered by user
        fun isValidPassword(password: String): Boolean {

            val PASSWORD_PATTERN = Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            )

            //entered password is empty or null
            if (password.isEmpty() || password.isBlank()) {
                return false
            }
            //to verify password must contain atleast 8 characters with 1 lower case,
            // 1 upper case, 1 special digit and no blank space
            if (!PASSWORD_PATTERN.matcher(password).matches()) {
                return false
            }

            return true
        }
    }

}