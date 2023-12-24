package com.example.adapterservice.sevice;

import com.example.adapterservice.config.DiscasdProperties;
import com.example.adapterservice.domain.Clients;
import com.example.adapterservice.dto.ClientsInfo;
import com.example.adapterservice.listner.ClientsPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class SmsService {
    private final ClientsPublisher publisher;

    private final DiscasdProperties discount;

    public Boolean sendSms(ClientsInfo clientsInfo) {
        if (clientsInfo == null || clientsInfo.getBirthday() == null) {
            throw new RuntimeException("clientsInfo not found");
        }
        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));
        LocalTime cutoffTime = LocalTime.parse("19:00");
        if (currentTime.isBefore(cutoffTime)) {

            publisher.sendSms(toSms(getFullName(clientsInfo), clientsInfo.getBirthday()));
            System.out.println(toSms(getFullName(clientsInfo), clientsInfo.getBirthday()));
            return true;
        }

        return false;

    }


    public Boolean sendSmsOldClients(Clients clients) {
        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));
        LocalTime cutoffTime = LocalTime.parse("19:00");

        if (currentTime.isBefore(cutoffTime)) {
            publisher.sendSms(toSms(clients.getFullName(), clients.getBirthday()));
            return true;
        }

        return false;

    }

    private String getFullName(ClientsInfo source) {
        return source.getName() + " " + source.getMiddleName() + " " + source.getSurname();
    }

    private String toSms(String fullName, LocalDate someDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonthValue() == someDate.getMonthValue() ?
                String.format("%s, в этом месяце для вас действует скидка %d%%", fullName, discount.getBirthdayThisMonthDiscount())
                : String.format("%s, в этом месяце для вас действует скидка %d%%", fullName, discount.getDefaultDiscount());
    }
}
