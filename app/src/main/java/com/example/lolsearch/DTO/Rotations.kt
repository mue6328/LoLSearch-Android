package com.example.lolsearch.dto

data class Rotations(
    var freeChampionIdsForNewPlayers : List<Int>,
    var freeChampionIds : List<Int>,
    var maxNewPlayerLevel : Int
)