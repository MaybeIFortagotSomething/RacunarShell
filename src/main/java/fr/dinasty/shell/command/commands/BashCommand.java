package fr.dinasty.shell.command.commands;

import com.jcraft.jsch.JSchException;
import fr.dinasty.shell.Main;
import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandExecutor;
import fr.dinasty.shell.events.CommandReceivedEvent;
import fr.dinasty.shell.utils.AdminUtils;
import fr.dinasty.shell.utils.Colors;

import java.awt.*;

public class BashCommand implements CommandExecutor {
    @Override
    public void run(CommandReceivedEvent event, Command command, String[] args) throws JSchException {
        if(event.isAdmin())
            System.out.println(Color.RED +"Admin Output -> "+ Colors.RESET +AdminUtils.runCommand(event.getMessage(), "0").toString());
        else
            System.out.println(Color.CYAN +"User Output -> "+ Colors.RESET +AdminUtils.runCommand(event.getMessage(), "1000").toString());
    }
}
