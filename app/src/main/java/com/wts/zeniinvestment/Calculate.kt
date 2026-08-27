package com.wts.zeniinvestment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.pow

class Calculate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
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

            val editMoneyStr: String = editMoney.text.toString()
            val editContributionStr: String = editContribution.text.toString()
            val editTaxaStr: String = editTaxa.text.toString()
            val editYearsStr: String = editYears.text.toString()

            if (editMoneyStr == "" || editContributionStr == "" || editTaxaStr == "" || editYearsStr == "") {
                Snackbar.make(
                    editMoney,
                    "Favor preencher todos os campos!",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            } else {
                val editMoneyValue = editMoneyStr.toDouble()
                val editContributionValue = editContributionStr.toDouble()
                val editTaxaValue = editTaxaStr.toDouble()
                val editYearsValue = editYearsStr.toInt()

                //taxa anual -> mensal
                val monthTaxa = (1.0 + editTaxaValue / 100.0).pow(1.0 / 12.0) - 1.0

                var balance = editMoneyValue
                var totalContribution = 0.0
                var totalFees = 0.0

                val monthTotal = editYearsValue * 12
                val simulation = mutableListOf<InvestmentMonth>()

                for (month in 1..monthTotal) {

                    // juros mês
                    val monthsFees = balance * monthTaxa

                    //atualiza saldo
                    balance += monthsFees

                    //Aporte mensal
                    balance += editContributionValue

                    // Acumula os valores
                    totalContribution += editContributionValue
                    totalFees += monthsFees



                    simulation.add(
                        InvestmentMonth(
                            month = month,
                            contribution = editContributionValue,
                            interest = monthTaxa,
                            balance = balance
                        )
                    )
                }
                val totalInvested = editMoneyValue + totalContribution
                val finalBalance = balance
                val intent = Intent(this, Result::class.java)

                intent.putExtra("initialValue", editMoneyValue)
                intent.putExtra("totalContribution", totalContribution)
                intent.putExtra("totalInvested", totalInvested)
                intent.putExtra("totalInterest", totalFees)
                intent.putExtra("finalBalance", finalBalance)
                intent.putExtra("years", editYearsValue)

                startActivity(intent)

            }

        }

    }

}