package com.example.lolsearch.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lolsearch.dto.ChampionNumber

class RotationAdapter : RecyclerView.Adapter<RotationAdapter.Holder>() {

    private var rotationInfo: List<ChampionNumber> = ArrayList()

    fun setItem(list: List<ChampionNumber>) {
        this.rotationInfo = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rotation_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return rotationInfo.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(rotationInfo[position].championNumber)
            .into(holder.rotationChampionImage)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rotationChampionImage = itemView.findViewById<ImageView>(R.id.rotationChampionImage)!!
    }
}