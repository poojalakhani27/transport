package com.mobimeo.transport

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfig {
    @Bean
    open fun restTemplate(): TestRestTemplate {
        return TestRestTemplate()
    }
}