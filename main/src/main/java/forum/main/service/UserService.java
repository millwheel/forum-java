package forum.main.service;

import forum.main.Repository.SpringDataRdsUserRepository;
import forum.main.entity.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataRdsUserRepository repository;

    public UserService(SpringDataRdsUserRepository repository) {
        this.repository = repository;
    }

    public long createUser(String username, List<String> keywordList){
        User user = new User();
        SecureRandom secureRandom = new SecureRandom();
        long randomLong = Integer.toUnsignedLong(secureRandom.nextInt());
        user.setUserId(randomLong);
        user.setUsername(username);
        user.setKeywordList(keywordList);
        User save = repository.save(user);
        return save.getUserId();
    }

    public Optional<User> readUser(long id){
        return repository.findById(id);
    }
}
