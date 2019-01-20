package me.domos.Kostkuj.server.event.writer;

import org.bukkit.entity.Player;

public class WriteConfig {

    private static boolean start = false;
    private static String answer;
    private static String question;
    private static Player winPlayer;

    public static boolean isStart() {
        return WriteConfig.start;
    }

    public static void setStart(boolean start) {
        WriteConfig.start = start;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer, String question) {
        WriteConfig.answer = answer;
        WriteConfig.question = question;
    }

    public static Player getWinPlayer() {
        return winPlayer;
    }

    public static void setWinPlayer(Player winPlayer) {
        WriteConfig.winPlayer = winPlayer;
        WriteConfig.start = false;
    }

    public static String getQuestion() {
        return question;
    }
}
