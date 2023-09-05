package forum.message.service;

import forum.message.entity.Notification;
import forum.message.repository.SpringDataRedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DeduplicationService {

    private final SpringDataRedisRepository redisRepository;

    public synchronized Boolean deduplicationMessage(Long postId, Long userId, String title){
        Long messageId = postId + userId;
        if (redisRepository.existsById(messageId)) {
            return false;
        }
        Notification notification = new Notification(messageId, title);
        log.info("Memorize user id={}, post id={}, post title={}", userId, postId, title);
        redisRepository.save(notification);
        return true;
    }

}
