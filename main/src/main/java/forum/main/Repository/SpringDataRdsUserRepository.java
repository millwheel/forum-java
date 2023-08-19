package forum.main.Repository;

import forum.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRdsUserRepository extends JpaRepository<User, Long> {
}
