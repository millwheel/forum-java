package forum.main.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRequestDto {
    private String username;
    private List<String> keywordList;
}
