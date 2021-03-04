package com.example.choyxona.companent;

//import com.example.choyxona.bot.ClientBotService;
import com.example.choyxona.controller.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@EnableScheduling
public class Scheduling {

//    @Autowired
//    ClientBotService clientBotService;

    @Autowired
    Test test;
//
//    @Scheduled(cron = "0 1 1 * * *")
//    public void scheduling() {
//        clientBotService.deleteRejectClient();
//    }
//
//    @Scheduled(cron = "0 8 * * * *")
//    public void example() throws TelegramApiException {
//        clientBotService.sendMessageTomorrow();
//    }

    @Scheduled(fixedRate = 25 * 60  * 1000)
    public void runEvery25min() {
        test.getEmployees();
    }
}
