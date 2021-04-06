package fr.dinasty.shell.command.commands;

import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandExecutor;
import fr.dinasty.shell.command.CommandManager;
import fr.dinasty.shell.events.CommandReceivedEvent;

public class InfoCommand implements CommandExecutor {
    @Override
    public void run(CommandReceivedEvent event, Command command, String[] args)

    {
        for(Command cmd: CommandManager.getRegistry().getCommands())
        {
            StringBuilder aliases = new StringBuilder();
            for(int i =0; i< cmd.getAliases().length; i++)
            {
                aliases.append(cmd.getAliases()[i]);
                if(i < cmd.getAliases().length-1)
                    aliases.append(", ");
            }

            String stringBuilder = "Name: " + cmd.getId() + "\n" +
                    "Description: " + cmd.getDescription() + "\n" +
                    "Alias: " + aliases.toString();

            System.out.println(stringBuilder);
            System.out.println();
        }
    }
}