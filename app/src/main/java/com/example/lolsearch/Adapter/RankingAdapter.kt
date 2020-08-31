package com.example.lolsearch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.dto.RankingInfo
import com.example.lolsearch.R
import android.content.Intent
import com.example.lolsearch.activity.SummonerActivity

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.Holder>() {
    private var rankingInfo: List<RankingInfo> = ArrayList()

    fun setItem(list: List<RankingInfo>) {
        this.rankingInfo = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ranking_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return rankingInfo.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.ranking.text = rankingInfo[position].ranking + " "
        holder.rankingSummonerName.text = rankingInfo[position].summonerName
        if (rankingInfo[position].summonerTier == "CHALLENGER") {
            holder.rankingTier.text = "C1 "
        }
        holder.rankingLP.text = rankingInfo[position].summonerLP + " LP"

        holder.rankingClick.setOnClickListener {
            var intent = Intent(holder.itemView.context, SummonerActivity::class.java)
            intent.putExtra("userId", rankingInfo[position].summonerId)
            intent.putExtra("name", rankingInfo[position].summonerName)
            //intent.putExtra("profileImage", rankingInfo[position].)
            holder.itemView.context.startActivity(intent)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rankingClick = itemView.findViewById<ConstraintLayout>(R.id.click_ranking)!!
        var ranking = itemView.findViewById<TextView>(R.id.ranking)!!
        var rankingSummonerName = itemView.findViewById<TextView>(R.id.rankingSummonerName)!!
        var rankingTier = itemView.findViewById<TextView>(R.id.rankingTier)!!
        var rankingLP = itemView.findViewById<TextView>(R.id.rankingLP)!!
    }
}