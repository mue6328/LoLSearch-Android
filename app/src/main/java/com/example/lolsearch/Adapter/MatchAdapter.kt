package com.example.lolsearch.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.lolsearch.dto.MatchInfo
import com.example.lolsearch.R
import android.util.Log
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lolsearch.activity.MainActivity
import com.example.lolsearch.activity.MatchDetailActivity

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.Holder>() {

    private var matchInfo: List<MatchInfo> = ArrayList()

    fun setItem(list: List<MatchInfo>) {
        this.matchInfo = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.summonermatch_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return matchInfo.size
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(matchInfo[position].championNumber)
            .into(holder.championImage)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item0)
            .into(holder.item0)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item1)
            .into(holder.item1)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item2)
            .into(holder.item2)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item3)
            .into(holder.item3)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item4)
            .into(holder.item4)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].item5)
            .into(holder.item5)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].spell1Id)
            .into(holder.spell1)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].spell2Id)
            .into(holder.spell2)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].mainRune)
            .into(holder.mainRune)

        Glide.with(holder.itemView.context)
            .load(matchInfo[position].subRune)
            .into(holder.subRune)

        var durationmin: String = String.format("%02d", (matchInfo[position].gameDuration / 60))
        var durationsec: String = String.format("%02d", (matchInfo[position].gameDuration % 60))

        var matchDuration = "$durationmin:$durationsec"

        if (matchInfo[position].win) {
            holder.matchWinorLose.text = "승\n\n$matchDuration"
            holder.matchWinorLose.setBackgroundResource(R.color.colorBlue)
        }
        else {
            holder.matchWinorLose.text = "패\n\n$matchDuration"
            holder.matchWinorLose.setBackgroundResource(R.color.colorRed)
        }



        holder.kda.text = "" + matchInfo[position].kills + " / " + matchInfo[position].deaths + " / " + matchInfo[position].assists



        //matchInfo[position].deaths

        var grade = (matchInfo[position].kills + matchInfo[position].assists) / matchInfo[position].deaths.toDouble()



        when {
            grade >= 3 && grade < 4 -> holder.grade.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorGreen))
            grade >= 4 && grade < 5 -> holder.grade.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorBlue))
            grade >= 5 -> holder.grade.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorOrange))
            else -> holder.grade.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorGray))
        }

        when {
            String.format("%.2f", grade) == "NaN" -> holder.grade.text = "0.00"
            String.format("%.2f", grade) == "Infinity" -> {
                holder.grade.text = "Perfect"
                holder.grade.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorOrange))
            }
            else -> holder.grade.text = String.format("%.2f", grade)
        }

        when {
            matchInfo[position].matchQueueType.toString() == "420" -> holder.matchQueueType.text = "솔로랭크"
            matchInfo[position].matchQueueType.toString() == "430" -> holder.matchQueueType.text = "일반"
            matchInfo[position].matchQueueType.toString() == "440" -> holder.matchQueueType.text = "자유랭크"
            matchInfo[position].matchQueueType.toString() == "450" -> holder.matchQueueType.text = "무작위 총력전"
            matchInfo[position].matchQueueType.toString() == "900" -> holder.matchQueueType.text = "우르프"
        }

        holder.matchDetail.setOnClickListener {
            Log.d("click", "cardviewClick" + matchInfo[position])
            var intent = Intent(holder.itemView.context, MatchDetailActivity::class.java)
            if (matchInfo[position].win)
                intent.putExtra("matchWinOrLose", "승리")
            else
                intent.putExtra("matchWinOrLose", "패배")
            intent.putExtra("matchQueueType", holder.matchQueueType.text)
            intent.putExtra("matchDuration", matchDuration)
            intent.putExtra("gameId", matchInfo[position].gameId)
            holder.itemView.context.startActivity(intent)
        }

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var matchCardView = itemView.findViewById<CardView>(R.id.matchCardView)!!
        var matchDetail = itemView.findViewById<ConstraintLayout>(R.id.matchDetail)!!
        var matchWinorLose = itemView.findViewById<TextView>(R.id.matchWinorLose)!!
        var championImage = itemView.findViewById<ImageView>(R.id.championImage)!!
        var spell1 = itemView.findViewById<ImageView>(R.id.spell1)!!
        var spell2 = itemView.findViewById<ImageView>(R.id.spell2)!!
        var mainRune = itemView.findViewById<ImageView>(R.id.mainRune)!!
        var subRune = itemView.findViewById<ImageView>(R.id.subRune)!!
        var item0 = itemView.findViewById<ImageView>(R.id.item0)!!
        var item1 = itemView.findViewById<ImageView>(R.id.item1)!!
        var item2 = itemView.findViewById<ImageView>(R.id.item2)!!
        var item3 = itemView.findViewById<ImageView>(R.id.item3)!!
        var item4 = itemView.findViewById<ImageView>(R.id.item4)!!
        var item5 = itemView.findViewById<ImageView>(R.id.item5)!!
        var kda = itemView.findViewById<TextView>(R.id.kda)!!
        var grade = itemView.findViewById<TextView>(R.id.grade)!!
        var matchQueueType = itemView.findViewById<TextView>(R.id.matchQueueType)!!
    }
}