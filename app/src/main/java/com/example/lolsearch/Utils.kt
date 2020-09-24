package com.example.lolsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    companion object {
        private const val BASE_URL = "https://kr.api.riotgames.com"

        val version_champion = "https://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/"

        val version_spell = "https://ddragon.leagueoflegends.com/cdn/10.19.1/img/spell/"

        val version_item = "https://ddragon.leagueoflegends.com/cdn/10.19.1/img/item/"

        val championMap = hashMapOf(-1 to "null", 1 to "Annie", 2 to "Olaf", 3 to "Galio", 4 to "TwistedFate",
            5 to "XinZhao", 6 to "Urgot", 7 to "Leblanc", 8 to "Vladimir", 9 to "Fiddlesticks", 10 to "Kayle", 11 to "MasterYi",
            12 to "Alistar", 13 to "Ryze", 14 to "Sion", 15 to "Sivir", 16 to "Soraka", 17 to "Teemo", 18 to "Tristana", 19 to "Warwick",
            20 to "Nunu", 21 to "MissFortune", 22 to "Ashe", 23 to "Tryndamere", 24 to "Jax", 25 to "Morgana", 26 to "Zilean",
            27 to "Singed", 28 to "Evelynn", 29 to "Twitch", 30 to "Karthus", 31 to "Chogath", 32 to "Amumu", 33 to "Rammus",
            34 to "Anivia", 35 to "Shaco", 36 to "DrMundo", 37 to "Sona", 38 to "Kassadin", 39 to "Irelia", 40 to "Janna",
            41 to "Gangplank", 42 to "Corki", 43 to "Karma", 44 to "Taric", 45 to "Veigar", 48 to "Trundle", 50 to "Swain",
            51 to "Caitlyn", 53 to "Blitzcrank", 54 to "Malphite", 55 to "Katarina", 56 to "Nocturne", 57 to "Maokai",
            58 to "Renekton", 59 to "JarvanIV", 60 to "Elise", 61 to "Orianna", 62 to "MonkeyKing", 63 to "Brand", 64 to "LeeSin",
            67 to "Vayne", 68 to "Rumble", 69 to "Cassiopeia", 72 to "Skarner", 74 to "Heimerdinger", 75 to "Nasus", 76 to "Nidalee",
            77 to "Udyr", 78 to "Poppy", 79 to "Gragas", 80 to "Pantheon", 81 to "Ezreal", 82 to "Mordekaiser", 83 to "Yorick",
            84 to "Akali", 85 to "Kennen", 86 to "Garen", 89 to "Leona", 90 to "Malzahar", 91 to "Talon", 92 to "Riven",
            96 to "KogMaw", 98 to "Shen", 99 to "Lux", 101 to "Xerath", 102 to "Shyvana", 103 to "Ahri", 104 to "Graves",
            105 to "Fizz", 106 to "Volibear", 107 to "Rengar", 110 to "Varus", 111 to "Nautilus", 112 to "Viktor", 113 to "Sejuani",
            114 to "Fiora", 115 to "Ziggs", 117 to "Lulu", 119 to "Draven", 120 to "Hecarim", 121 to "Khazix", 122 to "Darius",
            126 to "Jayce", 127 to "Lissandra", 131 to "Diana", 133 to "Quinn", 134 to "Syndra", 136 to "AurelionSol",
            141 to "Kayn", 142 to "Zoe", 143 to "Zyra", 145 to "Kaisa", 150 to "Gnar", 154 to "Zac", 157 to "Yasuo",
            161 to "Velkoz", 163 to "Taliyah", 164 to "Camille", 201 to "Braum", 202 to "Jhin", 203 to "Kindred",
            222 to "Jinx", 223 to "TahmKench", 235 to "Senna", 236 to "Lucian", 240 to "Kled", 238 to "Zed",
            245 to "Ekko", 246 to "Qiyana", 254 to "Vi", 266 to "Aatrox", 267 to "Nami", 350 to "Yuumi", 360 to "Samira",
            268 to "Azir", 412 to "Thresh", 420 to "Illaoi", 421 to "RekSai",
            427 to "Ivern", 429 to "Kalista", 432 to "Bard", 497 to "Rakan", 498 to "Xayah",
            516 to "Ornn", 517 to "Sylas", 518 to "Neeko", 523 to "Aphelios", 555 to "Pyke", 777 to "Yone",
            875 to "Sett", 876 to "Lillia")

        val spellMap = hashMapOf(1 to "SummonerBoost", 3 to "SummonerExhaust", 4 to "SummonerFlash",
            6 to "SummonerHaste", 7 to "SummonerHeal", 11 to "SummonerSmite", 12 to "SummonerTeleport", 13 to "SummonerMana",
            14 to "SummonerDot", 21 to "SummonerBarrier", 30 to "SummonerPoroRecall", 31 to "SummonerPoroThrow",
            32 to "SummonerSnowball", 39 to "SummonerSnowURFSnowball_Mark", 50 to "SummonerOdysseyRevive", 52 to "SummonerOdysseyFlash")

        val runeMap = hashMapOf(
            8000 to "Precision", 8005 to "PressTheAttack", 8008 to "LethalTempo", 8010 to "Conqueror", 8021 to "FleetFootwork",

            8100 to "Domination", 8112 to "Electrocute", 8124 to "Predator", 8128 to "DarkHarvest", 9923 to "HailOfBlades",

            8200 to "Sorcery", 8214 to "SummonAery", 8229 to "ArcaneComet", 8230 to "PhaseRush",

            8300 to "Inspiration", 8351 to "GlacialAugment", 8358 to "MasterKey", 8359 to "Kleptomancy", 8360 to "UnsealedSpellbook",

            8400 to "Resolve", 8437 to "GraspOfTheUndying", 8439 to "VeteranAftershock", 8465 to "Guardian"
        )

        val runeMap2 = hashMapOf(
            8000 to "Precision", 8005 to "PressTheAttack", 8008 to "LethalTempoTemp", 8010 to "Conqueror", 8021 to "FleetFootwork",

            8100 to "Domination", 8112 to "Electrocute", 8124 to "Predator", 8128 to "DarkHarvest", 9923 to "HailOfBlades",

            8200 to "Sorcery", 8214 to "SummonAery", 8229 to "ArcaneComet", 8230 to "PhaseRush",

            8300 to "Inspiration", 8351 to "GlacialAugment", 8358 to "MasterKey", 8359 to "Kleptomancy", 8360 to "UnsealedSpellbook",

            8400 to "Resolve", 8437 to "GraspOfTheUndying", 8439 to "VeteranAftershock", 8465 to "Guardian"
        )

        val subRuneMap = hashMapOf(8000 to "7201_Precision.png",
            8100 to "7200_Domination.png", 8200 to "7202_Sorcery.png", 8300 to "7203_Whimsy.png", 8400 to "7204_Resolve.png")

        const val riotToken = "RGAPI-0be96474-9ea6-418a-983a-a12eba4b1bdd"

        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

