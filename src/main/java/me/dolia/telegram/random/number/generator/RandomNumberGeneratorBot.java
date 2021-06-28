package me.dolia.telegram.random.number.generator;

import lombok.extern.slf4j.Slf4j;
import me.dolia.telegram.random.number.generator.commands.NumCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;
import java.util.Random;

@Slf4j
public class RandomNumberGeneratorBot extends TelegramLongPollingCommandBot {

    private final String apiToken;

    public RandomNumberGeneratorBot(Random random) {
        this.apiToken = Objects.requireNonNull(System.getenv("API_TOKEN"), "Telegram Bot API Token must be provided");
        Objects.requireNonNull(random, "java.util.Random object must be provided");
        this.register(new NumCommand(random));
    }

    @Override
    public String getBotUsername() {
        return "MxfRandomNumberGeneratorBot";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        try {
            var chatId = update.getMessage().getChatId().toString();
            var toReply = new SendMessage(chatId, "I only support /num command so far");
            execute(toReply);
            var from = update.getMessage().getFrom();
            log.info("Replied with default message to {} {}", from.getFirstName(), from.getLastName());
        } catch (TelegramApiException e) {
            log.error("Error occurred while trying to reply to non-command / unknown command message", e);
        }
    }

    @Override
    public String getBotToken() {
        return apiToken;
    }
}