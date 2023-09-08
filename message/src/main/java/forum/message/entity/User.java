package forum.message.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@DynamoDBTable(tableName = "forum_user")
@NoArgsConstructor
public class User {

    @DynamoDBHashKey
    private Long userId;
    @DynamoDBAttribute
    private String username;
    @DynamoDBAttribute
    private List<String> tagList;
    @DynamoDBAttribute
    private String token;

}