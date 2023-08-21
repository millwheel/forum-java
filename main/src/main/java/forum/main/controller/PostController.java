package forum.main.controller;


import forum.main.dto.PostRequestDto;
import forum.main.dto.PostResponseDto;
import forum.main.entity.Post;
import forum.main.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public PostResponseDto getPost(@RequestParam Long postId){
        Post post = postService.readPost(postId).orElse(null);
        return new PostResponseDto(post);
    }

    @PostMapping
    public Long createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto.getUserId(), postRequestDto.getContent(), postRequestDto.getTagList());
    }
}
