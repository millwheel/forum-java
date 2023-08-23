package forum.main.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private Long userId;
    private String title;
    private String content;
    private List<String> tagList;

}
