package com.example.lolsearch.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.lolsearch.dto.inGame
import com.example.lolsearch.dto.inGameBans

class SpectatorBanAdapter : RecyclerView.Adapter<SpectatorBanAdapter.Holder>() {

    private var inGameBansInfo: List<inGameBans> = ArrayList()

    //private var matchSummonerTier: List<String> = ArrayList()

    fun setItem(list: List<inGameBans>) {
        this.inGameBansInfo = list
        notifyDataSetChanged()
    }

//    fun setTier(tier: List<String>) {
//        this.matchSummonerTier = tier
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.spectator_banitem, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return inGameBansInfo.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
//        Glide.with(holder.itemView.context)
//            .load(inGameBansInfo[position].)
//            .into(holder.championImage)


    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var championImage = itemView.findViewById<ImageView>(R.id.championImage)!!
    }
}