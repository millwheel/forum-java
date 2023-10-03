package forum.main.repository;

import forum.main.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface SpringDataDynamoUserRepository extends DynamoDBCrudRepository<User, Long> {
}
