package forum.main.dto;

import java.util.List;

public class UserResponseDto {
    private String username;
    private List<String> keywordList;

    public UserResponseDto(String username, List<String> keywordList) {
        this.username = username;
        this.keywordList = keywordList;
    }
}
