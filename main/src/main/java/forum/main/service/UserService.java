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
        int randomId = secureRandom.nextInt();
        user.setId(randomId);
        user.setUsername(username);
        user.setKeywordList(keywordList);
        User save = repository.save(user);
        return save.getId();
    }

    public Optional<User> readUser(long id){
        return repository.findById(id);
    }
}
