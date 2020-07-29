package me.domos.kostkuj.bukkit.listeners.cmds.playerInfo;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class PlayerInfoEditCc {
    SendSystem ss = new SendSystem();

    public boolean onCommand(OfflinePlayer op, CommandSender sr, String[] args, MPlayerInfo mpi){
        if (args.length == 3){
            this.ss.info(sr, "Zadej množství cc.");
            return true;
        }

        return false;
    }
}
