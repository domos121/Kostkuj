package me.domos.kostkuj.bukkit.listeners.cmds.playerInfo;

import me.domos.kostkuj.general.connect.mysql.player.MPlayerInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class PlayerInfoEdit {

    public boolean onCommand(OfflinePlayer op, CommandSender sr, MPlayerInfo mpi, String[] args) {
        if (args.length == 2){
            sr.sendMessage("cc // delete // active");
            return false;
        }

        if (args[2].equalsIgnoreCase("cc")){
            if (sr.hasPermission("")){
                new PlayerInfoEditCc().onCommand(op, sr, args, mpi);
            }
        } else if (args[2].equalsIgnoreCase("delete")) {
            if (sr.hasPermission("")){
                new PlayerInfoEditDelete().onCommand(mpi, args, sr);
            }
        } else if (args[2].equalsIgnoreCase("active")) {
            if (sr.hasPermission("")){
                new PlayerInfoEditActive();
            }
        }
        return false;
    }
}
