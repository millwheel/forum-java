package forum.main.repository;

import forum.main.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataRedisNotiRepository extends CrudRepository<Notification, Long> {
}
