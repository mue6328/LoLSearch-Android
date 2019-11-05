package com.example.lolsearch.DTO

data class League(
    var queueType: String,
    var summonerName: String,
    var hotStreak: Boolean,
    var miniSeries: MiniSeries,
    var wins: Int,
    var veteran: Boolean,
    var losses: Int,
    var rank: String,
    var leagueId: String,
    var inactive: Boolean,
    var freshBlood: Boolean,
    var tier: String,
    var summonerId: String,
    var leaguePoints: Int
)

data class MiniSeries(
    var progress: String,
    var losses: Int,
    var target: Int,
    var wins: Int
)