package com.notiprice.notiprice.config

import com.notiprice.notiprice.exception.RestTemplateResponseErrorHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
@EnableScheduling
class ServiceConfig
{
    /**
     * "Instances of the JdbcTemplate class are threadsafe once configured"
     * https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/jdbc.html
     */
    @Bean
    fun restTemplate(
        builder: RestTemplateBuilder,
        @Value("\${timeout.seconds.connect}") connectTimeout: Long,
        @Value("\${timeout.seconds.read}") readTimeout: Long
    ): RestTemplate = builder
        .errorHandler(RestTemplateResponseErrorHandler())
        .setConnectTimeout(Duration.ofSeconds(connectTimeout))
        .setReadTimeout(Duration.ofSeconds(readTimeout))
        .build()
}