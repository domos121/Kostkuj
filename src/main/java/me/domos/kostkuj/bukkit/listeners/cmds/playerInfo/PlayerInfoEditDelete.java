package me.domos.kostkuj.bukkit.listeners.cmds.playerInfo;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerInfo;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class PlayerInfoEditDelete {

    private SendSystem ss = new SendSystem();

    boolean onCommand(MPlayerInfo mpi, String[] args, CommandSender sr) {
        if (args.length == 3){
            this.ss.info(sr, "Dosaď id");
            return true;
        }
        if (!StringUtils.isNumeric(args[3])) {
            this.ss.info(sr, "ID není číslo");
            return true;
        }

        int userid = mpi.getUser_id();
        if (Integer.parseInt(args[3]) == userid){
            mpi.deleteUser(args[0], Integer.parseInt(args[3]));
            this.ss.info(sr, "Uživatel " + args[0] + " [" + args[3] + "] " + "byl odstraněn.");
        } else {
            this.ss.info(sr, "Nesedí podmínky.");
        }
        return false;
    }
}
