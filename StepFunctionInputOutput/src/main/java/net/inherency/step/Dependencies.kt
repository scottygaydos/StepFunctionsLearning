package net.inherency.step

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * This class essentially handled dependency management.  Spring tends to be slow in Lambdas, so handling dependencies
 * here speeds things up and keeps the much smaller.
 */
class Dependencies {

    companion object {
        val objectMapper = jacksonObjectMapper()
                .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerKotlinModule()
    }
}