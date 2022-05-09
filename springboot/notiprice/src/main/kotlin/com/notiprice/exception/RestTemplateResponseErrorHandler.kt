package com.notiprice.exception

import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.ResponseErrorHandler

private val log = KotlinLogging.logger {}

/**
 * Перехватывает исключения из RestTemplate.
 */
@Component
class RestTemplateResponseErrorHandler : ResponseErrorHandler {

    /**
     * Является ли ответ ошибкой.
     */
    override fun hasError(httpResponse: ClientHttpResponse): Boolean =
        httpResponse.statusCode.is4xxClientError || httpResponse.statusCode.is5xxServerError

    /**
     * Обработка ошибки.
     */
    override fun handleError(httpResponse: ClientHttpResponse) {
        if (httpResponse.statusCode.is4xxClientError) {
            log.warn("Bad request to the external server", httpResponse)
        } else if (httpResponse.statusCode.is5xxServerError) {
            log.error("The external server error", httpResponse)
        }
    }
}