package com.example.lolsearch.Service

import com.example.lolsearch.dto.League
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface LeagueApi {
    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    fun getSummonerLeague(
        @Header("X-Riot-Token") token: String,
        @Path("encryptedSummonerId") id: String
    ) : Call<List<League>>
}