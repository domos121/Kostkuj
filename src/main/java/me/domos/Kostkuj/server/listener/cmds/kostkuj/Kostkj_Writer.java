package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.event.writer.WriteConfig;
import me.domos.Kostkuj.server.event.writer.WriterCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkj_Writer {


    private SendSystem ss = new SendSystem();

    public boolean onCommand(CommandSender sr, String args[]){

        if (WriteConfig.isStart()){
            ss.info(sr, "Event momentálně probíhá.\n§6Otázka: §a" + WriteConfig.getQuestion());
            return true;
        }

        new WriterCore((Player) sr, null, null);

        return true;
    }
}
