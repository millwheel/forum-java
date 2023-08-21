package forum.main.service;

import forum.main.entity.Post;
import forum.main.repository.SpringDataDynamoPostRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final SpringDataDynamoPostRepository repository;

    public PostService(SpringDataDynamoPostRepository repository) {
        this.repository = repository;
    }

    public long createPost(long userId, String content, List<String> tagList){
        SecureRandom secureRandom = new SecureRandom();
        int postId = secureRandom.nextInt();
        Post post = new Post(postId, userId, content, tagList);
        Post save = repository.save(post);
        return save.getPostId();
    }

    public Optional<Post> readPost(long postId){
        return repository.findById(postId);
    }
}
