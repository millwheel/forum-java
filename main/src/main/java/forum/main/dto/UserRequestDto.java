package forum.main.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRequestDto {
    private String username;
    private List<String> keywordList;

    public UserRequestDto(String username, List<String> keywordList) {
        this.username = username;
        this.keywordList = keywordList;
    }
}
