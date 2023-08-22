package forum.main.dto;

import forum.main.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto {
    private Long userId;
    private String content;
    private List<String> tagList;

    public PostResponseDto(Post post) {
        userId = post.getWriterId();
        content = post.getContent();
        tagList = post.getTagList();
    }
}
