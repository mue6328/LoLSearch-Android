package com.example.lolsearch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lolsearch.R
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.example.lolsearch.dto.Summoner
import com.example.lolsearch.Service.SummonerApi
import com.example.lolsearch.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolsearch.adapter.RotationAdapter
import com.example.lolsearch.dto.ChampionNumber
import com.example.lolsearch.dto.Rotations
import com.example.lolsearch.Service.ChampionApi
import com.example.lolsearch.Utils.Companion.championMap
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList
import android.widget.TextView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_Rank -> {
                startActivity(Intent(this, RankingActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private var rotationList = ArrayList<ChampionNumber>()

    var summonerApi: SummonerApi? = null
    var championApi: ChampionApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        summonerApi = Utils.retrofit.create(SummonerApi::class.java)
        championApi = Utils.retrofit.create(ChampionApi::class.java)

        var rotationRecyclerView : RecyclerView = findViewById(R.id.rotationChampions)

        var rotationAdapter = RotationAdapter()

        rotationRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rotationRecyclerView.setHasFixedSize(true)
        rotationRecyclerView.adapter = rotationAdapter

        championApi!!.getRotations(Utils.riotToken).enqueue(object : Callback<Rotations> {
            override fun onFailure(call: Call<Rotations>, t: Throwable) {

            }

            override fun onResponse(call: Call<Rotations>, response: Response<Rotations>) {
                Log.d(
                    "success getRotations : ",
                    "" + response.body() + response.code() + response.message()
                )
                Log.d("tag", "" + championMap.size)
                if (response.body() != null) {
                    for (i in response.body()!!.freeChampionIds.indices) {
                        //Log.d("tag", "" + response.body()!!.freeChampionIds[i])
                        rotationList.add(
                            ChampionNumber(
                                Utils.version_champion
                                        + championMap.getValue(response.body()!!.freeChampionIds[i]) + ".png"
                            )
                        )
                    }
                    rotationAdapter.setItem(rotationList)
                }
            }
        })

        search_button.setOnClickListener {
            summonerApi!!.getSummoner(Utils.riotToken, userName.text.toString()).enqueue(object : Callback<Summoner> {
                override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                    if(response.body() == null) {
                        if (response.code() == 500) Toast.makeText(this@MainActivity, "서버 오류", Toast.LENGTH_SHORT).show()
                        else if (response.code() == 429) Toast.makeText(this@MainActivity, "잠시 후 시도해주세요.", Toast.LENGTH_SHORT).show()
                        else if (response.code() == 404) Toast.makeText(this@MainActivity, "입력하신 소환사는 존재하지 않는 소환사입니다.", Toast.LENGTH_SHORT).show()
                        else Toast.makeText(this@MainActivity, response.message() + " " + response.code(), Toast.LENGTH_SHORT).show()

                    }
                    else {
                        val intent = Intent(it.context, SummonerActivity::class.java)
                        intent.putExtra("userId", response.body()!!.id)
                        intent.putExtra("profileImage", response.body()!!.profileIconId.toString())
                        intent.putExtra("name", response.body()!!.name)
                        intent.putExtra("level", response.body()!!.summonerLevel.toString())
                        intent.putExtra("accountId", response.body()!!.accountId)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Summoner>, t: Throwable) {
                    Log.d("fail : ", t.toString())
                }
            })
        }

        nav_view.setNavigationItemSelectedListener(this)
    }
}
