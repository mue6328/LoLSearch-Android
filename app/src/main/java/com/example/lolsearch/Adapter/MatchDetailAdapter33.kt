package com.example.lolsearch.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lolsearch.dto.MatchDetail
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lolsearch.activity.SummonerActivity

class MatchDetailAdapter33 : RecyclerView.Adapter<MatchDetailAdapter33.Holder>() {

    private var matchSummonerTier: List<String> = ArrayList()

    fun setTier(tier: List<String>) {
        this.matchSummonerTier = tier
        //notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.matchdetail_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return matchSummonerTier.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.summonerTier.text = matchSummonerTier[position]
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var summonerTier = itemView.findViewById<TextView>(R.id.summonerTier)!!
    }
}