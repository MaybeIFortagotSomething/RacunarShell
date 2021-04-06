package fr.dinasty.shell.command;

import com.jcraft.jsch.JSchException;
import fr.dinasty.shell.events.CommandReceivedEvent;

public interface CommandExecutor {
    void run(CommandReceivedEvent event, Command command, String[] args) throws JSchException;
}
