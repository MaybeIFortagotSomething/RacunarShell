package fr.dinasty.shell;

import fr.dinasty.shell.command.Command;
import fr.dinasty.shell.command.CommandManager;
import fr.dinasty.shell.events.CommandReceivedEvent;
import fr.dinasty.shell.utils.AdminUtils;
import fr.dinasty.shell.utils.Colors;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static String motd;
    private static String issue = "======== RacunarShell Version 1.0 ==========";
    private static String userName, UIDUser, pwd;
    private static Scanner scanner;
    private static boolean Admin;
    public static void main(String[] args) throws UnknownHostException {
        Admin = false;
        pwd= System.getProperty("user.dir")+"/";
        UIDUser = "1000";
        userName = System.getProperty("user.name");
        motd = Colors.YELLOW +userName+"@"+java.net.InetAddress.getLocalHost().getHostName();
        scanner = new Scanner(System.in);
        System.out.println(issue);
        while(true)
        {
            System.out.print(motd+ " "+Colors.BRIGHT_BLUE+pwd+" "+Colors.RESET+" >> ");
            CommandManager.onMessageReceived(new CommandReceivedEvent(AdminUtils.isAdmin(userName), userName, scanner.nextLine()));
        }
    }

    public static void setMotd(String motd) {
        Main.motd = motd;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getUIDUser() {
        return UIDUser;
    }

    public static void setPwd(String pwd)
    {
        Main.pwd = pwd;
    }

    public static String getPwd()
    {
        return pwd;
    }

    public static boolean isAdmin() {
        return Admin;
    }
}
