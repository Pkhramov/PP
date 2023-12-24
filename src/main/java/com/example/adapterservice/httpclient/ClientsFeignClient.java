package com.example.adapterservice.httpclient;

import com.example.adapterservice.dto.ClientsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "ClientsApi", url = "${clients.url}", primary = false)
public interface ClientsFeignClient {
    @PostMapping("/api/v1/getClient")
    List<ClientsInfo> getClients();

    @PostMapping("/api/v1/getClient/{clientId}")
    ClientsInfo getClient(@PathVariable String clientId);
}
