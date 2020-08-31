package com.example.lolsearch.Service

import com.example.lolsearch.dto.Summoner
import retrofit2.Call
import retrofit2.http.*

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(
        @Header("X-Riot-Token") token: String,
        @Path("summonerName") summonerName: String
    ) : Call<Summoner>

}