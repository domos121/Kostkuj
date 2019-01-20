package me.domos.Kostkuj.server.time;

public class restartingTimerSettings {

    private restartingTimerSettings() {
    }

    static restartingTimerSettings instance = new restartingTimerSettings();

    public static restartingTimerSettings getInstance() {
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
