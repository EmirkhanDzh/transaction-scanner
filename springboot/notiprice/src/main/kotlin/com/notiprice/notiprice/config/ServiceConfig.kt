package com.notiprice.notiprice.config

import com.notiprice.notiprice.exception.RestTemplateResponseErrorHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Duration

@Configuration
class ServiceConfig
    //: WebMvcConfigurer
{
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

//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**").allowedMethods("*")
//    }
}