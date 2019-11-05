package com.example.lolsearch.DTO

data class Summoner(
    var profileIconId : Int,
    var name : String,
    var puuid : String,
    var summonerLevel : Long,
    var revisionDate : Long,
    var id : String,
    var accountId : String
)