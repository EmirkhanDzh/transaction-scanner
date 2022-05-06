package com.notiprice.telegram
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class MyFirstBot(
    private val botToken: String,
    private val botUsername: String,
    private val notipriceUrl: String
) : TelegramLongPollingBot() {
    override fun getBotToken() = botToken

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update) {

        if (update.hasMessage() && update.message.hasText()) {

            if (update.message.text == commandSignUp) {
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
        message.text = "Please go to $notipriceUrl/sign-up/$chatId"
        execute(message)
    }

    companion object {
        const val commandSignUp = "/signup"
    }
}