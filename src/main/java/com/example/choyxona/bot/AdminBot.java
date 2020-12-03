package com.example.choyxona.bot;

import com.example.choyxona.entity.User;
import com.example.choyxona.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminBot {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Bot bot;

    public SendMessage check(Update update) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setParseMode(ParseMode.MARKDOWN);
        String text = update.getMessage().getText();
        String[] split = text.split("#");
        Optional<User> byEmail = userRepository.findByEmail(split[0]);
        if (byEmail.isPresent()) {
            if (passwordEncoder.matches(split[1], byEmail.get().getPassword())) {
                if (byEmail.get().getChatId() != null) {
                    byEmail.get().setChatId(null);
                    sendMessage.setText("bekor qilindi");
                } else {
                    sendMessage.setText("royhatdan o'tdingiz");
                    byEmail.get().setChatId(update.getMessage().getChatId());
                }
                userRepository.save(byEmail.get());
            } else {
                sendMessage.setText("bunday foydalunvhi mavjud emas");
            }
        } else {
            sendMessage.setText("bunday foydalunvhi mavjud emas");
        }
        return sendMessage;
    }

    public void sendAdmin(String text) throws TelegramApiException {
        List<User> all = userRepository.findAll();
        for (User user : all) {
            if (user.getChatId() != null) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(user.getChatId());
                sendMessage.setParseMode(ParseMode.MARKDOWN);
                sendMessage.setText(text);
                bot.execute(sendMessage);
            }
        }
    }

    public SendMessage admin(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(Constant.WELCOME + "[.](https://telegra.ph/Assalamu-Alaykum-Choyhonamizga-Xushkelibsiz-10-17)");
        sendMessage.setReplyMarkup(bnt());
        return sendMessage;
    }

    public ReplyKeyboardMarkup bnt() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup()
                .setSelective(true)
                .setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton b2 = new KeyboardButton("Kalendar bo'yicha tekshirish");
        KeyboardButton location = new KeyboardButton("\uD83D\uDCCD Bizning manzilimiz");
        KeyboardButton menyu = new KeyboardButton("\uD83D\uDCCB menyu,");
        row1.add(b2);
        row2.add(location);
        row2.add(menyu);
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
