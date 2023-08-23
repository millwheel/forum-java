package forum.message.repository;

import forum.message.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataRedisRepository extends CrudRepository<Notification, Long> {
}
