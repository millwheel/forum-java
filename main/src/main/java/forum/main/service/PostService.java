package forum.main.service;

import forum.main.dto.NotiMessageDto;
import forum.main.entity.Post;
import forum.main.messaging.KafkaProducer;
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
    private final KafkaProducer producer;

    public PostService(SpringDataDynamoPostRepository repository, SpringDataDynamoTagRepository tagRepository, KafkaProducer producer) {
        this.postRepository = repository;
        this.tagRepository = tagRepository;
        this.producer = producer;
    }

    public Long createPost(Long writerId, String title, String content, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        Long postId = Integer.toUnsignedLong(secureRandom.nextInt());
        Post post = new Post(postId, writerId, title, content, tagList);
        Post save = postRepository.save(post);
        for (String tagName: tagList){
            log.info("Tag name is {}", tagName);
            tagRepository.findById(tagName).ifPresent(tag -> {
                List<Long> userIds = tag.getUserIds();
                for (Long userId: userIds){
                    if (Objects.equals(userId, writerId)) continue;
                    NotiMessageDto notiMessageDto = new NotiMessageDto(userId, postId, title);
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
