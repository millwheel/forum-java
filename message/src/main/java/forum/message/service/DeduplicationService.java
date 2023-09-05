package forum.message.service;

import forum.message.entity.Notification;
import forum.message.repository.SpringDataRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeduplicationService {

    private final SpringDataRedisRepository redisRepository;

    public DeduplicationService(SpringDataRedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public void deduplicationMessage(Long postId, Long userId, String title){
        Long messageId = postId + userId;
        if (redisRepository.existsById(messageId)) return;
        Notification notification = new Notification(messageId, title);
        log.info("Memorize user id={}, post id={}, post title={}", userId, postId, title);
        redisRepository.save(notification);
    }

}
