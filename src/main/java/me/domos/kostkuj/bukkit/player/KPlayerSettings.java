package me.domos.kostkuj.bukkit.player;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ECfg;

import java.util.HashMap;

public class KPlayerSettings {


    private static HashMap<String, Long> loginTime = new HashMap<>();

    public LoginTimeBuffer getLoginTime(String player) {
        SendSystem.domosTest("Pocet zaznamu loginTimeOut: " + loginTime.size());
        if (loginTime == null){
            return new LoginTimeBuffer(false, (long) 0);
        }
        if (loginTime.containsKey(player)){
            long current = System.currentTimeMillis() - loginTime.get(player);
            if ((current/1000) < ECfg.LOGIN_TIME_OUT_TIME.getInt()){
                return new LoginTimeBuffer(true, current/1000);
            }
            return new LoginTimeBuffer(false, (long) 0);
        } else {
            return new LoginTimeBuffer(false, (long) 0);
        }
    }

    public void setLoginTime(String player, long time) {
        loginTime.put(player, time);
    }

    public class LoginTimeBuffer {
        private boolean isAcces;
        private long time;

        public LoginTimeBuffer(boolean isAcces, long time){
            this.isAcces = isAcces;
            this.time = time;
        }

        public boolean isAcces() {
            return isAcces;
        }

        public long getTime() {
            return time;
        }

    }
}
