package forum.main.repository;

import forum.main.entity.Post;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;

public interface SpringDataDynamoPostRepository extends DynamoDBCrudRepository<Post, Long> {
}
