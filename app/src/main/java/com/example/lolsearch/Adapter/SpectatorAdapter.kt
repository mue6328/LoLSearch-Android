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
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lolsearch.activity.SummonerActivity
import com.example.lolsearch.dto.inGame

class SpectatorAdapter : RecyclerView.Adapter<SpectatorAdapter.Holder>() {

    private var inGameInfo: List<inGame> = ArrayList()

    //private var matchSummonerTier: List<String> = ArrayList()

    fun setItem(list: List<inGame>) {
        this.inGameInfo = list
        notifyDataSetChanged()
    }

//    fun setTier(tier: List<String>) {
//        this.matchSummonerTier = tier
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.spectator_item_blue, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return inGameInfo.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(inGameInfo[position].championImage)
            .into(holder.championImage)

        Glide.with(holder.itemView.context)
            .load(inGameInfo[position].spell1)
            .into(holder.spell1)

        Glide.with(holder.itemView.context)
            .load(inGameInfo[position].spell2)
            .into(holder.spell2)

        Glide.with(holder.itemView.context)
            .load(inGameInfo[position].mainRune)
            .into(holder.mainRune)

        Glide.with(holder.itemView.context)
            .load(inGameInfo[position].subRune)
            .into(holder.subRune)

        holder.summonerName.text = inGameInfo[position].summonerName
        holder.spectatorLayoutBlue.setOnClickListener {
            var intent = Intent(holder.itemView.context, SummonerActivity::class.java)
            intent.putExtra("userId", inGameInfo[position].summonerId)
            intent.putExtra("name", inGameInfo[position].summonerName)
            holder.itemView.context.startActivity(intent)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var matchDetailLayout = itemView.findViewById<ConstraintLayout>(R.id.matchDetailLayout)
        var spectatorLayoutBlue = itemView.findViewById<ConstraintLayout>(R.id.spectatorLayoutBlue)!!
        var championImage = itemView.findViewById<ImageView>(R.id.championImage)!!
        var spell1 = itemView.findViewById<ImageView>(R.id.spell1)!!
        var spell2 = itemView.findViewById<ImageView>(R.id.spell2)!!
        var summonerName = itemView.findViewById<TextView>(R.id.summonerName)!!
        var mainRune = itemView.findViewById<ImageView>(R.id.mainRune)!!
        var subRune = itemView.findViewById<ImageView>(R.id.subRune)!!
        //var summonerTier = itemView.findViewById<TextView>(R.id.summonerTier)!!
    }
}