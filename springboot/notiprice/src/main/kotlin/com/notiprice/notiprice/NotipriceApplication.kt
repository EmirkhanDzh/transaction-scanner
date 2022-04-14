	package com.notiprice.notiprice

import com.notiprice.notiprice.bot.telegram.MyFirstBot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

	@SpringBootApplication
class NotipriceApplication

fun main(args: Array<String>) {
	val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
	botsApi.registerBot(MyFirstBot())

	runApplication<NotipriceApplication>(*args)
}
