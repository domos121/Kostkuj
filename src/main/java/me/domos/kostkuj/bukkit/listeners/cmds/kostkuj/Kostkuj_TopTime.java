package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.player.Statisticks;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_TopTime {

    private SendSystem ss = new SendSystem();
    private Statisticks stat = new Statisticks();


    public boolean onCommand(CommandSender sr, String[] args) {
        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }
        sr.sendMessage(" §8 ======= §7TOP 10: §cTOPTIME §8======= ");
        stat.getTime(sr, sr.getName());
        return false;
    }
}
