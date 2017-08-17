package model;

/**
 * Created by Hasmik on 01.07.2017.
 */
public class Message {
    private long id;
    private long fromId;
    private User fromUser;
    private long toId;
    private User toUser;
    private String message;
    private String timestamp;

    public Message(long id, long fromId, User fromUser, long toId, User toUser, String message, String timestamp) {
        this.id = id;
        this.fromId = fromId;
        this.fromUser = fromUser;
        this.toId = toId;
        this.toUser = toUser;
        this.message = message;
        this.timestamp = timestamp;
    }
    public Message() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (fromId != message1.fromId) return false;
        if (toId != message1.toId) return false;
        if (fromUser != null ? !fromUser.equals(message1.fromUser) : message1.fromUser != null) return false;
        if (toUser != null ? !toUser.equals(message1.toUser) : message1.toUser != null) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null) return false;
        return timestamp != null ? timestamp.equals(message1.timestamp) : message1.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (fromId ^ (fromId >>> 32));
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (int) (toId ^ (toId >>> 32));
        result = 31 * result + (toUser != null ? toUser.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", fromUser=" + fromUser +
                ", toId=" + toId +
                ", toUser=" + toUser +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
