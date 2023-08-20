package forum.main.Repository;

import forum.main.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRdsPostRepository extends JpaRepository<Post, Long> {
}
