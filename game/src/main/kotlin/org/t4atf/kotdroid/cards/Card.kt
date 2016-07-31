package org.t4atf.kotdroid.cards

import java.time.LocalDateTime

interface Card

data class GameData(val subtype: Set<String>,
                    val faction: Faction,
                    val side: String,
                    val text: String,
                    val uniqueness: Boolean)

data class Metadata(val lastModified: LocalDateTime, val code: String, val title: String, val number: Int, val quantity: Int, val set: String,
                    val cycle: String, val cycleNumber: Int) //TODO: together

data class BaseCard(val gameData: GameData, val metadata: Metadata)

data class BaseGameCard(
        val baseCard: BaseCard,
        val cost: Int, val factionCost: Int, val limited: Int)

data class Faction(val code: String, val letter: String)

data class Identity(val base: BaseCard, val influenceLimit: Int, val minimumDeckSize: Int) : Card

data class Event(val base: BaseGameCard) : Card

data class Program(val base: BaseGameCard, val memoryUnits: Int, val strength: Int) : Card //not all programs have strength

data class Hardware(val base: BaseGameCard) : Card

data class Resource(val base: BaseGameCard) : Card

data class Ice(val base: BaseGameCard) : Card

data class Asset(val base: BaseGameCard) : Card

data class Upgrade(val base: BaseGameCard) : Card

data class Agenda(val base: BaseCard, val advancementTokens: Int, val agendaPoints: Int, val limited: Int) : Card

data class Operation(val base: BaseGameCard) : Card

data class Cards<T : Card>(val cards : List<T>)
