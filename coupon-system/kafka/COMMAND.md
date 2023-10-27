# kafka 실행 명령어
## kafka 실행
```docker-compose up -d```

## topic 생성
```docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic testTopic```

## producer 실행
```docker exec -it kafka kafka-console-producer.sh --topic testTopic --broker-list 0.0.0.0:9092```

## consumer 실행
```docker exec -it kafka kafka-console-consumer.sh --topic testTopic --bootstrap-server localhost:9092```

## 쿠폰 예제 consumer 실행
```docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"```
