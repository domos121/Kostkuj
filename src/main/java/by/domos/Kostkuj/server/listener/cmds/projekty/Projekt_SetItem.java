package by.domos.Kostkuj.server.listener.cmds.projekty;

import by.domos.Kostkuj.connect.MySQL.Projekty.MySQLGetProjekt;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.projecty.ProjektSettings;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Projekt_SetItem {

    SendSystem ss = new SendSystem();
    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();

    public void setItem(CommandSender sr, String[] args){

        if (args.length == 1){
            ss.info(sr, "Use: /project setitem <projektid>");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadané id!");
            return;
        }


        int id = Integer.parseInt(args[1]);

        if (!mySQLGetProjekt.isAccept(id)){
            ss.info(sr, "Projekt nenei schvalen, Prosim o schvaleni projektu!");
            return;
        }

        if (!ProjektSettings.isCompileCommand(sr.getName())) {
            ProjektSettings.addCompileCommand(sr.getName(), id);
            ss.info(sr, "Prikaz pro kompilaci truhly je aktivovan!");
        } else {
            ss.info(sr, "Prikaz je již aktivovan!");
        }
    }
}
