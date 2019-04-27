package me.domos.kostkuj.bukkit.listeners.cmds.stavba;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.general.connect.mysql.stavba.MStavba;
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
