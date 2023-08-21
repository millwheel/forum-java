package forum.main.service;

import forum.main.repository.SpringDataDynamoUserRepository;
import forum.main.entity.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataDynamoUserRepository repository;

    public UserService(SpringDataDynamoUserRepository repository) {
        this.repository = repository;
    }

    public long createUser(String username, List<String> keywordList){
        SecureRandom secureRandom = new SecureRandom();
        long userId = Integer.toUnsignedLong(secureRandom.nextInt());
        User user = new User(userId, username, keywordList);
        User save = repository.save(user);
        return save.getUserId();
    }

    public Optional<User> readUser(long id){
        return repository.findById(id);
    }
}
