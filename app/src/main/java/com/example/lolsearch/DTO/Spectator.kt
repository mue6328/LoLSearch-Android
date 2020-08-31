package com.example.lolsearch.dto

data class Spectator(
    var gameId: Long,
    var gameStartTime: Long,
    var platformId: String,
    var gameMode: String,
    var mapId: Long,
    var gameType: String,
    var bannedChampions: List<BannedChampion>,
    var observers: Observer,
    var participants: List<CurrentGameParticipant>,
    var gameLength: Long,
    var gameConfigId: Long
)

data class BannedChampion(
    var pickTurn: Int,
    var championId: Long,
    var teamId: Long
)

data class Observer(
    var encryptionKey: String
)

data class CurrentGameParticipant(
    var profileIconId: Long,
    var championId: Long,
    var summonerName: String,
    var gameCustomizationObjects: List<GameCustomizationObject>,
    var bot: Boolean,
    var perks: Perks,
    var spell2Id: Long,
    var teamId: Long,
    var spell1Id: Long,
    var summonerId: String
)

data class GameCustomizationObject(
    var category: String,
    var content: String
)

data class Perks(
    var perkStyle: Long,
    var perkIds: List<Long>,
    var perkSubStyle: Long
)

data class inGame(
    var championImage: String,
    var spell1: String,
    var spell2: String,
    var mainRune: String,
    var subRune: String,
    var summonerName: String,
    var summonerId: String
)

data class inGameBans(
    var ban1: String,
    var ban2: String,
    var ban3: String,
    var ban4: String,
    var ban5: String
)