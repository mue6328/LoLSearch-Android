package com.example.lolsearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.adapter.RankingAdapter
import com.example.lolsearch.dto.Ranking
import com.example.lolsearch.dto.RankingInfo
import com.example.lolsearch.R
import com.example.lolsearch.Service.LeagueEXPApi
import com.example.lolsearch.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast

class RankingActivity : AppCompatActivity() {

    private var rankingList = ArrayList<RankingInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_activity)

        var leagueEXPApi: LeagueEXPApi = Utils.retrofit.create(LeagueEXPApi::class.java)

        var rankingRecyclerView : RecyclerView = findViewById(R.id.rankingRecyclerView)

        var rankingAdapter = RankingAdapter()

        rankingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rankingRecyclerView.setHasFixedSize(true)
        rankingRecyclerView.adapter = rankingAdapter

        leagueEXPApi.getleagueRanking(Utils.riotToken, "RANKED_SOLO_5x5", "CHALLENGER", "I", 1)
            .enqueue(object : Callback<List<Ranking>>{
                override fun onFailure(call: Call<List<Ranking>>, t: Throwable) {

                }

                override fun onResponse(call: Call<List<Ranking>>, response: Response<List<Ranking>>) {
                    if (response.code() == 429) {
                        Toast.makeText(applicationContext, "잠시 후 시도해주세요.(너무 많은 요청)", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        for (i in 0..199) {
                            Log.d(
                                "ranking",
                                "" + response.body() + response.code() + response.message()
                            )
                            rankingList.add(
                                RankingInfo(
                                    "" + (i + 1) + " ",
                                    response.body()!![i].summonerName,
                                    response.body()!![i].tier,
                                    response.body()!![i].leaguePoints.toString(),
                                    response.body()!![i].summonerId
                                )
                            )
                        }
                        rankingAdapter.setItem(rankingList)
                    }
                }

            })
    }
}