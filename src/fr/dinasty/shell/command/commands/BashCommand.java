package fr.dinasty.shell.command.commands;

import fr.dinasty.shell.Main;
import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandExecutor;
import fr.dinasty.shell.events.CommandReceivedEvent;
import fr.dinasty.shell.utils.AdminUtils;

public class BashCommand implements CommandExecutor {
    @Override
    public void run(CommandReceivedEvent event, Command command, String[] args)
    {
        if(event.isAdmin())
            AdminUtils.runCommand(event.getMessage(), "0");
        else
            AdminUtils.runCommand(event.getMessage(), Main.getUIDUser());
    }
}
