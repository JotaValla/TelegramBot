package es.codegym.telegrambot;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    //Read the .env file variables
    public static final Dotenv dotenv = Dotenv.load();

    //Replace with the variables  who provide the BOTFAHTER
    public static final String NAME = dotenv.get("TELEGRAM_BOT_NAME");
    public static final String TOKEN = dotenv.get("TELEGRAM_BOT_TOKEN");

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        if (getMessageText().equalsIgnoreCase("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT,
                    Map.of("Hackear la nevera", "/step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("¡Tomar una salchica! + 20 de fama", "/step_2_btn",
                            "¡Tomar un pescado! + 30 de fama", "/step_2_btn",
                            "¡Tomar un pollo! + 40 de fama", "/step_2_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_2_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT,
                    Map.of("Hackear al robot aspiradora", "/step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Enviar al robot aspirador a por comida! + 20 de fama", "/step_4_btn",
                            "Dar un pase en el robot aspirador! + 30 de fama", "/step_4_btn",
                            "¡Huir del robot aspirador! + 40 de fama", "/step_4_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_4_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT,
                    Map.of("Ponerse la GoPro", "/step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Comida ilimitada", "/step_6_btn",
                            "Paseo diario de papa", "/step_6_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_6_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT,
                    Map.of("Hackear la computadora", "/step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("¡Tomar una salchica! + 20 de fama", "/step_8_btn",
                            "¡Tomar un pescado! + 30 de fama", "/step_8_btn",
                            "¡Tomar un pollo! + 40 de fama", "/step_8_btn"));
        }
        if (getCallbackQueryButtonKey().equals("/step_8_btn")) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT + "\n¡Has completado el juego! ¡Tu fama es " + getUserGlory() + "!");
        }


    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}