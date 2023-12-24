package com.example.adapterservice.sevice;

import com.example.adapterservice.httpclient.ClientsFeignClient;
import com.example.adapterservice.mapper.ClientMapper;
import com.example.adapterservice.repository.ClientsRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClientsService {

    private final ClientsFeignClient feignClient;
    private final ClientMapper mapper;
    private final ClientsRepository repository;
    private final SmsService smsService;
    private static final String PHONE_FILTER_DIGIT = "7";

    @Transactional
    public void processClients() {
        val clientsInfoList = feignClient.getClients();
        System.out.println("clientsInfoList = " + clientsInfoList);
        clientsInfoList.stream()
                .filter(client -> client.getPhone().endsWith(PHONE_FILTER_DIGIT))
                .forEach(clientsInfo -> {
                    val messageSend = smsService.sendSms(clientsInfo);
                    System.out.println("sms отправлено для " + clientsInfo);
                    repository.save(mapper.mapToClients(clientsInfo, messageSend));
                });
    }

    @Transactional
    public void sendOldClients() {
        repository.findClientsByMessageSend().forEach(clients -> {
            val messageSend = smsService.sendSmsOldClients(clients);
            System.out.println("sms отправлено для " + clients);
            clients.setMessageSend(messageSend);
            repository.save(clients);
        });
    }
    public void sendAndSaveClients(String clientId){
        val clientsInfo = feignClient.getClient(clientId);
        val messageSend = smsService.sendSms(clientsInfo);
        repository.save(mapper.mapToClients(clientsInfo, messageSend));
    }
}
