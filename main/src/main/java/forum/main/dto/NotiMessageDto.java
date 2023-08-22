package forum.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotiMessageDto {
    private Long userId;
    private Long postId;

    public NotiMessageDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
