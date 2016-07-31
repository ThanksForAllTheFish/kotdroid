package org.t4atf.kotdroid.cards.model.generators

import com.fasterxml.jackson.databind.JsonNode
import org.t4atf.kotdroid.cards.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("UNCHECKED_CAST")
fun <T : Card> generates(card: JsonNode): T {
    when (card.get("type_code").asText()) {
        "event" -> return Event(generatesBaseGameCard(card)) as T
        "hardware" -> return Hardware(generatesBaseGameCard(card)) as T
        "resource" -> return Resource(generatesBaseGameCard(card)) as T
        "identity" -> return Identity(generatesBaseCard(card), card.get("influencelimit")?.asInt() ?: Int.MAX_VALUE, card.get("minimumdecksize").asInt()) as T
        "program" -> return Program(generatesBaseGameCard(card), card.get("memoryunits").asInt(), card.get("strength")?.asInt() ?: 0) as T
        "ice" -> return Ice(generatesBaseGameCard(card)) as T
        "asset" -> return Asset(generatesBaseGameCard(card)) as T
        "upgrade" -> return Upgrade(generatesBaseGameCard(card)) as T
        "agenda" -> return Agenda(generatesBaseCard(card), card.get("advancementcost").asInt(), card.get("agendapoints").asInt(), card.get("limited").asInt()) as T
        "operation" -> return Operation(generatesBaseGameCard(card)) as T
        else -> throw RuntimeException("unknown card type '${card.get("type_code").asText()}'")
    }
}

private fun generatesBaseGameCard(card: JsonNode): BaseGameCard = BaseGameCard(generatesBaseCard(card),
        card.get("cost").asInt(), card.get("factioncost").asInt(), card.get("limited").asInt())

private fun generatesBaseCard(card: JsonNode): BaseCard = BaseCard(
        GameData(card.get("subtype_code").asText().split(" - ").map { it.trim() }.toSet(),
                Faction(card.get("faction_code").asText(), card.get("faction_letter").asText()),
                card.get("side_code").asText(), card.get("text")?.asText() ?: "", card.get("uniqueness")?.asBoolean() ?: false),
        Metadata(LocalDateTime.parse(card.get("last-modified").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME), card.get("code").asText(), card.get("title").asText(), card.get("number").asInt(),
                card.get("quantity").asInt(), card.get("set_code")?.asText() ?: "", card.get("cycle_code")?.asText() ?: "", card.get("cyclenumber")?.asInt() ?: 0))
