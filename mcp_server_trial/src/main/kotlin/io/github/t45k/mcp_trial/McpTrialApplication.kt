package io.github.t45k.mcp_trial

import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@SpringBootApplication
class McpTrialApplication {
    @Bean
    fun weatherTools(weatherService: WeatherService): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder()
            .toolObjects(weatherService)
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<McpTrialApplication>(*args)
}

@Service
class WeatherService {
    @Tool(description = "Get weather information by city name")
    fun weather(cityName: String): String =
        when (cityName) {
            "Tokyo" -> "Sunny"
            "Osaka" -> "Rainy"
            "Fukuoka" -> "Cloudy"
            else -> "Unknown"
        }
}
