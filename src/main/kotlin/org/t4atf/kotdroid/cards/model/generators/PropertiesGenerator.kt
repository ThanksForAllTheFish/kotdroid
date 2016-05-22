package org.t4atf.kotdroid.cards.model.generators

import com.fasterxml.jackson.databind.JsonNode
import org.t4atf.kotdroid.cards.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun generates(card: JsonNode): Card? {
     when(card.get("type_code").asText()) {
        "event" -> return Event(generatesBaseGameCard(card))
        "hardware" -> return Hardware(generatesBaseGameCard(card))
        "resource" -> return Resource(generatesBaseGameCard(card))
        "identity" -> return Identity(generatesBaseCard(card), card.get("influencelimit")?.asInt()?:Int.MAX_VALUE, card.get("minimumdecksize").asInt())
        "program" -> return Program(generatesBaseGameCard(card), card.get("memoryunits").asInt(), card.get("strength")?.asInt()?:0)
        "ice" -> return Ice(generatesBaseGameCard(card))
        "asset" -> return Asset(generatesBaseGameCard(card))
        "upgrade" -> return Upgrade(generatesBaseGameCard(card))
        "agenda" -> return Agenda(generatesBaseCard(card), card.get("advancementcost").asInt(), card.get("agendapoints").asInt(), card.get("limited").asInt())
        "operation" -> return Operation(generatesBaseGameCard(card))
        else -> throw RuntimeException("unknown card type '${card.get("type_code").asText()}'")
    }
}

private fun generatesBaseGameCard(card: JsonNode): BaseGameCard = BaseGameCard(generatesBaseCard(card),
        card.get("cost").asInt(), card.get("factioncost").asInt(), card.get("limited").asInt())

private fun generatesBaseCard(card: JsonNode): BaseCard = BaseCard(
        GameData(card.get("code").asText(), card.get("code").asText().split("-").map { it.trim() }.asSequence(),
            Faction(card.get("faction_code").asText(), card.get("faction_letter").asText()),
            card.get("side").asText(), card.get("text")?.asText()?:"", card.get("uniqueness")?.asBoolean()?:false),
        Metadata(LocalDateTime.parse(card.get("last-modified").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME), card.get("title").asText(), card.get("number").asInt(),
                card.get("quantity").asInt(), card.get("set")?.asText()?:"", card.get("cycle")?.asText()?:"", card.get("cycleNumber")?.asInt()?:0))
