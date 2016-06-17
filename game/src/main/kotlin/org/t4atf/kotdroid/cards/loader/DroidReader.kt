package org.t4atf.kotdroid.cards.loader

import com.fasterxml.jackson.databind.JsonNode

interface DroidReader {

    fun readCards(): JsonNode
}
