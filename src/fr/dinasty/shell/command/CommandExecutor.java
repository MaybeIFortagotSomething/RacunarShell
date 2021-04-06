package fr.dinasty.shell.command;

import fr.dinasty.shell.events.CommandReceivedEvent;

public interface CommandExecutor {
    void run(CommandReceivedEvent event, Command command, String[] args);
}
