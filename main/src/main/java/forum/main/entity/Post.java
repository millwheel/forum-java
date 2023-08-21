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
    private long postId;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private List<String> tagList;
}
