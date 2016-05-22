package org.t4atf.kotdroid.cards.loader

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api")
class RemoteDroidReader @Autowired constructor(private val reader: RestTemplate): DroidReader {

    @RequestMapping(path = arrayOf("/cards"), method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    override fun readCards(): JsonNode {

        return reader.exchange("https://netrunnerdb.com/api/cards", HttpMethod.GET, HttpEntity.EMPTY, object : ParameterizedTypeReference<JsonNode>() {}).body
    }
}
