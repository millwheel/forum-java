package forum.main;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

public class UserIdCreateTest {

    public static final AtomicInteger serialNumber = new AtomicInteger(0);

    @Test
    public void getLocalhostIP(){
        try {
            InetAddress ipAddress = InetAddress.getLocalHost();
            String hostAddress = ipAddress.getHostAddress();
            System.out.println(hostAddress);
            Long lastIp = Long.parseLong(hostAddress.split("\\.")[3]);
            System.out.println(lastIp);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createUserIdTest(){
        String currentTime = Long.toString(System.currentTimeMillis());
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String lastIpNumber = hostAddress.split("\\.")[3];
        String serialNow = "512";
        long userId = Long.parseLong(currentTime + lastIpNumber + serialNow);
        System.out.println(userId);
    }
}
