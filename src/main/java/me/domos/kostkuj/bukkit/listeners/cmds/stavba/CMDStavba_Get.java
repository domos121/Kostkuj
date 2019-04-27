package me.domos.kostkuj.bukkit.listeners.cmds.stavba;

import me.domos.kostkuj.general.connect.mysql.stavba.MStavba;
import org.bukkit.command.CommandSender;

public class CMDStavba_Get {

    private MStavba mStavba = new MStavba();

    public void get(CommandSender sr, String[] args){

        mStavba.get(sr);

    }

}
