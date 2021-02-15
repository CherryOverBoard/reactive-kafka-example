package com.cherryoverboard.reactivekafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReactiveKafkaListener {

    private ReactiveKafkaConsumerTemplate<String, String> consumer;

    public ReactiveKafkaListener(ReactiveKafkaConsumerTemplate<String, String> consumer) {
        this.consumer = consumer;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onMessage() {
        consumer.receive()
                .map(ConsumerRecord::value)
                .doOnNext(value -> System.out.println("kafka >> api request: " + value))
                .flatMap(value ->
                        WebClient.builder()
                                .baseUrl("http://localhost:8080")
                                .build()
                                .post()
                                .uri("/api/v1/test/consume-sync")
                                .bodyValue(value)
                                .exchangeToMono(response -> response.bodyToMono(String.class))
                )
                .subscribe(body -> System.out.println("kafka >> api response: " + body));
    }
}
