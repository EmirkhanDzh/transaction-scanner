package com.notiprice

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

open class TestContainer {

    companion object {
        @JvmStatic
        @Container
        val container: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13-alpine")
    }
}