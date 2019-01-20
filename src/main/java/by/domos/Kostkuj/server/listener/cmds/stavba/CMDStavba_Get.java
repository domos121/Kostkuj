package by.domos.Kostkuj.server.listener.cmds.stavba;

import by.domos.Kostkuj.connect.MySQL.stavba.MStavba;
import org.bukkit.command.CommandSender;

public class CMDStavba_Get {

    private MStavba mStavba = new MStavba();

    public void get(CommandSender sr, String[] args){

        mStavba.get(sr);

    }

}
