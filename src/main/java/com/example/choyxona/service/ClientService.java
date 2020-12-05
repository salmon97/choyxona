package com.example.choyxona.service;

import com.example.choyxona.bot.Bot;
import com.example.choyxona.entity.*;
import com.example.choyxona.entity.enums.Status;
import com.example.choyxona.payload.*;
import com.example.choyxona.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductKgRepository productKgRepository;

    @Autowired
    ProductClientRepository productClientRepository;

    @Autowired
    ProductKgClientRepository productKgClientRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    Bot bot;

    public ApiResponse registerClient(ReqClient reqClient) {
        if (reqClient.getTelNum() != null && reqClient.getName() != null) {
            Client client = new Client();
            client.setRoom(roomRepository.getOne(reqClient.getRoomId()));
            if (reqClient.getDayId() != null) {
                client.setDay(dayRepository.getOne(reqClient.getDayId()));
            } else {
                LocalDate t = LocalDate.now();
                int yearNum = t.getYear();
                int monthNum = t.getMonth().getValue();
                int dayNum = t.getDayOfMonth();
                client.setDay(dayRepository.findByDayNumAndMonth_MonthNumAndMonth_Year(dayNum, monthNum, yearNum));
            }
            client.setName(reqClient.getName());
            client.setTelName(reqClient.getTelNum());
            client.setStatus(Status.WEB);
            clientRepository.save(client);
            return new ApiResponse("ok", true);
        }
        return new ApiResponse("ok", false);
    }

    public ApiResponse sendCheck(UUID clientId) throws TelegramApiException {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("getClient"));
        if (client.getChatId() == null) {
            return new ApiResponse("chek jo'nata olmaysiz", false);
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(client.getChatId());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        List<ResPro_Client> byGroupedProClient = productClientRepository.getByGroupedPro(clientId);
        List<ResPro_Client> grouped = productKgClientRepository.getGrouped(clientId);
        StringBuilder text = new StringBuilder("sizning chekingiz \uD83E\uDDFE \n");
        double sum = 0;
        for (ResPro_Client resPro_client : byGroupedProClient) {
            text.append(resPro_client.getName()).append(" ").append(resPro_client.getAmount()).append("-ta ").append(resPro_client.getSumma()).append("-sum \n");
            sum += resPro_client.getSumma();
        }
        for (ResPro_Client resPro_client : grouped) {
            text.append(resPro_client.getName()).append(" ").append(resPro_client.getAmount()).append("-gr ").append(resPro_client.getSumma()).append("-sum \n");
            sum += resPro_client.getSumma();
        }
        text.append("\uD83D\uDCB0 jami ").append(sum).append(" - sum");
        sendMessage.setText(text.toString());
        client.setStatus(Status.FINISHED);
        clientRepository.save(client);
        bot.execute(sendMessage);
        return new ApiResponse("jo'natildi", true);
    }

    //product client kg add
    public ApiResponse addProductClientKg(ReqProduct reqProduct) {
        Client client = clientRepository.findById(reqProduct.getClientId()).orElseThrow(() -> new ResourceNotFoundException("getClient"));
        ProductKg productKg = productKgRepository.findById(reqProduct.getProductId()).orElseThrow(() -> new ResourceNotFoundException("getProductKg"));
        ProductKgClient productKgClient = new ProductKgClient();
        productKgClient.setClient(client);
        productKgClient.setProductKg(productKg);
        productKgClient.setKg(reqProduct.getAmount());
        productKgClient.setSum((productKg.getBalance() / 1000) * reqProduct.getAmount());
        productKgClient.setCost((productKg.getCost() / 1000) * reqProduct.getAmount());
        productKgClientRepository.save(productKgClient);
        return new ApiResponse("add", true, new ResProduct(
                productKgClient.getId(),
                productKg.getId(),
                productKg.getName(),
                productKgClient.getSum(),
                reqProduct.getAmount()),
                productKgClientRepository.getTotalSum(reqProduct.getClientId()));
    }

    // edit product kg client
    public ApiResponse editProductClientKg(ReqProduct reqProduct) {
        ProductKg productKg = productKgRepository.findById(reqProduct.getProductId()).orElseThrow(() -> new ResourceNotFoundException("getProductKg"));
        ProductKgClient productKgClient = productKgClientRepository.getOne(reqProduct.getProductClientId());
        productKgClient.setProductKg(productKg);
        productKgClient.setKg(reqProduct.getAmount());
        productKgClient.setSum((productKg.getBalance() / 1000) * reqProduct.getAmount());
        productKgClient.setCost((productKg.getCost() / 1000) * reqProduct.getAmount());
        productKgClientRepository.save(productKgClient);
        return new ApiResponse("edit", true, new ResProduct(
                productKgClient.getId(),
                productKg.getId(),
                productKg.getName(),
                productKgClient.getSum(),
                reqProduct.getAmount()),
                productKgClientRepository.getTotalSum(productKgClient.getClient().getId()));
    }

    //res list product kg client
    public ApiResponse resProductKgClient(UUID client_id) {
        List<ProductKgClient> allByClient_id = productKgClientRepository.findAllByClient_Id(client_id);
        List<ResProduct> resProducts = new ArrayList<>();
        for (ProductKgClient productKgClient : allByClient_id) {
            resProducts.add(new ResProduct(
                    productKgClient.getId(),
                    productKgClient.getProductKg().getId(),
                    productKgClient.getProductKg().getName(),
                    productKgClient.getSum(),
                    productKgClient.getKg()
            ));
        }
        return new ApiResponse("ok", true, resProducts, allByClient_id.size() > 0 ? productKgClientRepository.getTotalSum(allByClient_id.get(0).getClient().getId()) : 0);
    }

    // delete product kg client
    public ApiResponse deleteProKgClient(UUID uuid, UUID clientId) {
        productKgClientRepository.deleteById(uuid);
        return new ApiResponse(uuid.toString(), true, null, productKgClientRepository.getTotalSum(clientId));
    }

    //add product client
    public ApiResponse addProduct(ReqProduct reqProduct) {
        Client client = clientRepository.findById(reqProduct.getClientId()).orElseThrow(() -> new ResourceNotFoundException("getClient"));
        Product product = productRepository.findById(reqProduct.getProductId()).orElseThrow(() -> new ResourceNotFoundException("getProduct"));
        ProductClient productClient = new ProductClient();
        productClient.setClient(client);
        productClient.setProduct(product);
        productClient.setPiece(reqProduct.getAmount());
        productClient.setSum(product.getBalance() * reqProduct.getAmount());
        productClient.setCost(product.getCost() * reqProduct.getAmount());
        productClientRepository.save(productClient);
        return new ApiResponse("add", true, new ResProduct(
                productClient.getId(),
                product.getId(),
                product.getName(),
                productClient.getSum(),
                reqProduct.getAmount()),
                productClientRepository.getTotalSum(reqProduct.getClientId()));
    }

    //edit product client
    public ApiResponse editProduct(ReqProduct reqProduct) {
        ProductClient productClient = productClientRepository.getOne(reqProduct.getProductClientId());
        Product product = productRepository.getOne(reqProduct.getProductId());
        productClient.setProduct(product);
        productClient.setPiece(reqProduct.getAmount());
        productClient.setSum(product.getBalance() * reqProduct.getAmount());
        productClient.setCost(product.getCost() * reqProduct.getAmount());
        productClientRepository.save(productClient);
        return new ApiResponse("edit", true, new ResProduct(
                productClient.getId(),
                product.getId(),
                product.getName(),
                productClient.getSum(),
                reqProduct.getAmount()),
                productClientRepository.getTotalSum(productClient.getClient().getId()));
    }

    //res list product client
    public ApiResponse resProductClient(UUID clientId) {
        List<ProductClient> productClients = productClientRepository.findAllByClient_Id(clientId);
        List<ResProduct> resProducts = new ArrayList<>();
        for (ProductClient productClient : productClients) {
            resProducts.add(new ResProduct(
                    productClient.getId(),
                    productClient.getProduct().getId(),
                    productClient.getProduct().getName(),
                    productClient.getSum(),
                    productClient.getPiece()
            ));
        }

        return new ApiResponse("ok", true, resProducts, productClients.size() > 0 ? productClientRepository.getTotalSum(productClients.get(0).getClient().getId()) : 0);
    }

    //delete product client
    public ApiResponse deleteProClient(UUID id, UUID clientId) {
        productClientRepository.deleteById(id);
        return new ApiResponse(id.toString(), true, null, productClientRepository.getTotalSum(clientId));
    }

    //client
    public ApiResponse deleteClient(UUID clientId) {
        clientRepository.deleteById(clientId);
        return new ApiResponse(clientId.toString(), true);
    }

    //res Client
    public ApiResponse resClient(UUID uuid) {
        Client client = clientRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("getClient"));
        return new ApiResponse("ok", true, new ResClient(
                client.getId(),
                client.getRegisterAt(),
                client.getName(),
                client.getTelName(),
                client.getStatus().name(),
                client.getDay().getMonth().getMonthNum() + "-" + client.getDay().getDayNum() + "-" + client.getDay().getMonth().getYear(),
                client.getChatId()
        ));
    }
}
