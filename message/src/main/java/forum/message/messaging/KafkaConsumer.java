package forum.message.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import forum.message.dto.NotiMessageDto;
import forum.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    private final MessageService messageService;

    public KafkaConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "forum_notification", groupId = "notification_server")
    public void listener(@Headers MessageHeaders messageHeaders, @Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        NotiMessageDto notiMessageDto;
        try {
            notiMessageDto = objectMapper.readValue(message, NotiMessageDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Long userId = notiMessageDto.getUserId();
        Long postId = notiMessageDto.getPostId();
        String title = notiMessageDto.getTitle();
        log.info("Received message: header={}, postId={}, userId={}", messageHeaders, postId, userId);
        messageService.deduplicationMessage(postId, userId, title);
    }

}
