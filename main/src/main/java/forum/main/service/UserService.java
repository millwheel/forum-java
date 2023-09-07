package forum.main.service;

import forum.main.entity.Tag;
import forum.main.repository.SpringDataDynamoTagRepository;
import forum.main.repository.SpringDataDynamoUserRepository;
import forum.main.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class UserService {
    private final SpringDataDynamoUserRepository userRepository;
    private final SpringDataDynamoTagRepository tagRepository;

    public static final AtomicInteger serialNumber = new AtomicInteger(0);

    public long createUser(String username, List<String> tagList){
        Long userId = createUserId();
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

    public long createUserId() {
        String currentTime = Long.toString(System.currentTimeMillis());
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String lastIpNumber = hostAddress.split("\\.")[3];
        String serialNow = getSerialNumber();
        return Long.parseLong(currentTime + lastIpNumber + serialNow);
    }

    public synchronized String getSerialNumber(){
        int serialNow = serialNumber.getAndIncrement();
        if (serialNumber.get() == 512){
            serialNumber.set(0);
        }
        return Integer.toString(serialNow);
    }


    public User readUser(Long userId){
        return userRepository.findById(userId).orElseThrow();
    }
}
