package com.wts.zeniinvestment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val initialValue =
            intent.getDoubleExtra("initialValue", 0.0)

        val totalContribution =
            intent.getDoubleExtra("totalContribution", 0.0)

        val totalInvested =
            intent.getDoubleExtra("totalInvested", 0.0)

        val totalInterest =
            intent.getDoubleExtra("totalInterest", 0.0)

        val finalBalance =
            intent.getDoubleExtra("finalBalance", 0.0)

        val years =
            intent.getIntExtra("years", 0)


    }
}