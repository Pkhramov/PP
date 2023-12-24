package com.example.adapterservice.mapper;

import com.example.adapterservice.domain.Clients;
import com.example.adapterservice.dto.ClientsInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "source", target = "fullName", qualifiedByName = "getFullName")
    @Mapping(source = "messageSend", target = "messageSend")
    @Mapping(source = "source.clientId", target = "clientId")
    Clients mapToClients(ClientsInfo source, Boolean messageSend);

    @Named("getFullName")
    default String getFullName(ClientsInfo source) {
        return source.getName() + " " + source.getMiddleName() + " " + source.getSurname();
    }
}
