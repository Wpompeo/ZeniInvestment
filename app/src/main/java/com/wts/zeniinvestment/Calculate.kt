package com.wts.zeniinvestment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class Calculate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editMoney: TextInputEditText = findViewById(R.id.edt_money)
        val editContribution: TextInputEditText = findViewById(R.id.edt_contribution)
        val editTaxa: TextInputEditText = findViewById(R.id.edt_taxa)
        val editYears: TextInputEditText = findViewById(R.id.edt_time)
        val btnAdvanced: Button = findViewById(R.id.btn_calc)

        btnAdvanced.setOnClickListener {

        }


    }


}