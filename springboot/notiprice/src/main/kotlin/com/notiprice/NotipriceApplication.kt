package com.notiprice

import com.notiprice.telegram.NotipriceTelegramBot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@SpringBootApplication
class NotipriceApplication

fun main(args: Array<String>) {

    val context = runApplication<NotipriceApplication>(*args)

    val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
    botsApi.registerBot(
        NotipriceTelegramBot(
            context.environment.getProperty("bot.token")!!,
            context.environment.getProperty("bot.username")!!,
            context.environment.getProperty("notiprice.url")!!
        )
    )
}


