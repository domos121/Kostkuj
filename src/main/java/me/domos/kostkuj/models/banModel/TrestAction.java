package me.domos.kostkuj.models.banModel;

import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.trests.MTrestsIP;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;


public class TrestAction {

    private Time t = new Time();
    private MTrestsIP mti = new MTrestsIP();

    public int action(String action){
        switch (action.toLowerCase()){
            case "ban":
                return 1;
            case "tempban":
                return 2;
            case "ipban":
                return 3;
            case "tempipban":
                return 4;
            case "kick":
                return 5;
            case "mute":
                return 6;
        }
        return -1;
    }

    public String user_uuid(String user){

        OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(user);

        return op.getUniqueId().toString();
    }

    public int[] rule(String rule){
        String[] srule = rule.split(",");
        int[] error = {-1};
        int[] irule = new int[srule.length];
        try{
            for (int i = 0; srule.length > i; i++){
                irule[i] = Integer.parseInt(srule[i]);
            }
            return irule;
        }catch (ArrayIndexOutOfBoundsException ignored) {

        }catch (NumberFormatException ignored){

        }
        return error;
    }
        //15d
    public long time(String time){
        try {
            String jedn = "";
            String scurent = "";
            String[] stime = time.split("");
            for (int i = 0; stime.length > i; i++) {
                if (StringUtils.isNumeric(stime[i])) {
                    scurent = scurent + stime[i];
                } else {
                    jedn = jedn + stime[i];
                }
            }
            long current = Long.parseLong(scurent);

            long expirycur = t.getTimeForTemp(current, jedn);
            return expirycur;
        } catch (ArithmeticException e){
            return -1;
        }
    }

    public int ipid(String ipid, String user, int action){
        if(action == 5 || action == 6){
            return 0;
        }
        if (user == null){
            int id = Integer.parseInt(ipid);
            if (!mti.isIpExist(id)){
                return -1;
            }
            return id;
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(user);
            String uuid = op.getUniqueId().toString();
            int id = mti.getLastIP(uuid);
            if ((action == 1 || action == 2) && id == -1){
                return 0;
            } else {
                return id;
            }
        }
    }

}
