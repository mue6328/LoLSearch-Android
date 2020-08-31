package com.example.lolsearch.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lolsearch.R
import com.example.lolsearch.Service.LeagueApi
import com.example.lolsearch.Utils
import kotlinx.android.synthetic.main.summoner_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lolsearch.adapter.MatchAdapter
import com.example.lolsearch.adapter.SummonerAdapter
import com.example.lolsearch.dto.*
import com.example.lolsearch.Service.MatchApi
import com.example.lolsearch.Service.SummonerApi
import java.util.*
import android.content.Intent
import android.widget.Toast

class SummonerActivity : AppCompatActivity() {

    private var leagueList = ArrayList<SummonerLeagueData>()
    private var matchChampionList = ArrayList<MatchInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summoner_activity)

        //var accountId = intent.getStringExtra("accountId")

        var summonerApi: SummonerApi = Utils.retrofit.create(SummonerApi::class.java)
        var leagueApi: LeagueApi = Utils.retrofit.create(LeagueApi::class.java)
        var matchApi: MatchApi = Utils.retrofit.create(MatchApi::class.java)

        var leagueRecyclerView : RecyclerView = findViewById(R.id.leagueRecyclerView)
        var summonerAdapter = SummonerAdapter()

        leagueRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        leagueRecyclerView.setHasFixedSize(true)
        leagueRecyclerView.adapter = summonerAdapter

        var matchRecyclerView : RecyclerView = findViewById(R.id.matchRecyclerView)
        var matchAdapter = MatchAdapter()

        matchRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        matchRecyclerView.setHasFixedSize(true)
        matchRecyclerView.adapter = matchAdapter



        summonerApi.getSummoner(Utils.riotToken, intent.getStringExtra("name")).enqueue(object : Callback<Summoner>{
            override fun onFailure(call: Call<Summoner>, t: Throwable) {

            }

            override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                Log.d(
                    "response summoner",
                    "" + response.body() + response.code() + response.message()
                )
                if (response.code() == 429) {
                    Toast.makeText(applicationContext, "잠시 후 시도해주세요.(너무 많은 요청)", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    var level = response.body()!!.summonerLevel
                    var profileImage = response.body()!!.profileIconId
                    var accountId = response.body()!!.accountId

                    if (intent.hasExtra("userId")) {
                        var id = intent.getStringExtra("userId")!!

                        inGameButton.setOnClickListener {
                            val intent = Intent(it.context, SpectatorActivity::class.java)
                            intent.putExtra("summonerId", id)
                            intent.putExtra("summonerName", response.body()!!.name)
                            startActivity(intent)
                        }

                        Glide.with(applicationContext)
                            .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/profileicon/$profileImage.png")
                            .into(summonerIcon)

                        leagueApi.getSummonerLeague(Utils.riotToken, id)
                            .enqueue(object : Callback<List<League>> {
                                override fun onFailure(call: Call<List<League>>, t: Throwable) {
                                    Log.d("fail : ", "" + t.toString())
                                }

                                override fun onResponse(
                                    call: Call<List<League>>,
                                    response: Response<List<League>>
                                ) {
                                    Log.d(
                                        "success : ",
                                        "" + response.body() + response.code() + response.message()
                                    )

                                    if (response.body()!!.isEmpty()) {
                                        summonerName.text = intent.getStringExtra("name")
                                        summonerLevel.text = level.toString() + " 레벨"
                                    } else {
                                        summonerName.text = response.body()!![0].summonerName
                                        summonerLevel.text = level.toString() + " 레벨"

                                        for (i in response.body()!!.indices) {
                                            leagueList.add(
                                                SummonerLeagueData(
                                                    response.body()!![i].queueType,
                                                    response.body()!![i].wins,
                                                    response.body()!![i].losses,
                                                    response.body()!![i].rank,
                                                    response.body()!![i].tier,
                                                    response.body()!![i].leaguePoints
                                                )
                                            )

                                        }
                                        summonerAdapter.setItem(leagueList)
                                    }

                                    matchApi.getMatchLists(Utils.riotToken, accountId)
                                        .enqueue(object : Callback<Matchlist> {
                                            override fun onFailure(
                                                call: Call<Matchlist>,
                                                t: Throwable
                                            ) {

                                            }

                                            override fun onResponse(
                                                call: Call<Matchlist>,
                                                response: Response<Matchlist>
                                            ) {
                                                Log.d(
                                                    "tag",
                                                    "" + response.body() + response.code() + response.message()
                                                )
                                                var date = Date(1594541407881)
                                                Log.i("timestamp", "" + response.body()!!.matches[0].timestamp
                                                + "" + date + "" + response.body()!!.matches[0].champion)

                                                for (i in 0..29) {
                                                    response.body()!!.matches[i].timestamp
                                                    matchApi.getMatches(
                                                        Utils.riotToken,
                                                        response.body()!!.matches[i].gameId
                                                    ).enqueue(object : Callback<Match> {
                                                        override fun onFailure(
                                                            call: Call<Match>,
                                                            t: Throwable
                                                        ) {

                                                        }

                                                        override fun onResponse(
                                                            call: Call<Match>,
                                                            response: Response<Match>
                                                        ) {

                                                            for (j in 0..9) {
                                                                if (response.body() != null) {
                                                                    if (response.body()!!.participantIdentities[j].player.accountId == accountId) {
                                                                        Log.d(
                                                                            "matchlist",
                                                                            "" + response.body()!!.participants[j]
                                                                        )

                                                                        matchChampionList.add(
                                                                            (MatchInfo(
                                                                                Utils.version_champion
                                                                                        + Utils.championMap.getValue(
                                                                                    response.body()!!.participants[j].championId
                                                                                ) + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item0 + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item1 + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item2 + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item3 + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item4 + ".png",

                                                                                Utils.version_item +
                                                                                        response.body()!!.participants[j].stats.item5 + ".png",

                                                                                Utils.version_spell +
                                                                                        Utils.spellMap.getValue(
                                                                                            response.body()!!.participants[j].spell1Id
                                                                                        ) + ".png",

                                                                                Utils.version_spell +
                                                                                        Utils.spellMap.getValue(
                                                                                            response.body()!!.participants[j].spell2Id
                                                                                        ) + ".png",

                                                                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                                                                        Utils.runeMap.getValue(
                                                                                            response.body()!!.participants[j].stats.perkPrimaryStyle
                                                                                        ) +

                                                                                        "/" + Utils.runeMap.getValue(
                                                                                    response.body()!!.participants[j].stats.perk0
                                                                                ) +
                                                                                        "/" + Utils.runeMap2.getValue(
                                                                                    response.body()!!.participants[j].stats.perk0
                                                                                ) + ".png",

                                                                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                                                                        Utils.subRuneMap.getValue(
                                                                                            response.body()!!.participants[j].stats.perkSubStyle
                                                                                        ),

                                                                                response.body()!!.participants[j].stats.kills,
                                                                                response.body()!!.participants[j].stats.deaths,
                                                                                response.body()!!.participants[j].stats.assists,
                                                                                response.body()!!.gameDuration,
                                                                                response.body()!!.participants[j].stats.win,
                                                                                response.body()!!.queueId,
                                                                                response.body()!!.gameId.toString()
                                                                            ))
                                                                        )

                                                                    }
                                                                }


                                                                matchAdapter.setItem(
                                                                    matchChampionList
                                                                )
                                                            }
                                                        }
                                                    }) // matches


                                                }
                                            }
                                        }) // matchList

                                }
                            }) // League
                    }
                }
            }
        }) // Summoner

    }
}