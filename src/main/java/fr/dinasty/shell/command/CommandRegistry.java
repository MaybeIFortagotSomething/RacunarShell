package fr.dinasty.shell.command;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CommandRegistry {
    private ArrayList<Command> commands;
    public CommandRegistry()
    {
        commands = new ArrayList<Command>();
    }

    public void addCommand(Command cmd)
    {
        commands.add(cmd);
    }

    public void removeCommand(String id)
    {
        commands.removeIf((cmd)-> cmd.getId().equalsIgnoreCase(id));
    }

    public Optional<Command> getByAlias(String alias)
    {
        for (Command command : commands) {
            if(Arrays.asList(command.getAliases()).contains(alias))
            {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
