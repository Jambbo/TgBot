package com.example.springtelegrambot.service;

import com.example.springtelegrambot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config){
        this.config=config;
    }

    @Override
    public String getBotToken(){
        return  config.getToken();
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

        switch (messageText){
            case "/start":
                startCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                break;
            case "/max":
                maxCommandReceived(chatId,update.getMessage().getChat().getUserName());
                break;
            case "/pob":
                pobCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                break;
            default: sendMessage(chatId,"Command was not recognized");
        }
        }
    }
    private void startCommandReceived(long chatId,String name){
            String answer = "Hi, "+name+", nice to meet you!!";
            sendMessage(chatId,answer);
    }

    private void maxCommandReceived(long chatId, String name){
        String answer = "Hi, "+name+", are you max?!";
        sendMessage(chatId,answer);
    }
    private void pobCommandReceived(long chatId, String name){
        String answer = "Hi, "+name+", did u know that pob is pidor ebanii?!";
        sendMessage(chatId,answer);
    }
    private void sendMessage(long chatId,String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try{
            execute(message);
        }catch(TelegramApiException e){

        }
    }
}