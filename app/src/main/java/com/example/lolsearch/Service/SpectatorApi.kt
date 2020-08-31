package com.example.lolsearch.Service

import com.example.lolsearch.dto.Spectator
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpectatorApi {
    @GET("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    fun getActiveGame(
        @Header("X-Riot-Token") token: String,
        @Path("encryptedSummonerId") summonerId: String
    ) : Call<Spectator>
}