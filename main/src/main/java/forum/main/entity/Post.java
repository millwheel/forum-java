package forum.main.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "forum_post")
public class Post {

    @DynamoDBHashKey
    private Long postId;
    @DynamoDBAttribute
    private Long writerId;
    @DynamoDBAttribute
    private String title;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private List<String> tagList;

    public Post(Long postId, Long writerId, String title, String content, List<String> tagList) {
        this.postId = postId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.tagList = tagList;
    }
}
