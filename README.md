# reactive-kafka-example
Example of using reactive kafka producer and consumer

## How to
1. use `docker-compose up` to spin kafka
2. start app
3. call `http://localhost:8080/api/v1/test/produce` or use test: `ReactiveKafkaTestControllerTest.send_message_to_producer()`

## Use internal consuming endpoint
Set up using sync or reactive consumer endpoint.

In `ReactiveKafkaListener.onMessage()` set URI of WebClient to:
1. Synchronous: `consume-sync` (as in example)
2. Reactive: `consume`
