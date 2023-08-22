package forum.main.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "notification", timeToLive = 300)
public class Notification {
    @Id
    private Long notiId;
    private int value = 1;

    public Notification(Long notiId) {
        this.notiId = notiId;
    }
}
