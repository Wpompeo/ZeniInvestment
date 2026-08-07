package com.wts.zeniinvestment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.wts.zeniinvestment.adapter.TipsAdapter
import com.wts.zeniinvestment.model.Tip

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tips = listOf(

            Tip(
                R.drawable.ic_money,
                "Juros Compostos",
                "Quanto antes você começar, maior será seu patrimônio."
            ),

            Tip(
                R.drawable.ic_chart,
                "Diversifique",
                "Não concentre todos os investimentos em apenas um ativo."
            ),

            Tip(
                R.drawable.ic_bank,
                "Reserva de Emergência",
                "Tenha entre 6 e 12 meses de despesas antes de investir em renda variável."
            )

        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerInit)

        viewPager.adapter = TipsAdapter(tips)
    }
}