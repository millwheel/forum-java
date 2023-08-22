package forum.main.repository;

import forum.main.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDynamoUserRepository extends DynamoDBCrudRepository<User, Long> {
}
