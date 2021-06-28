package me.dolia.telegram.random.number.generator.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class NumCommand implements IBotCommand {

    private final Random random;

    @Override
    public String getCommandIdentifier() {
        return "num";
    }

    @Override
    public String getDescription() {
        return "Generate a random number";
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        try {
            int num = random.nextInt(10);
            var reply = new SendMessage(message.getChatId().toString(), String.valueOf(num));
            absSender.execute(reply);
            var from = message.getFrom();
            log.info("Sent reply with {} num to message from {} {}", num, from.getFirstName(), from.getLastName());
        } catch (TelegramApiException e) {
            log.error("Error occurred while trying to reply to message: {}", message, e);
        }
    }
}