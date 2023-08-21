package forum.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotiMessageDto {
    private Long userId;
    private String tag;

    public NotiMessageDto(Long userId, String tag) {
        this.userId = userId;
        this.tag = tag;
    }
}
