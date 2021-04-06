package fr.dinasty.shell.events;

public class Event {
    private final boolean admin;
    private final String userName;
    public Event(boolean admin, String userName)
    {
        this.admin = admin;
        this.userName = userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getUserName() {
        return userName;
    }
}
