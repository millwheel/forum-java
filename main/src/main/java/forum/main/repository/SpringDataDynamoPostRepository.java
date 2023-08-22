package forum.main.repository;

import forum.main.entity.Post;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDynamoPostRepository extends DynamoDBCrudRepository<Post, Long> {
}
