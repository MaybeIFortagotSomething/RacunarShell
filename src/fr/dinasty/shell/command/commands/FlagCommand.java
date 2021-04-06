package fr.dinasty.shell.command.commands;

import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandExecutor;
import fr.dinasty.shell.events.CommandReceivedEvent;
import fr.dinasty.shell.utils.Colors;

public class FlagCommand implements CommandExecutor {

    @Override
    public void run(CommandReceivedEvent event, Command command, String[] args)
    {
        System.out.println(Colors.GREEN+"Really I hesitate to give you the flag here but after reflexion just nop, have fun" + Colors.RESET);
    }
}
