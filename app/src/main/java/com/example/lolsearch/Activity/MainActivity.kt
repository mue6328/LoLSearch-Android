package com.example.lolsearch.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lolsearch.R
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.example.lolsearch.DTO.Summoner
import com.example.lolsearch.Service.SummonerApi
import com.example.lolsearch.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import android.util.Log

class MainActivity : AppCompatActivity() {

    var summonerApi: SummonerApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var summonerApi: SummonerApi = Utils.retrofit.create(SummonerApi::class.java)

        summonerApi = Utils.retrofit.create(SummonerApi::class.java)

        var getSummoner = summonerApi!!.getSummoner("RGAPI-86c7a9e0-78e3-4b92-9fbf-47a6b8009047", userName.text.toString())




        search_button.setOnClickListener {
            System.out.println("fff")
            summonerApi!!.getSummoner("RGAPI-86c7a9e0-78e3-4b92-9fbf-47a6b8009047", userName.text.toString()).enqueue(object : Callback<Summoner> {
                override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                    Log.d("success : ", "" + response.body() + response.code() + response.message())
                   // Log.d("success : ", "" + response.body()!!.name + response.body()!!.id)
                }

                override fun onFailure(call: Call<Summoner>, t: Throwable) {
                    Log.d("fail : ", t.toString())
                }
            })
            //val intent = Intent(this, SummonerActivity::class.java)
            //startActivity(intent)
        }
    }
}
