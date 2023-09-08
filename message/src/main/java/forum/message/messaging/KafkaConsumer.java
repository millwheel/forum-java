package forum.message.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import forum.message.dto.NotiMessageDto;
import forum.message.entity.User;
import forum.message.repository.SpringDataDynamoUserRepository;
import forum.message.service.DeduplicationService;
import forum.message.service.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

    private final DeduplicationService deduplicationService;
    private final FirebaseService firebaseService;
    private final SpringDataDynamoUserRepository userRepository;

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
        if(deduplicationService.deduplicationMessage(postId, userId, title)){
            User user = userRepository.findById(userId).orElseThrow();
            String token = user.getToken();
            if (token.isEmpty()) return;
            firebaseService.sendNotification(title, token);
        }
    }

}
