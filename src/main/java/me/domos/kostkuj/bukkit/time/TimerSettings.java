package me.domos.kostkuj.bukkit.time;

public class TimerSettings {

    private TimerSettings() {
    }

    static TimerSettings instance = new TimerSettings();

    public static TimerSettings getInstance() {
        return instance;
    }

    private int time;
    private boolean stop = true;

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public boolean isStop() {
        return this.stop;
    }

    public void setStop(boolean stop){
        this.stop = stop;
    }
}
