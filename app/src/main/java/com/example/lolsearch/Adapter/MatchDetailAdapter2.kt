package com.example.lolsearch.adapter

import android.annotation.SuppressLint
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

class MatchDetailAdapter2 : RecyclerView.Adapter<MatchDetailAdapter2.Holder>() {

    private var matchDetailInfo: List<MatchDetail> = ArrayList()

    private var matchSummonerTier: List<String> = ArrayList()

    fun setItem(list: List<MatchDetail>) {
        this.matchDetailInfo = list
        notifyDataSetChanged()
    }

    fun setTier(tier: List<String>) {
        this.matchSummonerTier = tier
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.matchdetail_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return matchDetailInfo.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].championId)
            .into(holder.championImage)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item0)
            .into(holder.item0)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item1)
            .into(holder.item1)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item2)
            .into(holder.item2)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item3)
            .into(holder.item3)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item4)
            .into(holder.item4)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].item5)
            .into(holder.item5)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].spell1Id)
            .into(holder.spell1)

        Glide.with(holder.itemView.context)
            .load(matchDetailInfo[position].spell2Id)
            .into(holder.spell2)

//        if (matchDetailInfo[position].win ) {
//            holder.matchDetailLayout.setBackgroundResource(R.color.colorBlue)
//        }
//        else {
//            holder.matchDetailLayout.setBackgroundResource(R.color.colorRed)
//        }
        holder.summonerTier.text = matchSummonerTier[position]

        holder.summonerName.text = matchDetailInfo[position].summonerName
        holder.kda.text = "" + matchDetailInfo[position].kills + " / " +  matchDetailInfo[position].deaths + " / " + matchDetailInfo[position].assists
        holder.csGold.text = "" + matchDetailInfo[position].cs + " / " + matchDetailInfo[position].goldEarned + " G"
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var matchDetailLayout = itemView.findViewById<ConstraintLayout>(R.id.matchDetailLayout)
        var championImage = itemView.findViewById<ImageView>(R.id.detailChampionImage)!!
        var spell1 = itemView.findViewById<ImageView>(R.id.spell1)!!
        var spell2 = itemView.findViewById<ImageView>(R.id.spell2)!!
        var summonerName = itemView.findViewById<TextView>(R.id.summonerName)!!
        var kda = itemView.findViewById<TextView>(R.id.kda)!!
        var item0 = itemView.findViewById<ImageView>(R.id.item0)!!
        var item1 = itemView.findViewById<ImageView>(R.id.item1)!!
        var item2 = itemView.findViewById<ImageView>(R.id.item2)!!
        var item3 = itemView.findViewById<ImageView>(R.id.item3)!!
        var item4 = itemView.findViewById<ImageView>(R.id.item4)!!
        var item5 = itemView.findViewById<ImageView>(R.id.item5)!!
        var csGold = itemView.findViewById<TextView>(R.id.csGold)!!
        var summonerTier = itemView.findViewById<TextView>(R.id.summonerTier)!!
    }
}