
import java.time.LocalDateTime;
import java.util.UUID;


public class User {

    private UUID id;
    private String nickname;
    private String password;
    private LocalDateTime dayOfBirth;

    public User(UUID id, String nickname, String password, LocalDateTime dayOfBirth) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDayOfBirth() {
        return dayOfBirth;
    }
}
