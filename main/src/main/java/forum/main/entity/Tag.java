package forum.main.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "forum_tag")
public class Tag {

    @DynamoDBHashKey
    private long tagId;
    @DynamoDBAttribute
    private String tagName;
    @DynamoDBAttribute
    private List<Long> userList;
}
