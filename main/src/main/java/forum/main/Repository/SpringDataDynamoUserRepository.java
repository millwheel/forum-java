package forum.main.Repository;

import forum.main.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;

public interface SpringDataDynamoUserRepository extends DynamoDBCrudRepository<User, Long> {
}
