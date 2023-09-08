package forum.message.repository;


import forum.message.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDynamoUserRepository extends DynamoDBCrudRepository<User, Long> {
}
