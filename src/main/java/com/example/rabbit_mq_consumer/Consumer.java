package com.example.rabbit_mq_consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class Consumer {

    @StreamListener(Sink.INPUT)
    public void consumeMessage(Message msg) {
        System.out.println("Received Message is "+msg.getPayload().toString());
    }
}
