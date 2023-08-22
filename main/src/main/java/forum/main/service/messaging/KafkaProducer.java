package forum.main.service.messaging;

import forum.main.dto.NotiMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final String topic = "forum_notification";

    @Autowired
    public KafkaProducer(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NotiMessageDto noti){
        Message<NotiMessageDto> message = MessageBuilder.withPayload(noti).setHeader(KafkaHeaders.TOPIC, topic).build();

        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(message);
        future.whenComplete((result, e) -> {
           if (e == null){
               int partition = result.getRecordMetadata().partition();
               long offset = result.getRecordMetadata().offset();
               NotiMessageDto sentNoti = (NotiMessageDto) result.getProducerRecord().value();
               log.info("produced message topic={}, partition={}, offset={}, payload: writerId={}, postId={}",
                       topic, partition, offset, sentNoti.getUserId(), sentNoti.getPostId());
           } else{
               log.error("Error occurred while producing message: {}", e.getMessage());
           }
        });
    }
}
