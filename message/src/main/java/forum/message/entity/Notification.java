package forum.message.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "notification", timeToLive = 300)
public class Notification {
    @Id
    private Long notiId;
    private String title;

    public Notification(Long notiId, String title) {
        this.notiId = notiId;
        this.title = title;
    }
}
