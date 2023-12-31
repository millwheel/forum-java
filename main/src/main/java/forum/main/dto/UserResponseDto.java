package forum.main.dto;

import forum.main.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponseDto {

    private Long userId;
    private String username;
    private List<String> tagList;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.tagList = user.getTagList();
    }

}
