package fr.dinasty.shell.command;

import fr.dinasty.shell.command.commands.BashCommand;
import fr.dinasty.shell.command.commands.CdCommand;
import fr.dinasty.shell.command.commands.FlagCommand;
import fr.dinasty.shell.command.commands.InfoCommand;
import fr.dinasty.shell.events.CommandReceivedEvent;

import java.util.Arrays;

public class CommandManager {
    private static CommandRegistry registry = new CommandRegistry();
    static {
        registry.addCommand(new Command(
                "flag",
                "Display the flag",
                new FlagCommand(),
                new String[]{"solution", "flag", "drapeau"}
        ));
        registry.addCommand(new Command(
                "cd",
                "Change your current directory",
                new CdCommand(),
                new String[]{"cd"}
        ));
        registry.addCommand(new Command(
                "infoRacunar",
                "Display all custom command",
                new InfoCommand(),
                new String[]{"infoRacunar", "helpRacunar", "inforacunar", "helpracunar"}
        ));
    }

    public static void onMessageReceived(CommandReceivedEvent event)
    {
            String[] args = event.getMessage().split(" ");
            String commandName = args[0];
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length);
            String[] finalArgs = args;
            if(registry.getByAlias(commandName).isPresent())
            {
                Command cmd = registry.getByAlias(commandName).get();
                cmd.getExecutor().run(event, cmd, finalArgs);
            }
            else
            {
                Command cmd = new Command("bash", "Execute your command on bash", new BashCommand(), new String[]{"Bash"});
                cmd.getExecutor().run(event, cmd, args);
            }
    }

    public static CommandRegistry getRegistry() {
        return registry;
    }
}
