package com.example.lolsearch.Service

import com.example.lolsearch.dto.Rotations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ChampionApi {
    @GET("/lol/platform/v3/champion-rotations")
    fun getRotations (
            @Header("X-Riot-Token") token: String
    ) : Call<Rotations>
}