package com.example.choyxona.bot;

import com.example.choyxona.entity.User;
import com.example.choyxona.entity.enums.Status;
import com.example.choyxona.repository.ClientRepository;
import com.example.choyxona.repository.MonthRepository;
import com.example.choyxona.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    BotService botService;

    @Autowired
    ClientBotService clientBotService;

    @Autowired
    MonthRepository monthRepository;

    @Autowired
    AdminBot adminBot;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    int level = 0;
    long dayId;
    long chatId;
    long adChatId;
    long monthId;
    String bron = "";
    long roomId;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            User byChatId = userRepository.findByChatId(update.getMessage().getChatId());
            if (byChatId != null) {
                level = 7;
                adChatId = update.getMessage().getChatId();
            }
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                if (text.contains("#")) {
                    execute(adminBot.check(update));
                }
                if (text.equals("/start") && level != 7) {
                    execute(botService.mainBtn(update));
                    level = 0;
                }
                if (text.equals("\uD83D\uDCCD Bizning manzilimiz")) {
                    execute(botService.sendLocation(update));
                    level = 0;
                }
                if (text.equals("\uD83D\uDCCB menyu")) {
                    level = 0;
                    execute(botService.mainBtn(update));
                }
                if (text.equals("\uD83D\uDCCB menyu,")) level = 7;

                if (text.equals("Xona Bron qilish")) {
                    level = 1;
                    bron = "bron";
                    chatId = update.getMessage().getChatId();
                }
                if (text.equals("Kalendar bo'yicha tekshirish")) {
                    chatId = update.getMessage().getChatId();
                    level = 1;
                    bron = "check";
                }
            }
        }
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            if (data.contains("_#")) {
                execute(clientBotService.answerXaYoq(update));
                execute(botService.deleteMessage(update));
            }
            if (data.equals("kunOyga")) {
                level = 1;
                execute(botService.deleteMessage(update));
                chatId = update.getCallbackQuery().getMessage().getChatId();
            }
            if (data.equals("xonaKunga")) {
                level = 2;
                execute(botService.deleteMessage(update));
            }
            if (level == 3) {
                level = 4;
                if (bron.equals("check")) {
                    User byChatId = userRepository.findByChatId(update.getCallbackQuery().getMessage().getChatId());
                    if (byChatId != null) {
                        level = 7;
                        adChatId = update.getCallbackQuery().getMessage().getChatId();
                    } else {
                        execute(botService.useBron(update));
                        level = 0;
                    }
                }
            }
            if (!data.equals("xonaKunga") && level == 2) {
                execute(botService.deleteMessage(update));
                level = 3;
            }
            if (!data.equals("kunOyga") && level == 1) {
                level = 2;
                execute(botService.deleteMessage(update));
                monthId = Long.parseLong(data);
            }


        }

        switch (level) {
            case 1:
                execute(botService.months(chatId));
                break;
            case 2:
                execute(botService.monthOfDays(update, monthId));
                break;
            case 3:
                if (botService.weekend(update)) {
                    execute(botService.todayWeekend(update));
                    level = 1;
                } else {
                    dayId = Long.parseLong(update.getCallbackQuery().getData());
                    execute(botService.rooms(update));
                }
                break;
            case 4:
                roomId = Long.parseLong(update.getCallbackQuery().getData());
                execute(botService.deleteMessage(update));
                clientBotService.saveChatId(update);
                execute(clientBotService.name(update));
                level = 5;
                break;
            case 5:
                if (update.hasMessage()) {
                    if (update.getMessage().hasText()) {
                        execute(botService.deleteSimple(update, update.getMessage().getMessageId()));
                        if (clientRepository.existsByChatIdAndStatus(update.getMessage().getChatId(), Status.REJECT)) {
                            clientBotService.saveName(update);
                            execute(clientBotService.shareContact(update));
                            level = 6;
                        } else {
                            execute(botService.mainBtn(update));
                            level = 0;
                        }
                    }
                }
                break;
            case 6:
                if (update.hasMessage()) {
                    if (clientRepository.existsByChatIdAndStatus(update.getMessage().getChatId(), Status.REJECT)) {
                        if (update.getMessage().hasContact()) {
                            execute(clientBotService.finishSend(clientBotService.saveContact(update, dayId, roomId)));
                            level = 0;
                        } else {
                            execute(clientBotService.shareContact(update));
                        }
                    } else {
                        level = 0;
                        execute(botService.mainBtn(update));
                    }
                }
                break;
            case 7:
                execute(adminBot.admin(adChatId));
                break;
        }
//        System.out.println(level);
    }


    @Override
    public String getBotUsername() {
        return "chayxona_bot";
    }

    @Override
    public String getBotToken() {
        return "1466324606:AAENdFg5SmDSexIsCYLzYZI7xHQb8Z9g8Vs";
    }

}