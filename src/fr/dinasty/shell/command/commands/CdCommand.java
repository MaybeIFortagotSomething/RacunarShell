package fr.dinasty.shell.command.commands;

import fr.dinasty.shell.Main;
import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandExecutor;
import fr.dinasty.shell.events.CommandReceivedEvent;
import fr.dinasty.shell.utils.Colors;
import fr.dinasty.shell.utils.FileUtils;

import java.io.File;

public class CdCommand implements CommandExecutor
{

    @Override
    public void run(CommandReceivedEvent event, Command command, String[] args)
    {
        if(args.length == 0)
        {
            Main.setPwd(System.getProperty("user.dir")+"/");
            System.out.println(Colors.GREEN+"Let's back to home"+ Colors.RESET);
            return;
        }
        String[] args0 = event.getMessage().split("/");
        if(args0[0].equals(".."))
        {
            for(String i: args0)
            {
                if(i.equals(".."))
                {
                    File parent = new File(Main.getPwd()).getParentFile();
                    if(!parent.exists())
                    {
                        Main.setPwd("/");
                        return;
                    }
                    Main.setPwd(parent.getParent());
                }
            }
            return;
        }

        File file = new File(Main.getPwd()+args[0]);
        if(FileUtils.canBeAdd(file)) {
            Main.setPwd(Main.getPwd() + args[0] + "/");
            return;
        }
        System.out.println(Main.getPwd()+args[0]+"/");
        System.out.println(Colors.RED+"Error: "+args[0]+" is not a directory"+Colors.RESET);
    }
}
