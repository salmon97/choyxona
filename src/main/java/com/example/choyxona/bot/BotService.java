package com.example.choyxona.bot;

import com.example.choyxona.entity.*;
import com.example.choyxona.payload.ResRoom;
import com.example.choyxona.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BotService {

    @Autowired
    MonthRepository monthRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;


//    public static void main(String[] args) {
//        int year = YearMonth.now().getYear();
//        int month = YearMonth.now().getMonthValue();
//        LocalDate localDate = LocalDate.now();
//        LocalDate t = LocalDate.now().plusDays(1);
//        int yearNum = t.getYear();
//        int monthNum = t.getMonth().getValue();
//        int dayNum = t.getDayOfMonth();
////        LocalDate tomorrow = today.plusDays(1);
//        System.out.println(yearNum + " " + monthNum + " " + dayNum);
//    }

    public boolean weekend(Update update) {
        Day day = dayRepository.findById(Long.valueOf(update.getCallbackQuery().getData())).orElseThrow(() -> new ResourceNotFoundException("getDay"));
        return day.getName().equals(userRepository.getDirector().getWeekend());
    }

    public SendMessage todayWeekend(Update update) {
        return new SendMessage()
                .setParseMode(ParseMode.MARKDOWN)
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(Constant.WEEKEND);
    }

    public SendMessage mainBtn(Update update) {
        return new SendMessage()
                .setParseMode(ParseMode.MARKDOWN)
                .setChatId(update.getMessage().getChatId())
                .setText(Constant.WELCOME + "[.](https://telegra.ph/Assalamu-Alaykum-Choyhonamizga-Xushkelibsiz-10-17)")
                .setReplyMarkup(bnt());

    }

    public SendMessage useBron(Update update) {
        return new SendMessage()
                .setParseMode(ParseMode.MARKDOWN)
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(Constant.USE_BRON)
                .setReplyMarkup(bnt());

    }

    public SendLocation sendLocation(Update update) {
        SendLocation sendLocation = new SendLocation();
        User director = userRepository.getDirector();
        sendLocation.setLatitude(director.getLatitude().floatValue());
        sendLocation.setLongitude(director.getLongitude().floatValue());
        sendLocation.setChatId(update.getMessage().getChatId());
        sendLocation.setReplyToMessageId(update.getMessage().getMessageId());
        return sendLocation;
    }

    public ReplyKeyboardMarkup bnt() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup()
                .setSelective(true)
                .setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton b1 = new KeyboardButton("Xona Bron qilish");
        KeyboardButton b2 = new KeyboardButton("Kalendar bo'yicha tekshirish");
        KeyboardButton location = new KeyboardButton("\uD83D\uDCCD Bizning manzilimiz");
        KeyboardButton menyu = new KeyboardButton("\uD83D\uDCCB menyu");
        row1.add(b1);
        row1.add(b2);
        row2.add(location);
        row2.add(menyu);
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public SendMessage months(long chatId) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(Constant.MONTH)
                .setParseMode(ParseMode.MARKDOWN);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        int count = 0;
        List<InlineKeyboardButton> col = new ArrayList<>();
        List<Month> months = monthRepository.getByMonthNumAndYear(YearMonth.now().getMonthValue(), YearMonth.now().getYear());
        for (Month month : months) {
            if (count >= 2) {
                row.add(col);
                col = new ArrayList<>();
                count = 0;
            }
            InlineKeyboardButton b = new InlineKeyboardButton()
                    .setText(month.getNameUz())
                    .setCallbackData(String.valueOf(month.getId()));
            col.add(b);
            count++;
        }
        row.add(col);
        inlineKeyboardMarkup.setKeyboard(row);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage monthOfDays(Update update, long monthId) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setParseMode(ParseMode.MARKDOWN);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        int count = 0;
        List<InlineKeyboardButton> col = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        Month month = monthRepository.findById(monthId).orElseThrow(() -> new ResourceNotFoundException("getMonth"));
        sendMessage.setText(month.getNameUz() + " oyining" + Constant.DAY);
        List<Day> byMonthId;
        if (month.getYear() > localDate.getYear() || month.getMonthNum() > localDate.getMonth().getValue()) {
            byMonthId = dayRepository.getDayByMonthId(monthId);
        } else {
            byMonthId = dayRepository.getDayNumByMonthId(localDate.getDayOfMonth(), monthId);
        }
        for (Day day : byMonthId) {
            if (count >= 3) {
                row.add(col);
                col = new ArrayList<>();
                count = 0;
            }
            InlineKeyboardButton b = new InlineKeyboardButton()
                    .setText(String.valueOf(day.getDayNum()))
                    .setCallbackData(String.valueOf(day.getId()));
            col.add(b);
            count++;
        }
        row.add(col);
        row.add(ortga("kunOyga"));
        inlineKeyboardMarkup.setKeyboard(row);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage rooms(Update update) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setParseMode(ParseMode.MARKDOWN);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        if (roomsChecked(Long.parseLong(update.getCallbackQuery().getData())).size() > 0) {
            sendMessage.setText(Constant.ROOM);
            int count = 0;
            List<InlineKeyboardButton> col = new ArrayList<>();
            List<ResRoom> resRooms = roomsChecked(Long.parseLong(update.getCallbackQuery().getData()));
            for (ResRoom resRoom : resRooms) {
                if (count >= 1) {
                    row.add(col);
                    col = new ArrayList<>();
                    count = 0;
                }
                InlineKeyboardButton b = new InlineKeyboardButton()
                        .setText(resRoom.getName() + "-xona " + resRoom.getCapacity() + "-kishili")
                        .setCallbackData(String.valueOf(resRoom.getRoomId()));
                col.add(b);
                count++;
            }
            row.add(col);
        } else {
            sendMessage.setText(Constant.NO_ROOM);
        }
        row.add(ortga("xonaKunga"));
        inlineKeyboardMarkup.setKeyboard(row);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    private List<ResRoom> roomsChecked(long dayId) {
        List<Room> all = roomRepository.findAll();
        List<ResRoom> resRooms = new ArrayList<>();
        List<Client> allByDay_id = clientRepository.findAllByDay_Id(dayId);
        boolean a;
        for (Room room : all) {
            a = true;
            for (Client client : allByDay_id) {
                if (Objects.equals(room.getId(), client.getRoom().getId())) {
                    a = false;
                    break;
                }
            }
            if (a) {
                resRooms.add(new ResRoom(null, room.getId(), dayId, "bo'sh", room.getName(), room.getCapacity(),
                        null
                ));
            }
        }
        return resRooms;
    }

    public List<InlineKeyboardButton> ortga(String ortga) {
        List<InlineKeyboardButton> ortgaArr = new ArrayList<>();
        InlineKeyboardButton ortgaB = new InlineKeyboardButton()
                .setText("⬅️ Ortga")
                .setCallbackData(ortga);
        ortgaArr.add(ortgaB);
        return ortgaArr;
    }

    public DeleteMessage deleteMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        return deleteMessage;
    }

    public DeleteMessage deleteSimple(Update update, int messId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(update.getMessage().getChat().getId());
        deleteMessage.setMessageId(messId);
        return deleteMessage;
    }

    public EditMessageReplyMarkup editMessageReplyMarkup(int messageId, String inlineMessageId, long chatId, InlineKeyboardMarkup inlineKeyboardMarkup) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setInlineMessageId(inlineMessageId);
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(messageId);
        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);
        return editMessageReplyMarkup;
    }

    public EditMessageText editMessageText(int messageId, long chatId, String text) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(text);
        return editMessageText;
    }

}

//                .setText("Kategoriyalardan birini tanlang" + "[.](https://telegra.ph/ASL-GOSHT-MAHSULOTLARI-08-08)")