package com.joaostanzione.iddog.ui.dogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joaostanzione.iddog.R

class DogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)
        val token = intent.getStringExtra(EXTRA_KEY_TOKEN)
    }

    companion object {
        private const val EXTRA_KEY_TOKEN = "token"
        fun start(context: Context, token: String) {
            val intent = Intent(context, DogsActivity::class.java)
            intent.putExtra(EXTRA_KEY_TOKEN, token)
            context.startActivity(intent)
        }
    }
}
