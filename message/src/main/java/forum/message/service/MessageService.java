package forum.message.service;

import forum.message.entity.Notification;
import forum.message.repository.SpringDataRedisRepository;
import forum.message.service.messaging.KafkaConsumer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final KafkaConsumer kafkaConsumer;
    private final SpringDataRedisRepository redisRepository;

    public MessageService(KafkaConsumer kafkaConsumer, SpringDataRedisRepository redisRepository) {
        this.kafkaConsumer = kafkaConsumer;
        this.redisRepository = redisRepository;
    }

    public void deduplicationMessage(Long postId, Long userId){
        Long messageId = postId + userId;
        if (redisRepository.existsById(messageId)) return;
        Notification notification = new Notification(messageId);
        redisRepository.save(notification);
    }

}
