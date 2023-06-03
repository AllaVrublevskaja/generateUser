import java.util.UUID;

public class Friend {
    private UUID subscriberId;
    private UUID userId;
    private boolean isAccepted;

    public Friend(UUID subscriberId, UUID userId, boolean isAccepted) {
        this.subscriberId = subscriberId;
        this.userId = userId;
        this.isAccepted = isAccepted;
    }

    public UUID getSubscriberId() {
        return subscriberId;
    }

    public UUID getUserId() {
        return userId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setSubscriberId(UUID subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return subscriberId + " , " + userId +" , " + isAccepted;
    }
}
