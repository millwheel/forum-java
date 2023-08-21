package forum.main.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String content;
    private List<String> tagList;

}
