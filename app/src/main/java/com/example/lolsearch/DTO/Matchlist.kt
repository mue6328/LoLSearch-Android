package com.example.lolsearch.dto

data class Matchlist(
    var matches: List<MatchReference>,
    var totalGames: Int,
    var startIndex: Int,
    var endIndex: Int
)

data class MatchReference(
    var lane: String,
    var gameId: Long,
    var champion: Int,
    var platformId: String,
    var season: Int,
    var queue: Int,
    var role: String,
    var timestamp: Long
)

data class MatchInfo(
    var championNumber: String,
    var item0: String,
    var item1: String,
    var item2: String,
    var item3: String,
    var item4: String,
    var item5: String,
    var spell1Id: String,
    var spell2Id: String,
    var mainRune: String,
    var subRune: String,
    var kills: Int,
    var deaths: Int,
    var assists: Int,
    var gameDuration: Long,
    var win: Boolean,
    var matchQueueType: Int,
    var gameId: String
)

// spell hashMap

data class Match(
    var seasonId: Int,
    var queueId: Int,
    var gameId: Long,
    var participantIdentities: List<ParticipantIdentity>,
    var gameVersion: String,
    var platformId: String,
    var gameMode: String,
    var mapId: Int,
    var gameType: String,
    var teams: List<TeamStats>,
    var participants: List<Participant>,
    var gameDuration: Long,
    var gameCreation: Long
)

data class ParticipantIdentity(
    var player: Player,
    var participantId: Int
)

data class Player(
    var currentPlatformId: String,
    var summonerName: String,
    var matchHistoryUri: String,
    var platformId: String,
    var currentAccountId: String,
    var profileIcon: Int,
    var summonerId: String,
    var accountId: String
)

data class TeamStats(
    var firstDragon: Boolean,
    var firstInhibitor: Boolean,
    var bans: List<TeamBans>,
    var baronKills: Int,
    var firstRiftHeraId: Boolean,
    var firstBaron: Boolean,
    var riftHeraldKills: Int,
    var firstBlood: Boolean,
    var teamId: Int,
    var firstTower: Boolean,
    var vilemawKills: Int,
    var inhibitorKills: Int,
    var towerKills: Int,
    var dominionVictoryScore: Int,
    var win: String,
    var dragonKills: Int
)

data class TeamBans(
    var pickTurn: Int,
    var championId: Int
)

data class Participant(
    var stats: ParticipantStats,
    var participantId: Int,
    var runes: List<Rune>,
    var timeline: ParticipantTimeline,
    var teamId: Int,
    var spell2Id: Int,
    var masteries: List<Mastery>,
    var highestAchievedSeasonTier: String,
    var spell1Id: Int,
    var championId: Int
)

data class ParticipantStats(
    var firstBloodAssist: Boolean,
    var visionScore: Long,
    var magicDamageDealtToChampions: Long,
    var damageDealtToObjectives: Long,
    var totalTimeCrowdControlDealt: Int,
    var longestTimeSpentLiving: Int,
    var perk1Var1: Int,
    var perk1Var3: Int,
    var perk1Var2: Int,
    var tripleKills: Int,
    var perk3Var3: Int,
    var nodeNeutralizeAssist: Int,
    var perk3Var2: Int,
    var playerScore9: Int,
    var playerScore8: Int,
    var kills: Int,
    var playerScore1: Int,
    var playerScore3: Int,
    var playerScore2: Int,
    var playerScore5: Int,
    var playerScore4: Int,
    var playerScore7: Int,
    var playerScore6: Int,
    var perk5Var1: Int,
    var perk5Var3: Int,
    var perk5Var2: Int,
    var totalScoreRank: Int,
    var neutralMinionsKilled: Int,
    var damageDealtToTurrets: Long,
    var physicalDamageDealtToChampions: Long,
    var nodeCapture: Int,
    var largestMultiKill: Int,
    var perk2Var2: Int,
    var perk2Var3: Int,
    var totalUnitsHealed: Int,
    var perk2Var1: Int,
    var perk4Var1: Int,
    var perk4Var2: Int,
    var perk4Var3: Int,
    var wardsKilled: Int,
    var largestCriticalStrike: Int,
    var largestKillingSpree: Int,
    var quadraKills: Int,
    var teamObjective: Int,
    var magicDamageDealt: Long,
    var item2: Int,
    var item3: Int,
    var item0: Int,
    var neutralMinionsKilledTeamJungle: Int,
    var item6: Int,
    var item4: Int,
    var item5: Int,
    var perk1: Int,
    var perk0: Int,
    var perk3: Int,
    var perk2: Int,
    var perk5: Int,
    var perk4: Int,
    var perk3Var1: Int,
    var damageSelfMitigated: Long,
    var magicalDamageTaken: Long,
    var firstInhibitorKill: Boolean,
    var trueDamageTaken: Long,
    var nodeNeutralize: Int,
    var assists: Int,
    var combatPlayerScore: Int,
    var perkPrimaryStyle: Int,
    var goldSpent: Int,
    var trueDamageDealt: Long,
    var participantId: Int,
    var totalDamageTaken: Long,
    var physicalDamageDealt: Long,
    var sightWardsBoughtInGame: Int,
    var totalDamageDealtToChampions: Long,
    var physicalDamageTaken: Long,
    var totalPlayerScore: Int,
    var win: Boolean,
    var objectivePlayerScore: Int,
    var totalDamageDealt: Long,
    var item1: Int,
    var neutralMinionsKilledEnemyJungle: Int,
    var deaths: Int,
    var wardsPlaced: Int,
    var perkSubStyle: Int,
    var turretKills: Int,
    var firstBloodKill: Boolean,
    var trueDamageDealtToChampions: Long,
    var goldEarned: Int,
    var killingSprees: Int,
    var unrealKills: Int,
    var altarsCaptured: Int,
    var firstTowerAssist: Boolean,
    var firstTowerKill: Boolean,
    var champLevel: Int,
    var doubleKills: Int,
    var nodeCaptureAssist: Int,
    var inhibitorKills: Int,
    var firstInhibitorAssist: Boolean,
    var perk0Var1: Int,
    var perk0Var2: Int,
    var perk0Var3: Int,
    var visionWardsBoughtInGame: Int,
    var altarsNeutralized: Int,
    var pentaKills: Int,
    var totalHeal: Long,
    var totalMinionsKilled: Int,
    var timeCCingOthers: Long
)

data class Rune(
    var runeId: Int,
    var rank: Int
)

data class ParticipantTimeline(
    var lane: String,
    var participantId: Int,
    var csDiffPerMinDeltas: Map<String, Double>,
    var goldPerMinDeltas: Map<String, Double>,
    var xpDiffPerMinDeltas: Map<String, Double>,
    var creepsPerMinDeltas: Map<String, Double>,
    var xpPerMinDeltas: Map<String, Double>,
    var role: String,
    var damageTakenDiffPerMinDeltas: Map<String, Double>,
    var damageTakenPerMinDeltas: Map<String, Double>
)

data class Mastery(
    var masteryId: Int,
    var rank: Int
)

data class MatchDetail (
    var championId: String,
    var spell1Id: String,
    var spell2Id: String,
    var summonerName: String,
    var kills: Int,
    var deaths: Int,
    var assists: Int,
    var item0: String,
    var item1: String,
    var item2: String,
    var item3: String,
    var item4: String,
    var item5: String,
    var goldEarned: Int,
    var cs: Int,
    var championDealt: Long,
    var mainRune: String,
    var subRune: String,
    var summonerId: String,
    var tier: String
)