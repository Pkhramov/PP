package com.example.adapterservice.repository;

import com.example.adapterservice.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
    @Query("SELECT c FROM Clients c WHERE c.messageSend = false")
    List<Clients> findClientsByMessageSend();
}
