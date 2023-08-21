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
@DynamoDBTable(tableName = "forum_tag")
@NoArgsConstructor
public class Tag {

    @DynamoDBHashKey
    private String tagName;
    @DynamoDBAttribute
    private List<Long> userList;

    public Tag(String tagName, List<Long> userList) {
        this.tagName = tagName;
        this.userList = userList;
    }
}
