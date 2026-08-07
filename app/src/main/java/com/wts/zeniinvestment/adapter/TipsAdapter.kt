package com.wts.zeniinvestment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wts.zeniinvestment.R
import com.wts.zeniinvestment.model.Tip

class TipsAdapter(
    private val tips: List<Tip>
) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val icon = view.findViewById<ImageView>(R.id.imgIcon)
        val title = view.findViewById<TextView>(R.id.txtTitle)
        val description = view.findViewById<TextView>(R.id.txtDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)

        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {

        val tip = tips[position]

        holder.icon.setImageResource(tip.icon)
        holder.title.text = tip.title
        holder.description.text = tip.description
    }

    override fun getItemCount() = tips.size
}