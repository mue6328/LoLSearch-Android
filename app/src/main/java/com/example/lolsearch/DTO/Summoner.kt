package com.example.lolsearch.dto

data class Summoner(
    var profileIconId : Int,
    var name : String,
    var puuid : String,
    var summonerLevel : Long,
    var revisionDate : Long,
    var id : String,
    var accountId : String
)

data class SummonerLeagueData(
    var queueType: String,
    var wins: Int,
    var losses: Int,
    var rank: String,
    var tier: String,
    var leaguePoints: Int
)