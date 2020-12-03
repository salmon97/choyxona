package com.example.choyxona.bot;

import com.example.choyxona.entity.Client;
import com.example.choyxona.entity.enums.Status;
import com.example.choyxona.repository.ClientRepository;
import com.example.choyxona.repository.DayRepository;
import com.example.choyxona.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientBotService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    BotService botService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    Bot bot;

    @Autowired
    AdminBot adminBot;


    //send message tomorrow come or not
    public void sendMessageTomorrow() throws TelegramApiException {
        LocalDate t = LocalDate.now().plusDays(1);
        int year = t.getYear();
        int monthNum = t.getMonth().getValue();
        int dayNum = t.getDayOfMonth();
        List<Client> clients = clientRepository.getByGivenDate(year, monthNum, dayNum);
        for (Client client : clients) {
            if (client.getChatId() != null) {
                bot.execute(sendClient(client));
            }
        }
    }

    private SendMessage sendClient(Client client) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(client.getChatId());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(
                "Assalom Alaykum\n" +
                        client.getName() + "-" + client.getTelName() +
                        "\nSiz ertaga Gap Choyxonasida " + client.getRoom().getName() + "-chi xonani bron qilgansiz " +
                        "Kelsizmi ? " +
                        "Albatta boraman \"Xa\" Uzur Borolmayman \"Yo'q\""
        );
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        List<InlineKeyboardButton> col = new ArrayList<>();
        InlineKeyboardButton xa = new InlineKeyboardButton("Xa").setCallbackData(client.getId() + "_#xa");
        InlineKeyboardButton yoq = new InlineKeyboardButton("Yo'q").setCallbackData(client.getId() + "_#yo'q");
        col.add(xa);
        col.add(yoq);
        row.add(col);
        inlineKeyboardMarkup.setKeyboard(row);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage name(Update update) {
        return new SendMessage()
                .setText(Constant.NAME)
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setParseMode(ParseMode.MARKDOWN);
    }

    public void saveChatId(Update update) {
        Client client = new Client();
        client.setStatus(Status.REJECT);
        client.setChatId(update.getCallbackQuery().getMessage().getChatId());
        clientRepository.save(client);
    }

    public void saveName(Update update) {
        Client chatIdLast = clientRepository.getByChatIdLast(update.getMessage().getChatId());
        chatIdLast.setName(update.getMessage().getText());
        clientRepository.save(chatIdLast);
    }

    public Client saveContact(Update update, long dayId, long roomId) {
        Client chatIdLast = clientRepository.getByChatIdLastAndStatus(update.getMessage().getChatId(), String.valueOf(Status.REJECT));
        chatIdLast.setTelName(update.getMessage().getContact().getPhoneNumber());
        chatIdLast.setDay(dayRepository.getOne(dayId));
        chatIdLast.setRoom(roomRepository.getOne(roomId));
        chatIdLast.setStatus(Status.BOOKED);
        clientRepository.save(chatIdLast);
        return chatIdLast;
    }

    public SendMessage finishSend(Client client) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage()
                .setChatId(client.getChatId())
                .setParseMode(ParseMode.MARKDOWN);
        String text = "\uD83C\uDFE0 " + client.getRoom().getName() + "-chi xona\n" +
                "\uD83D\uDDD3 " + client.getDay().getDayNum() + "-" + client.getDay().getMonth().getNameUz() + "-kuni\n";
        sendMessage.setText(Constant.FINISH_BOOK + "\n" + text +
                "Sizni kutib qolamiz");
        sendMessage.setReplyMarkup(botService.bnt());
        adminBot.sendAdmin(client.getName() + "-+" + client.getTelName() + "\n" + text + "bron qilindi");
        return sendMessage;
    }

    public SendMessage shareContact(Update update) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setParseMode(ParseMode.MARKDOWN);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        sendMessage.setText(Constant.SHARE_CONTACT);
        keyboardButton.setText(Constant.SHARE_CONTACT_BUTTON);
        keyboardButton.setRequestContact(true);
        keyboardRow.add(keyboardButton);
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage answerXaYoq(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String[] split = update.getCallbackQuery().getData().split("_#");
        Client client = clientRepository.findById(UUID.fromString(split[0])).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        if (split[1].equals("yo'q")) {
            clientRepository.deleteById(UUID.fromString(split[0]));
            sendMessage.setText(Constant.YOQ);
            String text = client.getName() + " - +" + client.getTelName() + "\n" + client.getDay().getDayNum() + "-kungi " + client.getRoom().getName() + "-xonani bekor qildi";
            adminBot.sendAdmin(text);
        } else if (split[1].equals("xa")) {
            sendMessage.setText(Constant.XA);
            client.setStatus(Status.XA);
            clientRepository.save(client);
        }
        return sendMessage;
    }


    public void deleteRejectClient() {
        for (Client allByStatus : clientRepository.findAllByStatus(Status.REJECT)) {
            clientRepository.delete(allByStatus);
        }
    }
}
