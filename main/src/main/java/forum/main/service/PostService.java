package forum.main.service;

import forum.main.dto.NotiMessageDto;
import forum.main.entity.Post;
import forum.main.entity.Tag;
import forum.main.service.messaging.KafkaProducer;
import forum.main.repository.SpringDataDynamoPostRepository;
import forum.main.repository.SpringDataDynamoTagRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    private final SpringDataDynamoPostRepository postRepository;
    private final SpringDataDynamoTagRepository tagRepository;
    private final KafkaProducer producer;

    public PostService(SpringDataDynamoPostRepository repository, SpringDataDynamoTagRepository tagRepository, KafkaProducer producer) {
        this.postRepository = repository;
        this.tagRepository = tagRepository;
        this.producer = producer;
    }

    public Long createPost(Long userId, String content, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        Long postId = Integer.toUnsignedLong(secureRandom.nextInt());
        Post post = new Post(postId, userId, content, tagList);
        Post save = postRepository.save(post);
        for (String tagName: tagList){
            Optional<Tag> tagOptional = tagRepository.findById(tagName);
            if (tagOptional.isPresent()){
                Tag tag = tagOptional.get();
                List<Long> userList = tag.getUserList();
                for (Long id: userList){
                    if (Objects.equals(id, userId)) continue;
                    long messageCacheId = userId + postId;
                    NotiMessageDto notiMessageDto = new NotiMessageDto(id, postId);
                    producer.sendMessage(notiMessageDto);
                }
            }
        }
        return save.getPostId();
    }

    public Optional<Post> readPost(Long postId){
        return postRepository.findById(postId);
    }
}
