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
        String[] args0 = args[0].trim().split("/");
        if(args0[0].trim().equals(".."))
        {
            reRun(event,command,args0, 0);
            return;
        }
        System.out.println(args0[0]);
        System.out.println("test");
        File file = new File(Main.getPwd()+args[0]);
        if(FileUtils.canBeAdd(file)) {
            Main.setPwd(Main.getPwd() + args[0] + "/");
            return;
        }
        System.out.println(Main.getPwd()+args[0]+"/");
        System.out.println(Colors.RED+"Error: "+args[0]+" is not a directory"+Colors.RESET);
    }

    private void reRun(CommandReceivedEvent event, Command command, String[] args, int i)
    {
        if(args.length == i)
            return;

        if(args[i].equals(".."))
        {
            File parent = new File(Main.getPwd()).getParentFile();
            if(!parent.exists())
            {
                Main.setPwd("/");
                reRun(event, command, args, ++i);
                return;
            }
            Main.setPwd(parent.getAbsolutePath()+"/");
            System.out.println(parent.getAbsolutePath()+"/");
            reRun(event, command, args, ++i);
            return;
        }
        System.out.println("deuxieme test");
        File file = new File(Main.getPwd()+args[i]);
        if(FileUtils.canBeAdd(file)) {
            Main.setPwd(Main.getPwd() + args[i] + "/");
            reRun(event, command, args, ++i);
            return;
        }
        System.out.println(Main.getPwd()+args[i]+"/");
        System.out.println(Colors.RED+"Error: "+args[i]+" is not a directory"+Colors.RESET);
    }
}
