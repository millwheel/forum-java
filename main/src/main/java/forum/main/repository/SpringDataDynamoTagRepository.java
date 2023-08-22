package forum.main.repository;

import forum.main.entity.Tag;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDynamoTagRepository extends DynamoDBCrudRepository<Tag, String> {
}
