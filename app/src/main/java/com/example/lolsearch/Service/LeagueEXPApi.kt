package com.example.lolsearch.Service

import com.example.lolsearch.dto.Ranking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface LeagueEXPApi {
    @GET("/lol/league-exp/v4/entries/{queue}/{tier}/{division}")
    fun getleagueRanking(
        @Header("X-Riot-Token") token: String,
        @Path("queue") queue: String,
        @Path("tier") tier: String,
        @Path("division") division: String,
        @Query("page") page: Int
    ) : Call<List<Ranking>>
}