package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.connect.MySQL.player.Statisticks;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_TopTime {

    SendSystem ss = new SendSystem();
    Statisticks stat = new Statisticks();


    public boolean onCommand(CommandSender sr, String[] args) {
        Player p = Bukkit.getPlayer(sr.getName());
        sr.sendMessage(" §8 ======= §7TOP 10: §cTOPTIME §8======= ");
        stat.getTime(sr, sr.getName());
        return false;
    }
}
