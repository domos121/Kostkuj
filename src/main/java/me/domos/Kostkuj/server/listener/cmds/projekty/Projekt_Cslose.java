package me.domos.Kostkuj.server.listener.cmds.projekty;

import me.domos.Kostkuj.connect.MySQL.Projekty.MySQLGetProjekt;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Projekt_Cslose {

    SendSystem ss = new SendSystem();
    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();

    public void complete(CommandSender sr, String[] args){
        if (args.length == 1){
            ss.info(sr, "Use: /project close <id>");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadané id!");
            return;
        }

        int id = Integer.parseInt(args[1]);

        if (mySQLGetProjekt.isComplete(id)){
            ss.info(sr, "Projekt už je zrušen nebo je dokoncen.");
            return;
        }

        if (mySQLGetProjekt.isAccept(id)) {
            ss.info(sr, "Projekt byl oznacen jako zruseny!");
            mySQLGetProjekt.sendMessage(id, "<h2 style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><strong><span style=\"color: #ff0000; text-decoration: underline;\">PROJEKT ZRUSEN!</span></strong></span></h2>", sr.getName());
            mySQLGetProjekt.close(id, sr);
            return;
        } else {
            ss.info(sr, "Projekt byl oznacen jako zruseny!");
            mySQLGetProjekt.sendMessage(id, "<h2 style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><strong><span style=\"color: #ff0000; text-decoration: underline;\">PROJEKT ZRUSEN!</span></strong></span></h2>", sr.getName());
            mySQLGetProjekt.closes(id, sr);
            return;
        }
    }
}
