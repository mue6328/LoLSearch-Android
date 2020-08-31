package com.example.lolsearch.Service

import com.example.lolsearch.dto.Match
import com.example.lolsearch.dto.Matchlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MatchApi {
    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    fun getMatchLists (
        @Header("X-Riot-Token") token: String,
        @Path("encryptedAccountId") AccountId: String
    ) : Call<Matchlist>

    @GET("/lol/match/v4/matches/{matchId}")
    fun getMatches (
        @Header("X-Riot-Token") token: String,
        @Path("matchId") MatchId: Long
    ) : Call<Match>
}