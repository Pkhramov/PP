package com.example.adapterservice.scheduler;

import com.example.adapterservice.sevice.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientsScheduler {
    private final ClientsService service;

    @Scheduled(cron = "${cron}")
    public void fetchClientInformationScheduled() {
        service.sendOldClients();
        service.processClients();
    }

}
