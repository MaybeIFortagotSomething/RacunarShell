package fr.dinasty.shell.utils;

import java.io.*;

public class FileUtils
{
    public static void createFile(File file) throws IOException
    {
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static void save(File file, String text)
    {
        final FileWriter fileWriter;

        try
        {
            createFile(file);
            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void remove(File file) throws SecurityException
    {
        if(!file.exists())
            return;
        file.delete();
    }

    public static String loadContent(File file)
    {
        if(!file.exists())
        {
            return "";
        }

        try
        {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            final StringBuilder stringBuilder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        return "";
    }

    public static boolean canBeAdd(File file)
    {
        return file.exists() && file.isDirectory();
    }
}
