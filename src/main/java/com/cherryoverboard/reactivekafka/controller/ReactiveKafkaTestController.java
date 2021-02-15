package com.cherryoverboard.reactivekafka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/test")
public class ReactiveKafkaTestController {

    private ReactiveKafkaProducerTemplate<String, String> producer;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    public ReactiveKafkaTestController(
            final ReactiveKafkaProducerTemplate<String, String> producer) {
        this.producer = producer;
    }

    @PostMapping("consume")
    public Mono<String> consume(@RequestBody Mono<String> request) {
        return request.doOnNext(value -> System.out.println("api consuming >> " + value))
                .map(value -> "consumed: " + value);

    }

    @PostMapping("consume-sync")
    public String consume(@RequestBody String request) {
        System.out.println("sync api consuming >> " + request);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sync api responding");
        return "consumed: " + request;
    }

    @PostMapping("produce")
    public Mono<Void> produce(@RequestBody String request) {
        return producer.send(kafkaTopic, request)
                .then();
    }
}
