package me.domos.kostkuj.bukkit.listeners.cmds.projekty;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.projekty.MySQLGetProjekt;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Projekt_Complete {

    SendSystem ss = new SendSystem();
    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();

    public void complete(CommandSender sr, String[] args){
        if (args.length == 1){
            ss.info(sr, "Use: /project complete <id>");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadané id!");
            return;
        }

        int id = Integer.parseInt(args[1]);

        if (!mySQLGetProjekt.isAccept(id)) {
            ss.info(sr, "Projekt není schválen!");
        } else {
            if (!mySQLGetProjekt.isComplete(id)) {
                ss.info(sr, "Projekt byl oznacen jako dokonceny!");
                mySQLGetProjekt.sendMessage(id, "<h2 style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff; text-decoration: underline;\">PROJEKT DOKONCEN!</span></strong></span></h2>", sr.getName());
                mySQLGetProjekt.complete(id, sr);
            } else {
                ss.info(sr, "Projekt už je dokoncen!");
            }
        }
    }
}
