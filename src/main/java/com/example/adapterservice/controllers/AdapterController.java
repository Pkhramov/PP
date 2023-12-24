package com.example.adapterservice.controllers;

import com.example.adapterservice.scheduler.ClientsScheduler;
import com.example.adapterservice.sevice.ClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdapterController {
    private final ClientsScheduler scheduler;
    private final ClientsService service;

    @PostMapping("/api/v1/sendAndSaveClients")
    public ResponseEntity<Void> sendAndSaveClients() {
        scheduler.fetchClientInformationScheduled();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/v1/sendAndSaveClients/{clientId}")
    public ResponseEntity<Void> sendAndSaveClient(@PathVariable String clientId) {
        service.sendAndSaveClients(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
