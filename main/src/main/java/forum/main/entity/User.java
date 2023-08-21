package forum.main.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@DynamoDBTable(tableName = "forum_user")
public class User {

    @DynamoDBHashKey
    private long userId;
    @DynamoDBAttribute
    private String username;
    @DynamoDBAttribute
    private List<String> tagList;

    public User(long userId, String username, List<String> tagList) {
        this.userId = userId;
        this.username = username;
        this.tagList = tagList;
    }
}
