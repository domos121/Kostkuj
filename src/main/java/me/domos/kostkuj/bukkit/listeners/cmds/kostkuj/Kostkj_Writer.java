package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.forFun.gameEvents.writer.WriteConfig;
import me.domos.kostkuj.general.forFun.gameEvents.writer.WriterCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkj_Writer {


    private SendSystem ss = new SendSystem();

    public Kostkj_Writer() {
    }

    public boolean onCommand(CommandSender sr, String[] args) {
        if (WriteConfig.isStart()) {
            this.ss.info(sr, "Event momentálně probíhá.\n§6Otázka: §a" + WriteConfig.getQuestion());
            return true;
        } else {
            new WriterCore((Player)sr, (String)null, (String)null);
            return true;
        }
    }
}
