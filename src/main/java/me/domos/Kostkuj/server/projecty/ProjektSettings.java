package me.domos.Kostkuj.server.projecty;

import java.util.HashMap;

public class ProjektSettings {
    static HashMap<String, Integer> compileCommand = new HashMap<String, Integer>();

    public static void addCompileCommand(String user, int projectid){
        compileCommand.put(user, projectid);
    }

    public static int getProjectId(String user){
        return compileCommand.get(user);
    }

    public static boolean isCompileCommand(String user){
        try {
            if (compileCommand.containsKey(user)) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public static void removeCompileCommand(String user){
        compileCommand.remove(user);
    }
}
