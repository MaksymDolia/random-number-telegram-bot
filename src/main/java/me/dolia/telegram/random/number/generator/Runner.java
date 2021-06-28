package me.dolia.telegram.random.number.generator;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        try {
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            var random = new Random();
            botsApi.registerBot(new RandomNumberGeneratorBot(random));
            log.info("Random number generator bot registered and started");
        } catch (TelegramApiException e) {
            log.error("Error occurred while trying to register a bot", e);
        }
    }
}