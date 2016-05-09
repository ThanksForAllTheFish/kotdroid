package org.t4atf.kotdroid.data.loader

import com.fasterxml.jackson.databind.JsonNode

interface DroidReader {

    fun readCards(): JsonNode
}
