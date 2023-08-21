package forum.main.dto;

import forum.main.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long userId;
    private String content;
    private List<String> tagList;

    public PostResponseDto(Post post) {
        userId = post.getUserId();
        content = post.getContent();
        tagList = post.getTagList();
    }
}
