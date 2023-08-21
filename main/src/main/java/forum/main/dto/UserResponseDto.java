package forum.main.dto;

import forum.main.entity.User;

import java.util.List;

public class UserResponseDto {
    private String username;
    private List<String> tagList;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.tagList = user.getTagList();
    }
}
