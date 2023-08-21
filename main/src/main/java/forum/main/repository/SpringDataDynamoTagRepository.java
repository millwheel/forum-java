package forum.main.repository;

import forum.main.entity.Tag;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;

public interface SpringDataDynamoTagRepository extends DynamoDBCrudRepository<Tag, String> {
}
