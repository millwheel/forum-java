package forum.main.controller;


import forum.main.dto.PostResponseDto;
import forum.main.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public void getPost(){

    }

    @PostMapping("/post")
    public void createPost(@RequestBody PostResponseDto postResponseDto){

    }
}
