package com.example.lolsearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.R
import com.example.lolsearch.Service.SpectatorApi
import com.example.lolsearch.Utils
import com.example.lolsearch.adapter.MatchDetailAdapter
import com.example.lolsearch.adapter.SpectatorAdapter
import com.example.lolsearch.dto.Spectator
import com.example.lolsearch.dto.inGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.lolsearch.adapter.SpectatorAdapter2
import com.example.lolsearch.adapter.SpectatorBanAdapter
import com.example.lolsearch.dto.inGameBans
import kotlinx.android.synthetic.main.spectator_activity.*

class SpectatorActivity : AppCompatActivity() {

    private var inGameList = ArrayList<inGame>()
    private var inGameList2 = ArrayList<inGame>()

    private var inGameBanList = ArrayList<inGameBans>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spectator_activity)

        var spectatorApi = Utils.retrofit.create(SpectatorApi::class.java)

        var spectatorRecyclerView : RecyclerView = findViewById(R.id.spectatorRecyclerView)
        var spectatorAdapter = SpectatorAdapter()

        spectatorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        spectatorRecyclerView.setHasFixedSize(true)
        spectatorRecyclerView.adapter = spectatorAdapter

        var spectatorRecyclerView2 : RecyclerView = findViewById(R.id.spectatorRecyclerView2)
        var spectatorAdapter2 = SpectatorAdapter2()

        spectatorRecyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        spectatorRecyclerView2.setHasFixedSize(true)
        spectatorRecyclerView2.adapter = spectatorAdapter2

//        var banRecyclerView : RecyclerView = findViewById(R.id.banListRecyclerView)
//        var banAdapter = SpectatorBanAdapter()
//
//        banRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        banRecyclerView.setHasFixedSize(true)
//        banRecyclerView.adapter = banAdapter
//
//        var banRecyclerView2 : RecyclerView = findViewById(R.id.banListRecyclerView2)
//        var banAdapter2 = SpectatorBanAdapter()
//
//        banRecyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        banRecyclerView2.setHasFixedSize(true)
//        banRecyclerView2.adapter = banAdapter2

        spectatorApi.getActiveGame(Utils.riotToken, intent.getStringExtra("summonerId")).enqueue(object : Callback<Spectator>{
            override fun onFailure(call: Call<Spectator>, t: Throwable) {

            }

            override fun onResponse(call: Call<Spectator>, response: Response<Spectator>) {
                Log.d("tag", "" + response.body())
                if (response.code() == 404) {
                    Toast.makeText(
                        this@SpectatorActivity,
                        intent.getStringExtra("summonerName") + " 님은 게임 중이 아닙니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //startActivity(Intent(this@SpectatorActivity, SummonerActivity::class.java))
                }
                else if (response.code() == 429) {
                    Toast.makeText(applicationContext, "잠시 후 시도해주세요.(너무 많은 요청)", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0..4) {

                        inGameList.add(
                            inGame(
                                Utils.version_champion
                                        + Utils.championMap.getValue(response.body()!!.participants[i].championId.toInt()) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell1Id.toInt()) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell2Id.toInt()) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.runeMap.getValue(response.body()!!.participants[i].perks.perkStyle.toInt()) +

                                        "/" + Utils.runeMap.getValue(response.body()!!.participants[i].perks.perkIds[0].toInt()) +
                                        "/" + Utils.runeMap2.getValue(response.body()!!.participants[i].perks.perkIds[0].toInt()) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.subRuneMap.getValue(response.body()!!.participants[i].perks.perkSubStyle.toInt()),

                                response.body()!!.participants[i].summonerName,
                                response.body()!!.participants[i].summonerId
                            )
                        )


//                        inGameBanList.add(
//                            inGameBans(
//                                "https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
////                                        + Utils.championMap.getValue(response.body()!!.bannedChampions[i].championId.toInt()) + ".png",
////
                                //,
//
//                                "https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
//                                        + Utils.championMap.getValue(response.body()!!.bannedChampions[i].championId.toInt()) + ".png",
//
//                                "https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
//                                        + Utils.championMap.getValue(response.body()!!.bannedChampions[i].championId.toInt()) + ".png",
//
//                                "https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
//                                        + Utils.championMap.getValue(response.body()!!.bannedChampions[i].championId.toInt()) + ".png",
//                            )
//                        )
//                        banAdapter.setItem()
                        spectatorAdapter.setItem(inGameList)
                    }

                    for (i in 5..9) {
                        inGameList2.add(
                            inGame(
                                Utils.version_champion
                                        + Utils.championMap.getValue(response.body()!!.participants[i].championId.toInt()) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell1Id.toInt()) + ".png",

                                Utils.version_spell +
                                        Utils.spellMap.getValue(response.body()!!.participants[i].spell2Id.toInt()) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.runeMap.getValue(response.body()!!.participants[i].perks.perkStyle.toInt()) +

                                        "/" + Utils.runeMap.getValue(response.body()!!.participants[i].perks.perkIds[0].toInt()) +
                                        "/" + Utils.runeMap2.getValue(response.body()!!.participants[i].perks.perkIds[0].toInt()) + ".png",

                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" +
                                        Utils.subRuneMap.getValue(response.body()!!.participants[i].perks.perkSubStyle.toInt()),

                                response.body()!!.participants[i].summonerName,
                                response.body()!!.participants[i].summonerId
                            )
                        )

                        // ban
                        spectatorAdapter2.setItem(inGameList2)
                    }
                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[0].championId.toInt()) + ".png")
                        .into(ban1)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[1].championId.toInt()) + ".png")
                        .into(ban2)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[2].championId.toInt()) + ".png")
                        .into(ban3)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[3].championId.toInt()) + ".png")
                        .into(ban4)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[4].championId.toInt()) + ".png")
                        .into(ban5)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[5].championId.toInt()) + ".png")
                        .into(ban6)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[6].championId.toInt()) + ".png")
                        .into(ban7)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[7].championId.toInt()) + ".png")
                        .into(ban8)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[8].championId.toInt()) + ".png")
                        .into(ban9)

                    Glide.with(applicationContext)
                        .load("https://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"
                                + Utils.championMap.getValue(response.body()!!.bannedChampions[9].championId.toInt()) + ".png")
                        .into(ban10)
                }
            }
        })
    }
}