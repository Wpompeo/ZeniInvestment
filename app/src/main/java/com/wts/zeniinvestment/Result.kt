package com.wts.zeniinvestment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

class Result : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnFinish = findViewById<Button>(R.id.btnFinish)

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

        val annualResults =
            intent.getDoubleArrayExtra("annualResults")
                ?: doubleArrayOf()

        // Referências dos TextViews
        val txtFinalValue =
            findViewById<TextView>(R.id.txt_final_value)

        val txtTotalInvested =
            findViewById<TextView>(R.id.txt_total_invested)

        val txtProfit =
            findViewById<TextView>(R.id.txt_profit)

        val txtPeriod =
            findViewById<TextView>(R.id.txt_period)

        // Preenche os valores
        txtFinalValue.text = formatCurrency(finalBalance)

        txtTotalInvested.text = formatCurrency(totalInvested)

        txtProfit.text = formatCurrency(totalInterest)

        txtPeriod.text = "Após $years anos de investimento"


        val layoutYears =
            findViewById<LinearLayout>(R.id.layout_years)

        for (i in annualResults.indices) {

            val yearLayout = LinearLayout(this)

            yearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            yearLayout.orientation = LinearLayout.HORIZONTAL
            yearLayout.gravity = Gravity.CENTER_VERTICAL

            val yearText = TextView(this)

            yearText.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )

            yearText.text = "Ano ${i + 1}"
            yearText.textSize = 12f

            val valueText = TextView(this)

            valueText.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )

            valueText.text = formatCurrency(annualResults[i])
            valueText.textSize = 13f
            valueText.gravity = Gravity.END

            yearLayout.addView(yearText)
            yearLayout.addView(valueText)

            layoutYears.addView(yearLayout)
        }

        btnFinish.setOnClickListener {
            Snackbar.make(btnFinish, "Finalizando App...", Snackbar.LENGTH_LONG).show()
            btnFinish.postDelayed({
                finishAffinity()
            }, 300)

        }


    }


    private fun formatCurrency(value: Double): String {

        return NumberFormat
            .getCurrencyInstance(Locale("pt", "BR"))
            .format(value)
    }





}