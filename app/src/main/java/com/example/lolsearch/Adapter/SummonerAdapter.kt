package com.example.lolsearch.adapter

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.R
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lolsearch.dto.SummonerLeagueData

class SummonerAdapter : RecyclerView.Adapter<SummonerAdapter.Holder>() {

    private var leagueInfo: List<SummonerLeagueData> = ArrayList()

    fun setItem(list: List<SummonerLeagueData>) {
        this.leagueInfo = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.summonerleague_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return leagueInfo.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when {
            leagueInfo[position].queueType == "RANKED_SOLO_5x5" -> holder.queue.text = "솔로랭크"
            leagueInfo[position].queueType == "RANKED_FLEX_SR" -> holder.queue.text = "자유랭크"
            leagueInfo[position].queueType == "RANKED_TFT" -> holder.queue.text = "전략적 팀 전투"
        }

        holder.tier.text = leagueInfo[position].tier + " "
        holder.rank.text = leagueInfo[position].rank
        holder.leaguePoint.text = leagueInfo[position].leaguePoints.toString() + " LP"
        holder.win.text = leagueInfo[position].wins.toString() + "승 "
        holder.lose.text = leagueInfo[position].losses.toString() + "패"

        when {
            holder.tier.text == "IRON " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_iron)
                .into(holder.tierImage)
            holder.tier.text == "BRONZE " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_bronze)
                .into(holder.tierImage)
            holder.tier.text == "SILVER " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_silver)
                .into(holder.tierImage)
            holder.tier.text == "GOLD " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_gold)
                .into(holder.tierImage)
            holder.tier.text == "PLATINUM " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_platinum)
                .into(holder.tierImage)
            holder.tier.text == "DIAMOND " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_diamond)
                .into(holder.tierImage)
            holder.tier.text == "MASTER " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_master)
                .into(holder.tierImage)
            holder.tier.text == "GRANDMASTER " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_grandmaster)
                .into(holder.tierImage)
            holder.tier.text == "CHALLENGER " -> Glide.with(holder.itemView.context)
                .load(R.drawable.emblem_challenger)
                .into(holder.tierImage)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var queue = itemView.findViewById<TextView>(R.id.queueType)!!
        var tier = itemView.findViewById<TextView>(R.id.tier)!!
        var rank = itemView.findViewById<TextView>(R.id.rank)!!
        var leaguePoint = itemView.findViewById<TextView>(R.id.points)!!
        var win = itemView.findViewById<TextView>(R.id.summonerWins)!!
        var lose = itemView.findViewById<TextView>(R.id.summonerLose)!!
        var tierImage = itemView.findViewById<ImageView>(R.id.tierImage)!!
    }
}