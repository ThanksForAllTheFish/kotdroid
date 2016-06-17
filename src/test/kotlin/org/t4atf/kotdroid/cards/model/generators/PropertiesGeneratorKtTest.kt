package org.t4atf.kotdroid.cards.model.generators

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.t4atf.kotdroid.cards.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PropertiesGeneratorKtTest {

    companion object {
        val cardsMetadata = jsonNodeFrom("cards.json")
                .asSequence()
                .map { generates<Card>(it) }

        fun jsonNodeFrom(jsonSource: String) = ObjectMapper().readTree(PropertiesGeneratorKtTest::class.java.classLoader.getResourceAsStream(jsonSource))
    }

    @Test
    fun generatesAll() {
        Assert.assertThat(cardsMetadata.count(), Matchers.`is`(jsonNodeFrom("cards.json")
                .asSequence()
                .count()))
    }

    @Test
    fun generatesSingleBaseGameCard() {
        Assert.assertThat(generates(jsonNodeFrom("basegamecard.json")),
                Matchers.`is`(Operation(
                        BaseGameCard(
                                BaseCard(
                                        GameData(setOf(""), Faction("nbn", "n"), "corp", "Draw 3 cards.", false),
                                        Metadata(LocalDateTime.parse("2016-04-16T17:21:24+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME), "01083", "Anonymous Tip", 83, 2, "core", "core", 1)),
                                0, 1, 3)) as Card)
        )
    }

    @Test
    fun generatesAgenda() {
        Assert.assertThat(generates(jsonNodeFrom("agenda.json")),
                Matchers.`is`(Agenda(
                        BaseCard(
                                GameData(setOf(""), Faction("nbn", "n"), "corp", "When you score Breaking News, give the Runner 2 tags.\n\nWhen the turn on which you scored Breaking News ends, the Runner loses 2 tags.", false),
                                Metadata(LocalDateTime.parse("2016-05-11T18:41:36+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME), "01082", "Breaking News", 82, 2, "core", "core", 1)),
                        2, 1, 3) as Card)
        )
    }

    @Test
    fun generatesSingleIdentity() {
        Assert.assertThat(generates(jsonNodeFrom("identity.json")),
                Matchers.`is`(Identity(
                        BaseCard(
                                GameData(setOf("megacorp"), Faction("weyland-consortium", "w"), "corp", "Gain 1[Credits] whenever you play a <strong>transaction</strong> operation.", false),
                                Metadata(LocalDateTime.parse("2016-04-21T18:43:24+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME), "01093", "Weyland Consortium: Building a Better World", 93, 1, "core", "core", 1)),
                        15, 45) as Card)
        )
    }

    @Test
    fun generatesSingleProgram() {
        Assert.assertThat(generates(jsonNodeFrom("program.json")),
                Matchers.`is`(Program(
                        BaseGameCard(
                                BaseCard(
                                        GameData(setOf("virus"), Faction("anarch", "a"), "runner", "Place 2 virus counters on Imp when it is installed.\nOnce per turn, you may remove 1 hosted virus counter to trash a card you access at no cost (even if it cannot normally be trashed).", false),
                                        Metadata(LocalDateTime.parse("2016-04-27T18:36:15+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME), "02003", "Imp", 3, 3, "wla", "genesis", 2)),
                                2, 3, 3),
                        1, 0) as Card
                ))
    }

    @Test
    fun generatesEvents() {
        Assert.assertThat(cardsMetadata
                .filter { it is Event }
                .count(), Matchers.`is`(102))
    }

    @Test
    fun generatesIdentities() {
        Assert.assertThat(cardsMetadata
                .filter { it is Identity }
                .count(), Matchers.`is`(68))
    }

    @Test
    fun generatesPrograms() {
        Assert.assertThat(cardsMetadata
                .filter { it is Program }
                .count(), Matchers.`is`(116))
    }

    @Test
    fun generatesHardwares() {
        Assert.assertThat(cardsMetadata
                .filter { it is Hardware }
                .count(), Matchers.`is`(68))
    }

    @Test
    fun generatesResources() {
        Assert.assertThat(cardsMetadata
                .filter { it is Resource }
                .count(), Matchers.`is`(111))
    }

    @Test
    fun generatesIces() {
        Assert.assertThat(cardsMetadata
                .filter { it is Ice }
                .count(), Matchers.`is`(147))
    }

    @Test
    fun generatesAssets() {
        Assert.assertThat(cardsMetadata
                .filter { it is Asset }
                .count(), Matchers.`is`(104))
    }

    @Test
    fun generatesUpgrades() {
        Assert.assertThat(cardsMetadata
                .filter { it is Upgrade }
                .count(), Matchers.`is`(50))
    }

    @Test
    fun generatesAgendas() {
        Assert.assertThat(cardsMetadata
                .filter { it is Agenda }
                .count(), Matchers.`is`(79))
    }

    @Test
    fun generatesOperations() {
        Assert.assertThat(cardsMetadata
                .filter { it is Operation }
                .count(), Matchers.`is`(87))
    }
}