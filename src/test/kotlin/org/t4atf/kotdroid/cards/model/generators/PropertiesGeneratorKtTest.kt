package org.t4atf.kotdroid.cards.model.generators

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.t4atf.kotdroid.cards.*

class PropertiesGeneratorKtTest {

    companion object {
        lateinit var cardsMetadata: Sequence<Card?>

        @BeforeClass @JvmStatic fun init() {
            val resourceAsStream = PropertiesGeneratorKtTest::class.java.classLoader.getResourceAsStream("cards.json")
            cardsMetadata = ObjectMapper().readTree(resourceAsStream)
                    .asSequence()
                    .map { generates(it) }
        }
    }

    @Test
    fun generatesAll() {
        Assert.assertThat(cardsMetadata.count(), Matchers.`is`(ObjectMapper().readTree(PropertiesGeneratorKtTest::class.java.classLoader.getResourceAsStream("cards.json"))
                .asSequence()
                .count()))
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