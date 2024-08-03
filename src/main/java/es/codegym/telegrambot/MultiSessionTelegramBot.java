package es.codegym.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiSessionTelegramBot extends TelegramLongPollingBot {
    // Nombre del bot
    private String name;
    // Token del bot
    private String token;

    // Almacena el evento de actualización actual en un hilo local
    private ThreadLocal<Update> updateEvent = new ThreadLocal<>();
    // Almacena la gloria de los usuarios por chat ID
    private HashMap<Long, Integer> gloryStorage = new HashMap<>();

    // Constructor que inicializa el nombre y el token del bot
    public MultiSessionTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    // Devuelve el nombre del bot
    @Override
    public String getBotUsername() {
        return name;
    }

    // Devuelve el token del bot
    @Override
    public String getBotToken() {
        return token;
    }

    // Método que se llama cuando se recibe una actualización
    @Override
    public final void onUpdateReceived(Update updateEvent) {
        this.updateEvent.set(updateEvent);
        onUpdateEventReceived(this.updateEvent.get());
    }

    // Método que se puede sobrescribir para manejar eventos de actualización
    public void onUpdateEventReceived(Update updateEvent) {
        // No hace nada por defecto
    }

    // Devuelve el ID del chat actual
    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    // Devuelve el texto del mensaje actual
    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    // Devuelve la clave del botón de consulta de devolución de llamada actual
    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    // Envía un mensaje de texto de forma asíncrona
    public void sendTextMessageAsync(String text) {
        SendMessage message = createMessage(text);
        sendApiMethodAsync(message);
    }

    // Envía un mensaje de texto con botones de forma asíncrona
    public void sendTextMessageAsync(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text, buttons);
        sendApiMethodAsync(message);
    }

    // Envía un mensaje de foto de forma asíncrona
    public void sendPhotoMessageAsync(String photoKey) {
        SendPhoto photo = createPhotoMessage(photoKey);
        executeAsync(photo);
    }

    // Crea un mensaje de texto
    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        Long chatId = getCurrentChatId();
        message.setChatId(chatId);
        return message;
    }

    // Crea un mensaje de texto con botones
    public SendMessage createMessage(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text);
        if (buttons != null && !buttons.isEmpty())
            attachButtons(message, buttons);
        return message;
    }

    // Adjunta botones a un mensaje
    private void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    // Crea un mensaje de foto
    public SendPhoto createPhotoMessage(String name) {
        try {
            SendPhoto photo = new SendPhoto();
            InputFile inputFile = new InputFile();
            var is = ClassLoader.getSystemResourceAsStream("images/" + name + ".jpg");
            inputFile.setMedia(is, name);

            photo.setPhoto(inputFile);
            Long chatId = getCurrentChatId();
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    // Establece la gloria del usuario
    public void setUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), glories);
    }

    // Devuelve la gloria del usuario
    public int getUserGlory() {
        return gloryStorage.getOrDefault(getCurrentChatId(), 0);
    }

    // Añade gloria al usuario
    public void addUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), getUserGlory() + glories);
    }
}