package forum.message.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import forum.message.entity.Notification;
import forum.message.repository.SpringDataRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    private final SpringDataRedisRepository redisRepository;

    public MessageService(SpringDataRedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public void deduplicationMessage(Long postId, Long userId, String title){
        Long messageId = postId + userId;
        if (redisRepository.existsById(messageId)) return;
        Notification notification = new Notification(messageId, title);
        log.info("Memorize user id={}, post id={}, post title={}", userId, postId, title);
        redisRepository.save(notification);

    }

    public void sendNotification() {
        // This is just a basic example. You'd typically get the token from your database.
        String registrationToken = "YOUR-TARGET-DEVICE-TOKEN";

        Message message = Message.builder()
                .putData("title", "Notification Title")
                .putData("body", "Notification Body")
                .setToken(registrationToken)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
