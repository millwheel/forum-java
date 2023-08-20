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
@DynamoDBTable(tableName = "forum_user")
public class User {

    @DynamoDBHashKey
    private long userId;
    @DynamoDBAttribute
    private String username;
    @DynamoDBAttribute
    private List<String> keywordList;
}
