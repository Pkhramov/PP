package com.example.adapterservice.listner;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(ClientsPublisher.Publisher.class)
public class ClientsPublisher {
    private final Publisher publisher;

    public void sendSms(String sms) {
        Message<String> message = MessageBuilder.withPayload(sms).build();
        if (!publisher.output().send(message)) {
            throw new RuntimeException(String.format("Message not send : %s", message));
        }
    }

    public interface Publisher {
        String OUTPUT = "sms-output";

        @Output(OUTPUT)
        MessageChannel output();
    }
}
