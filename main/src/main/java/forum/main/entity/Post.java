package forum.main.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@DynamoDBTable(tableName = "forum_post")
public class Post {

    @DynamoDBHashKey
    private long postId;
    @DynamoDBAttribute
    private long userId;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private List<String> tagList;

    public Post(long postId, long userId, String content, List<String> tagList) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.tagList = tagList;
    }
}
