package com.joaostanzione.iddog.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.joaostanzione.iddog.R
import com.joaostanzione.iddog.ui.dogs.DogsActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
        initializeObservers()
    }

    private fun setupViews() {
        loginEmailEditText.addTextChangedListener(validEmailTextWatcher())

        loginButton.setOnClickListener {
            loginError.visibility = View.GONE
            viewModel.onLoginClick(loginEmailEditText.text.toString())
        }
    }

    private fun initializeObservers() {
        viewModel.states.observe(this, Observer { states ->
            states?.let {
                when (it) {
                    is LoginViewModel.LoginState.Success -> DogsActivity.start(this, it.token)
                    is LoginViewModel.LoginState.Error -> loginError.visibility = View.VISIBLE
                }
            }
        })

        viewModel.showLoading.observe(this, Observer { loading ->
            loading?.let {
                loginLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun validEmailTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                text?.let {
                    loginButton.visibility = if (Patterns.EMAIL_ADDRESS.matcher(it).matches()) View.VISIBLE else View.GONE
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /*do nothing*/ }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /*do nothing*/ }
        }
    }
}
