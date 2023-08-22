package forum.main.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "notification", timeToLive = 300)
public class Notification {
    @Id
    private String notiId;
    private int value = 1;

    public Notification(String notiId) {
        this.notiId = notiId;
    }
}
