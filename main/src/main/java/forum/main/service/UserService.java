package forum.main.service;

import forum.main.entity.Tag;
import forum.main.repository.SpringDataDynamoTagRepository;
import forum.main.repository.SpringDataDynamoUserRepository;
import forum.main.entity.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataDynamoUserRepository userRepository;
    private final SpringDataDynamoTagRepository tagRepository;

    public UserService(SpringDataDynamoUserRepository repository, SpringDataDynamoTagRepository tagRepository) {
        this.userRepository = repository;
        this.tagRepository = tagRepository;
    }

    public long createUser(String username, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        Long userId = Integer.toUnsignedLong(secureRandom.nextInt());
        User user = new User(userId, username, tagList);
        User save = userRepository.save(user);
        for (String tagName: tagList){
            Optional<Tag> tagOptional = tagRepository.findById(tagName);
            if (tagOptional.isPresent()) {
                Tag tag = tagOptional.get();
                List<Long> userIds = tag.getUserIds();
                userIds.add(userId);
                tagRepository.save(tag);
            } else {
                List<Long> userIds = new ArrayList<>();
                userIds.add(userId);
                Tag tag = new Tag(tagName, userIds);
                tagRepository.save(tag);
            }
        }
        return save.getUserId();
    }

    public Optional<User> readUser(Long userId){
        return userRepository.findById(userId);
    }
}
