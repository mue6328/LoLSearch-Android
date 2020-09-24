package com.example.lolsearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lolsearch.R
import com.example.lolsearch.Service.MatchApi
import com.example.lolsearch.Utils
import com.example.lolsearch.dto.Match
import kotlinx.android.synthetic.main.matchdetail_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.adapter.MatchDetailAdapter
import com.example.lolsearch.dto.MatchDetail
import android.util.Log
import android.widget.Toast
import com.example.lolsearch.Service.LeagueApi
import com.example.lolsearch.Service.SummonerApi
import com.example.lolsearch.dto.League
import com.example.lolsearch.dto.Summoner
import kotlinx.android.synthetic.main.matchdetail_activity.queueType
import kotlinx.android.synthetic.main.matchdetail_item.*
import kotlinx.android.synthetic.main.summonerleague_item.*
import java.util.*
import kotlin.collections.ArrayList

class MatchDetailActivity : AppCompatActivity() {

    private var matchDetailList = ArrayList<MatchDetail>()
    private var matchDetailList2 = ArrayList<MatchDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.matchdetail_activity)

        var matchTierList = ArrayList<String>()

        winorLose.text = intent.getStringExtra("matchWinOrLose")
        queueType.text = intent.getStringExtra("matchQueueType")
        gameDuration.text = intent.getStringExtra("matchDuration")

        var gameId = intent.getStringExtra("gameId").toLong()

        var matchApi: MatchApi = Utils.retrofit.create(MatchApi::class.java)
        var leagueApi: LeagueApi = Utils.retrofit.create(LeagueApi::class.java)

        var matchDetailRecyclerView : RecyclerView = findViewById(R.id.matchDetailRecyclerView)
        var matchDetailAdapter = MatchDetailAdapter()

        matchDetailRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        matchDetailRecyclerView.setHasFixedSize(true)
        matchDetailRecyclerView.adapter = matchDetailAdapter

        var matchDetailRecyclerView2 : RecyclerView = findViewById(R.id.matchDetailRecyclerView2)
        var matchDetailAdapter2 = MatchDetailAdapter()

        matchDetailRecyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        matchDetailRecyclerView2.setHasFixedSize(true)
        matchDetailRecyclerView2.adapter = matchDetailAdapter2

        matchApi.getMatches(Utils.riotToken, gameId).enqueue(object : Callback<Match>{
            override fun onFailure(call: Call<Match>, t: Throwable) {

            }

            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                var date = Date(1592636863023 * 1000)
                Log.i("datee", "" + date)
                Log.d(
                    "matchdetail",
                    "" + response.code() + response.message())
                if (response.code() == 429) {
                    Toast.makeText(applicationContext, "잠시 후 시도해주세요.(너무 많은 요청)", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    for (i in 0..4) {
                        leagueApi.getSummonerLeague(
                            Utils.riotToken,
                            response.body()!!.participantIdentities[i].player.summonerId
                        ).enqueue(object : Callback<List<League>> {
                            override fun onFailure(call: Call<List<League>>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<List<League>>,
                                response: Response<List<League>>
                            ) {
                                for (k in response.body()!!.indices) {
                                    if (response.body()!![k].queueType == "RANKED_SOLO_5x5") {
                                        if (response.body()!![k].tier == "IRON") {
                                            when {
                                                response.body()!![k].rank == "I" -> matchTierList.add("I1")
                                                response.body()!![k].rank == "II" -> matchTierList.add(
                                                    "I2"
                                                )

                                                response.body()!![k].rank == "III" -> matchTierList.add(
                                                    "I3"
                                                )

                                                response.body()!![k].rank == "IV" -> matchTierList.add(
                                                    "I4"
                                                )
                                            }
                                        }
                                        else if (response.body()!![k].tier == "BRONZE") {
                                            when {
                                                response.body()!![k].rank == "I" -> matchTierList.add(
                                                    "B1"
                                                )

                                                response.body()!![k].rank == "II" -> matchTierList.add(
                                                    "B2"
                                                )

                                                response.body()!![k].rank == "III" -> matchTierList.add(
                                                    "B3"
                                                )

                                                response.body()!![k].rank == "IV" -> matchTierList.add(
                                                    "B4"
                                                )

                                            }
                                        }
                                        else if (response.body()!![k].tier == "SILVER") {
                                            when {
                                                response.body()!![k].rank == "I" -> matchTierList.add(
                                                    "S1"
                                                )

                                                response.body()!![k].rank == "II" -> matchTierList.add(
                                                    "S2"
                                                )

                                                response.body()!![k].rank == "III" -> matchTierList.add(
                                                    "S3"
                                                )

                                                response.body()!![k].rank == "IV" -> matchTierList.add(
                                                    "S4"
                                                )
                                            }
                                        }
                                        else if (response.body()!![k].tier == "GOLD") {
                                            when {
                                                response.body()!![k].rank == "I" -> matchTierList.add(
                                                    "G1"
                                                )

                                                response.body()!![k].rank == "II" -> matchTierList.add(
                                                    "G2"
                                                )

                                                response.body()!![k].rank == "III" -> matchTierList.add(
                                                    "G3"
                                                )

                                                response.body()!![k].rank == "IV" -> matchTierList.add(
                                                    "G4"
                                                )
                                            }
                                        }
                                        else if (response.body()!![k].tier == "PLATINUM") {
                                            if (response.body()!![k].rank == "I")
                                                matchTierList.add("P1")
                                            else if (response.body()!![k].rank == "II") matchTierList.add(
                                                "P2"
                                            )
                                            else if (response.body()!![k].rank == "III") matchTierList.add(
                                                "P3"
                                            )
                                            else if (response.body()!![k].rank == "IV") matchTierList.add(
                                                "P4"
                                            )
                                        }

                                        else if (response.body()!![k].tier == "DIAMOND") {
                                            if (response.body()!![k].rank == "I") matchTierList.add(
                                                "D1"
                                            )
                                            else if (response.body()!![k].rank == "II") matchTierList.add(
                                                "D2"
                                            )
                                            else if (response.body()!![k].rank == "III") matchTierList.add(
                                                "D3"
                                            )
                                            else if (response.body()!![k].rank == "IV") matchTierList.add(
                                                "D4"
                                            )
                                        }
                                            else if (response.body()!![k].tier == "MASTER")
                                                matchTierList.add("M1")

                                            else if (response.body()!![k].tier == "GRANDMASTER")
                                                matchTierList.add("GM1")

                                            else if (response.body()!![k].tier == "CHALLENGER")
                                                matchTierList.add("C1")

                                        Log.d("tierr", response.body()!![k].tier + matchTierList + response.body()!![k].queueType)
                                    }
                                    Log.d("tierrr", response.body()!![k].tier[0] + "" + matchTierList + response.body()!![k].queueType)
                                    matchDetailAdapter.setTier(matchTierList)
                                }
                                    //Log.d("tierrr", response.body()!![0].tier + matchTierList + response.body()!![0].queueType)
                                Log.d("resp", "" + response.body())
                                //Log.d("tieeeerr", "" + matchTierList[0] + matchTierList[1] + matchTierList[2] + matchTierList[3] + matchTierList[4])
//                                if (k == 4)
//                                matchDetailAdapter.setTier(matchTierList)
                                //matchDetailList[i].tier = matchTierList[i]
                            }
                        })

                        Log.i("timee", "" + response.body()!!.gameCreation)

                        if (response.body()!!.participants[i].stats.win) {
                            team1.setBackgroundResource(R.color.colorBlue)
                            if (response.body()!!.participants[i].teamId == 100)
                                teamWinorLose.text = "승리 (블루)"
                            else
                                teamWinorLose.text = "승리 (레드)"
                        } else {
                            team1.setBackgroundResource(R.color.colorRed)
                            if (response.body()!!.participants[i].teamId == 100)
                                teamWinorLose.text = "패배 (블루)"
                            else
                                teamWinorLose.text = "패배 (레드)"
                        }

                        matchDetailList.add(
                            MatchDetail(
                                Utils.version_champion
                                        + Utils.championMap.getValue(response.body()!!.participants[i].championId) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell1Id) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell2Id) + ".png",

                                response.body()!!.participantIdentities[i].player.summonerName,
                                response.body()!!.participants[i].stats.kills,
                                response.body()!!.participants[i].stats.deaths,
                                response.body()!!.participants[i].stats.assists,

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item0 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item1 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item2 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item3 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item4 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item5 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item6 + ".png",

                                response.body()!!.participants[i].stats.goldEarned,
                                response.body()!!.participants[i].stats.totalMinionsKilled + response.body()!!.participants[i].stats.neutralMinionsKilled,
                                response.body()!!.participants[i].stats.totalDamageDealtToChampions,

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.runeMap.getValue(response.body()!!.participants[i].stats.perkPrimaryStyle) +

                                        "/" + Utils.runeMap.getValue(response.body()!!.participants[i].stats.perk0) +
                                        "/" + Utils.runeMap2.getValue(response.body()!!.participants[i].stats.perk0) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.subRuneMap.getValue(response.body()!!.participants[i].stats.perkSubStyle),

                                response.body()!!.participantIdentities[i].player.summonerId,

                                "dd"
                            )
                        )
                        //Log.d("tieeeer", matchSummonerTier)
                        Log.d("tierrrrr", "" + matchTierList)
                        matchDetailAdapter.setItem(matchDetailList)
                        // matchDetailAdapter2.setTier(matchTierList)
                    }
                    Log.d("tierrrrr", "" + matchTierList)
                    matchDetailAdapter.setTier(matchTierList)

                    for (i in 5..9) {

                        if (response.body()!!.participants[i].stats.win) {
                            team2.setBackgroundResource(R.color.colorBlue)
                            if (response.body()!!.participants[i].teamId == 100)
                                teamWinorLose2.text = "승리 (블루)"
                            else
                                teamWinorLose2.text = "승리 (레드)"
                        } else {
                            team2.setBackgroundResource(R.color.colorRed)
                            if (response.body()!!.participants[i].teamId == 100)
                                teamWinorLose2.text = "패배 (블루)"
                            else
                                teamWinorLose2.text = "패배 (레드)"
                        }
                        matchDetailList2.add(
                            MatchDetail(
                                Utils.version_champion
                                        + Utils.championMap.getValue(response.body()!!.participants[i].championId) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell1Id) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell2Id) + ".png",

                                response.body()!!.participantIdentities[i].player.summonerName,
                                response.body()!!.participants[i].stats.kills,
                                response.body()!!.participants[i].stats.deaths,
                                response.body()!!.participants[i].stats.assists,

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item0 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item1 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item2 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item3 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item4 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item5 + ".png",

                                Utils.version_item +
                                        response.body()!!.participants[i].stats.item6 + ".png",

                                response.body()!!.participants[i].stats.goldEarned,
                                response.body()!!.participants[i].stats.totalMinionsKilled + response.body()!!.participants[i].stats.neutralMinionsKilled,
                                response.body()!!.participants[i].stats.totalDamageDealtToChampions,

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.runeMap.getValue(response.body()!!.participants[i].stats.perkPrimaryStyle) +

                                        "/" + Utils.runeMap.getValue(response.body()!!.participants[i].stats.perk0) +
                                        "/" + Utils.runeMap2.getValue(response.body()!!.participants[i].stats.perk0) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.subRuneMap.getValue(response.body()!!.participants[i].stats.perkSubStyle),

                                response.body()!!.participantIdentities[i].player.summonerId,

                                "ff"
                            )
                        )
                        matchDetailAdapter2.setItem(matchDetailList2)
                        //  matchDetailAdapter2.setTier(matchTierList)
                    }
                }
            }
        })
    }
}