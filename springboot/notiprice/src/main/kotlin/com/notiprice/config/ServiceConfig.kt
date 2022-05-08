package com.notiprice.config

import com.notiprice.config.jwt.JwtFilter
import com.notiprice.exception.RestTemplateResponseErrorHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.time.Duration
import java.util.*


@Configuration
@EnableScheduling
@EnableWebSecurity
class ServiceConfig(private val jwtFilter: JwtFilter) : WebSecurityConfigurerAdapter() {
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

    override fun configure(http: HttpSecurity?) {
        http!!.httpBasic().disable().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers("/products*").hasRole("USER")
            .antMatchers("/users*").hasRole("USER")
            .antMatchers("/auth*").permitAll()
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .cors().configurationSource(corsConfigurationSource())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = Collections.singletonList("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}