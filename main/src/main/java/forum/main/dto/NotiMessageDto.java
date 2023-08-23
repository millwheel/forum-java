package forum.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotiMessageDto {
    private Long userId;
    private Long postId;
    private String title;

    public NotiMessageDto(Long userId, Long postId, String title) {
        this.userId = userId;
        this.postId = postId;
        this.title = title;
    }
}
