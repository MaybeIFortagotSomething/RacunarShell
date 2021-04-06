package fr.dinasty.shell.utils;

import fr.dinasty.shell.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.io.CharStreams;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import static java.util.Arrays.asList;

public class AdminUtils {
    public static boolean isAdmin(String username)
    {
        return Main.isAdmin();
    }

    private static final String SSH_HOST = "127.0.0.1";
    private static final String SSH_LOGIN = "root";
    private static final String SSH_PASSWORD = "6&yA4iMUUb>;wYuF/5+dzn25>848W?L_";

    public static List<String> runCommand(String command, String UID) throws JSchException {
        Session session = setupSshSession();
        session.connect();

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        try {
            channel.setCommand("runuser $(getent passwd "+UID+" | cut -d : -f 1) -c "+"\"cd "+Main.getPwd()+" && " + command+"\"");
            channel.setInputStream(null);
            InputStream output = channel.getInputStream();
            channel.connect();

            String result = CharStreams.toString(new InputStreamReader(output));
            return asList(result.split("\n"));

        } catch (JSchException | IOException e) {
            closeConnection(channel, session);
            throw new RuntimeException(e);

        } finally {
            closeConnection(channel, session);
        }
    }

    private static Session setupSshSession() throws JSchException {
        Session session = new JSch().getSession(SSH_LOGIN, SSH_HOST, 22);
        session.setPassword(SSH_PASSWORD);
        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

    private static void closeConnection(ChannelExec channel, Session session) {
        try {
            channel.disconnect();
        } catch (Exception ignored) {
        }
        session.disconnect();
    }
}
