package com.el.gov.ci.admin.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Jiangkui
 * @date 2019年08月01日
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test_account_edit", "test_account_add", "msg", "test_account_info_edit", "test_account_info_add"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println("consumer start" + record.topic() + "\n" + record.toString());
    }
}
