package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.connect.MySQL.player.Statisticks;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Kostkuj_TopTime {

    private SendSystem ss = new SendSystem();
    private Statisticks stat = new Statisticks();


    public boolean onCommand(CommandSender sr, String[] args) {
        sr.sendMessage(" §8 ======= §7TOP 10: §cTOPTIME §8======= ");
        stat.getTime(sr, sr.getName());
        return false;
    }
}
