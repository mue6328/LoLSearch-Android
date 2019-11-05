package com.example.lolsearch.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.lolsearch.R
import com.example.lolsearch.Service.SummonerApi
import kotlinx.android.synthetic.main.summoner_activity.*

class SummonerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.summoner_activity)

        var summonerApi: SummonerApi

//        val postLogin = service.postLogin(
//            id.getText().toString(),
//            pw.getText().toString()
//        )
//
//        var getSummoner = summonerApi.getSummoner("ff")
//
//        getSummoner.enqueue()
//        summonerName.text =
    }
}