package forum.main.controller;


import forum.main.dto.PostRequestDto;
import forum.main.dto.PostResponseDto;
import forum.main.entity.Post;
import forum.main.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto getPost(@PathVariable(name = "postId") Long postId){
        Post post = postService.readPost(postId).orElse(null);
        return new PostResponseDto(post);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto.getUserId(), postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getTagList());
    }
}
