package com.cherryoverboard.reactivekafka.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactiveKafkaTestControllerTest {

    @Test
    void send_message_to_producer() {
        RequestEntity<String> request = RequestEntity.post("http://localhost:8080/api/v1/test/produce")
                .body("random message");
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Void> response = rt.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
