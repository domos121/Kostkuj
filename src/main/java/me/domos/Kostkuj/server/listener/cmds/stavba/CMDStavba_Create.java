package me.domos.Kostkuj.server.listener.cmds.stavba;

import me.domos.Kostkuj.connect.MySQL.stavba.MStavba;
import me.domos.Kostkuj.enums.ECmd;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class CMDStavba_Create {

    private SendSystem ss = new SendSystem();
    private MStavba mStavba = new MStavba();

    public void createVote(CommandSender sr, String[] args){
        String popis = "";

        if (args.length == 1){
            ss.use(sr, ECmd.STAVBA_CREATE.getCmd() + " <popis>");
            return;
        }

        for (int i = 1; i < args.length; i++){
            popis = popis + args[i] + " ";
        }

        if (mStavba.isVote()){
            ss.info(sr, "Hlasování už je vytvořené.");
            return;
        }

        ss.info(sr, "Hlasování bylo vytvořeno.");
        mStavba.create(sr.getName(), popis);
    }
}
