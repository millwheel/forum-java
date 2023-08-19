package forum.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private List<String> keywordList;

    public UserResponseDto(String username, List<String> keywordList) {
        this.username = username;
        this.keywordList = keywordList;
    }
}
