package by.domos.Kostkuj.server.listener.cmds.stavba;

import by.domos.Kostkuj.connect.MySQL.stavba.MStavba;
import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class CMDStavba_Add {

    private SendSystem ss = new SendSystem();

    private MStavba mStavba = new MStavba();

    public void add(CommandSender sr, String[] args){
        int nonAktive = mStavba.getNonAktive();

        if (nonAktive == 0){
            ss.info(sr, "Neni žádné aktivní hlasování");
            return;
        } else if (nonAktive == -1){
            ss.info(sr, "Problém s komunikací");
            return;
        }

        if (args.length <= 2){
            ss.use(sr, ECmd.STAVBA_ADD.getCmd() + " <id_fora> <url_obrázku>");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadané forum!");
            return;
        }

        int idfora = Integer.parseInt(args[1]);

        String url = args[2];

        if (!mStavba.isForumExist(idfora)){
            ss.info(sr, "Forum neexistuje!");
            return;
        }

        if (!mStavba.isImgExist(url)){
            ss.info(sr, "Obrázek neexistuje!");
            return;
        }

        mStavba.addPosition(url, idfora, nonAktive);
        ss.info(sr, "Pozice přidána.");
    }
}
