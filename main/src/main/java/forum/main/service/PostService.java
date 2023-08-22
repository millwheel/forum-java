package forum.main.service;

import forum.main.dto.NotiMessageDto;
import forum.main.entity.Notification;
import forum.main.entity.Post;
import forum.main.repository.SpringDataRedisNotiRepository;
import forum.main.service.messaging.KafkaProducer;
import forum.main.repository.SpringDataDynamoPostRepository;
import forum.main.repository.SpringDataDynamoTagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PostService {

    private final SpringDataDynamoPostRepository postRepository;
    private final SpringDataDynamoTagRepository tagRepository;
    private final SpringDataRedisNotiRepository notiRepository;
    private final KafkaProducer producer;

    public PostService(SpringDataDynamoPostRepository repository, SpringDataDynamoTagRepository tagRepository, SpringDataRedisNotiRepository notiRepository, KafkaProducer producer) {
        this.postRepository = repository;
        this.tagRepository = tagRepository;
        this.notiRepository = notiRepository;
        this.producer = producer;
    }

    public Long createPost(Long writerId, String content, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        Long postId = Integer.toUnsignedLong(secureRandom.nextInt());
        Post post = new Post(postId, writerId, content, tagList);
        Post save = postRepository.save(post);
        for (String tagName: tagList){
            log.info("Tag name is {}", tagName);
            tagRepository.findById(tagName).ifPresent(tag -> {
                List<Long> userIds = tag.getUserIds();
                for (Long userId: userIds){
                    if (Objects.equals(userId, writerId)) continue;
                    Long messageCacheId = userId + postId;
                    log.info("check: Tag name={}, user id={}, post id={}", tagName, userId, postId);
                    if (notiRepository.existsById(messageCacheId)) continue;
                    log.info("confirmed: Tag name = {}, user id = {}, post id = {}", tagName, userId, postId);
                    NotiMessageDto notiMessageDto = new NotiMessageDto(userId, postId);
                    notiRepository.save(new Notification(messageCacheId));
                    producer.sendMessage(notiMessageDto);
                }
            });
        }
        return save.getPostId();
    }

    public Optional<Post> readPost(Long postId){
        return postRepository.findById(postId);
    }
}
