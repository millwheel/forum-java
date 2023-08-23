package forum.message.service;

import forum.message.entity.Notification;
import forum.message.repository.SpringDataRedisRepository;
import forum.message.messaging.KafkaConsumer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final SpringDataRedisRepository redisRepository;

    public MessageService(SpringDataRedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public void deduplicationMessage(Long postId, Long userId){
        Long messageId = postId + userId;
        if (redisRepository.existsById(messageId)) return;
        Notification notification = new Notification(messageId);
        redisRepository.save(notification);
    }

}
