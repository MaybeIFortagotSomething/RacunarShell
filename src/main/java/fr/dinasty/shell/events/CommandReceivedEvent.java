package fr.dinasty.shell.events;

public class CommandReceivedEvent extends Event {
    private final String message;

    public CommandReceivedEvent(boolean admin, String userName, String message)
    {
        super(admin, userName);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}