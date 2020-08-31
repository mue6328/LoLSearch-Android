package com.example.lolsearch.dto

data class Ranking(
    var queueType: String,
    var summonerName: String,
    var hotStreak: Boolean,
    var wins: Int,
    var veteran: Boolean,
    var losses: Int,
    var rank: String,
    var tier: String,
    var inactive: Boolean,
    var freshBlood: Boolean,
    var leagueId: String,
    var summonerId: String,
    var leaguePoints: Int
)

data class RankingInfo(
    var ranking: String,
    var summonerName: String,
    var summonerTier: String,
    var summonerLP: String,
    var summonerId: String
)