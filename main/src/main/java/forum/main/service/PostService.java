package forum.main.service;

import forum.main.entity.Post;
import forum.main.entity.Tag;
import forum.main.repository.SpringDataDynamoPostRepository;
import forum.main.repository.SpringDataDynamoTagRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final SpringDataDynamoPostRepository postRepository;
    private final SpringDataDynamoTagRepository tagRepository;

    public PostService(SpringDataDynamoPostRepository repository, SpringDataDynamoTagRepository tagRepository) {
        this.postRepository = repository;
        this.tagRepository = tagRepository;
    }

    public long createPost(long userId, String content, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        int postId = secureRandom.nextInt();
        Post post = new Post(postId, userId, content, tagList);
        Post save = postRepository.save(post);
        for (String tagName: tagList){
            Optional<Tag> tagOptional = tagRepository.findById(tagName);
            if (tagOptional.isPresent()){

            }
        }
        return save.getPostId();
    }

    public Optional<Post> readPost(long postId){
        return postRepository.findById(postId);
    }
}
