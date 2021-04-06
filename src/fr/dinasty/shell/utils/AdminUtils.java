package fr.dinasty.shell.utils;

import fr.dinasty.shell.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminUtils {
    public static boolean isAdmin(String username)
    {
        return Main.isAdmin();
    }

    public static void runCommand(String command, String UID)
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "" +
                "runuser $(getent passwd "+UID+" | cut -d : -f 1) -c "+"\"cd "+Main.getPwd()+" && " + command+"\"");
        try
        {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader
                    (
                            new InputStreamReader(process.getInputStream())
                    );
            String line;
            while((line = reader.readLine()) != null)
            {
                output.append(line).append("\n");
            }
            int exitVal = process.waitFor();
            if(exitVal == 0)
            {
                System.out.println(Colors.GREEN +"Success!" + Colors.RESET);
            }
            else
            {
                System.out.println(Colors.RED+"Echec!" + Colors.RESET);
            }
            System.out.println(output.toString());
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
