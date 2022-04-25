package com.notiprice.telegram

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class MyFirstBot : TelegramLongPollingBot() {
    override fun getBotToken() = "5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w"

    override fun getBotUsername() = "nur312_bot"

    override fun onUpdateReceived(update: Update) {

        if (update.hasMessage() && update.message.hasText()) {
            val message = SendMessage()
            message.chatId = update.message.chatId.toString()
            val lines = update.message.text.split(" ")
//            message.text = getValueByXpath(url = lines[0], xpath = lines[1]) ?: "error"

            message.text = message.chatId

            execute(message)
        }
    }


}