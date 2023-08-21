package forum.main.controller;


import forum.main.dto.PostRequestDto;
import forum.main.dto.PostResponseDto;
import forum.main.entity.Post;
import forum.main.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public void createPost(@RequestBody PostRequestDto postRequestDto){
        postService.createPost(postRequestDto.getUserId(), postRequestDto.getContent(), postRequestDto.getTagList());
    }

    @GetMapping("/post")
    public PostResponseDto getPost(@RequestParam long postId){
        Post post = postService.readPost(postId).orElseGet(null);
        return new PostResponseDto(post);
    }
}
