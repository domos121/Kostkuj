package by.domos.Kostkuj.server.listener.cmds.projekty;

import by.domos.Kostkuj.connect.MySQL.Projekty.MySQLGetProjekt;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Projekt_Accept {

    SendSystem ss = new SendSystem();
    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();

    public void accept(CommandSender sr, String[] args){
        if (args.length == 1){
            ss.info(sr, "Use: /project accept <id>");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadané id!");
            return;
        }

        int id = Integer.parseInt(args[1]);

        if (!mySQLGetProjekt.isAccept(id)) {
            mySQLGetProjekt.accept(id, sr);
            ss.info(sr, "Projekt schválen!");
            mySQLGetProjekt.sendMessage(id, "<h2 style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><strong><span style=\"color: #00ff00; text-decoration: underline;\">PROJEKT SCHVALEN</span></strong></span></h2>", sr.getName());
        } else {
            ss.info(sr, "Projekt uz je schvalen!");
        }
    }
}
