package Message;

import java.io.Serializable;

public class Message implements Serializable {

    private int id;
    private String login;
    private String message;
    private String users;

    /**
     * Creates a new instance of Message
     */
    public Message(int id, String login, String message){
        this.id = id;
        this.login = login;
        this.message = message;
    }

    public void setOnlineUsers(String users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

    public String getUsers() {
        return users;
    }


}