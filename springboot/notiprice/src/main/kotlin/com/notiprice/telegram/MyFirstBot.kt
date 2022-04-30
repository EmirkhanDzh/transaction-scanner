package com.notiprice.telegram

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class MyFirstBot : TelegramLongPollingBot() {
    override fun getBotToken() = "5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w"

    override fun getBotUsername() = "nur312_bot"

    override fun onUpdateReceived(update: Update) {

        if (update.hasMessage() && update.message.hasText()) {

            if(update.message.text == commandSignUp) {
                handleSignUp(update.message.chatId.toString())
            }
//            val message = SendMessage()
//            message.chatId = update.message.chatId.toString()
//
//            val lines = update.message.text.split(" ")
////            message.text = getValueByXpath(url = lines[0], xpath = lines[1]) ?: "error"
//
//            message.text = message.chatId
        }
    }

    private fun handleSignUp(chatId: String) {
        val message = SendMessage()
        message.chatId = chatId
        message.text = "Please go to http://localhost:3000/sign-up/$chatId"
        execute(message)
    }

    companion object {
        const val commandSignUp = "/signup"
    }
}