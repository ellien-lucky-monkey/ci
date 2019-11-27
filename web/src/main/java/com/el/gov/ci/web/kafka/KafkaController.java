package com.el.gov.ci.web.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiangkui
 * @date 2019年07月22日
 */
@Slf4j
@RestController
public class KafkaController {


    private static final Gson gson = new GsonBuilder().create();

    @Lazy
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired(required = false)
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/demo/api/message/produce")
    public String produceMessage() {
        Attach.Message message = new Attach.Message();
        message.setBody("Hello msg");
        message.setTopic("msg");
        String data = gson.toJson(message);

        kafkaTemplate.send(message.getTopic(), message.toString());


        kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
            @Override
            public Object doInKafka(Producer<String, String> producer) {
                ProducerRecord<String, String> record = new ProducerRecord<>(message.getTopic(), data);
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.println("recordMetadata = [" + recordMetadata + "], e = [" + e + "]");
                    }
                });
                log.info("send message {}",data);
                return null;
            }
        });


        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                System.out.println("topic = [" + topic + "], partition = [" + partition + "], key = [" + key + "], value = [" + value + "], recordMetadata = [" + recordMetadata + "]");
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                System.out.println("topic = [" + topic + "], partition = [" + partition + "], key = [" + key + "], value = [" + value + "], exception = [" + exception + "]");
            }
        });

        return "Hello !";
    }



}
