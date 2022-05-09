package com.notiprice.telegram
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Телеграм бот. Обрабатывает запросы пользователей в чате.
 */
class NotipriceTelegramBot(
    private val botToken: String,
    private val botUsername: String,
    private val notipriceUrl: String
) : TelegramLongPollingBot() {
    /**
     * Возвращает токен бота.
     */
    override fun getBotToken() = botToken

    /**
     * Возвращает пользовательское имя бота.
     */
    override fun getBotUsername() = botUsername

    /**
     * Обработка запросов пользователей.
     */
    override fun onUpdateReceived(update: Update) {
        if (!(update.hasMessage() && update.message.hasText())) {
            return;
        }

        if (update.message.text == commandSignUp) {
            handleSignUp(update.message.chatId.toString())
        }
    }

    /**
     * Отправка ссылки для регистрации.
     */
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