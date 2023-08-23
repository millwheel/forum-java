package forum.message.service.messaging;

import forum.message.dto.NotiMessageDto;
import forum.message.entity.Notification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "forum_notification", groupId = "notification_server")
    public void listener(@Headers MessageHeaders messageHeaders, @Payload NotiMessageDto noti, ConsumerRecord<?, ?> record) {
        log.info("Received message: header={}, postId={}, userId={}", messageHeaders, noti.getPostId(), noti.getUserId());
    }

}
