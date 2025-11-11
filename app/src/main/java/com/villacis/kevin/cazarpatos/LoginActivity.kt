package com.villacis.kevin.cazarpatos

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordText: TextView
    private lateinit var createAccountText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        // Initialize views
        emailInputLayout = findViewById(R.id.email_input_layout)
        passwordInputLayout = findViewById(R.id.password_input_layout)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        loginButton = findViewById(R.id.login_button)
        forgotPasswordText = findViewById(R.id.forgot_password_text)
        createAccountText = findViewById(R.id.create_account_text)
        
        // Set up click listeners
        loginButton.setOnClickListener {
            attemptLogin()
        }
        
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, R.string.forgot_password_message, Toast.LENGTH_SHORT).show()
        }
        
        createAccountText.setOnClickListener {
            Toast.makeText(this, R.string.create_account_message, Toast.LENGTH_SHORT).show()
        }
        
        // Clear errors when user starts typing
        emailEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailInputLayout.error = null
            }
        }
        
        passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordInputLayout.error = null
            }
        }
    }
    
    private fun attemptLogin() {
        // Clear previous errors
        emailInputLayout.error = null
        passwordInputLayout.error = null
        
        // Get values
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        
        var hasError = false
        
        // Validate email
        if (TextUtils.isEmpty(email)) {
            emailInputLayout.error = getString(R.string.error_email_required)
            hasError = true
        } else if (!isEmailValid(email)) {
            emailInputLayout.error = getString(R.string.error_email_invalid)
            hasError = true
        }
        
        // Validate password
        if (TextUtils.isEmpty(password)) {
            passwordInputLayout.error = getString(R.string.error_password_required)
            hasError = true
        } else if (password.length < 6) {
            passwordInputLayout.error = getString(R.string.error_password_too_short)
            hasError = true
        }
        
        if (hasError) {
            return
        }
        
        // Simulate login attempt (in a real app, this would call an API)
        // For demonstration, we'll show different messages
        if (email == "demo@cazarpatos.com" && password == "demo123") {
            Toast.makeText(this, R.string.login_success, Toast.LENGTH_LONG).show()
            // Here you would navigate to the main activity
        } else {
            Toast.makeText(this, R.string.error_invalid_credentials, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
