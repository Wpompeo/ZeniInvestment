package com.wts.zeniinvestment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.wts.zeniinvestment.adapter.TipsAdapter
import com.wts.zeniinvestment.model.Tip

class MainActivity : AppCompatActivity() {

    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //lista de mensagens da tela inicial
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
            ),

            Tip(
                R.drawable.ic_start,
                "Simulação inteligente",
                "Calcule projeções precisas com juros compostos."
            ),

            Tip(
                R.drawable.ic_invest,
                "Pronto para começar?",
                "Configure sua simulação e descubra o potencial dos seus investimentos."
            )



        )

        fun createDots(size: Int, currentPosition: Int) {

            dots = Array(size) { ImageView(this) }

            dotsLayout.removeAllViews()

            for (i in 0 until size) {

                dots[i] = ImageView(this)

                if (i == currentPosition)
                    dots[i].setImageResource(R.drawable.dot_selected)
                else
                    dots[i].setImageResource(R.drawable.dot_unselected)

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                params.setMargins(8, 0, 8, 0)

                dotsLayout.addView(dots[i], params)
            }
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerInit)

        viewPager.adapter = TipsAdapter(tips)
        viewPager.registerOnPageChangeCallback(

            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    createDots(tips.size, position)
                }

            }

        )
        dotsLayout = findViewById(R.id.dotsLayout)

        createDots(tips.size, 0)
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    // muda a bolinha selecionada
                }
            }
        )

        val btnInit = findViewById<Button>(R.id.btn_int)
        btnInit.setOnClickListener {
           val intent = Intent(this, Calculate::class.java)
            startActivity(intent)
        }


    }
}