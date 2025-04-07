package io.github.t45k.mcp_trial

import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class McpTrialApplication

fun main(args: Array<String>) {
    runApplication<McpTrialApplication>(*args)
}

@RestController
class McpClientController(mcpSyncClients: List<McpSyncClient>) {
    private val mcpSyncClient = mcpSyncClients.first()

    @GetMapping("/weather")
    fun getWeather(@RequestParam(name = "city_name") cityName: String): Any {
        return mcpSyncClient.callTool(
            McpSchema.CallToolRequest(
                "weather",
                mapOf("cityName" to cityName),
            )
        ).content
    }
}
