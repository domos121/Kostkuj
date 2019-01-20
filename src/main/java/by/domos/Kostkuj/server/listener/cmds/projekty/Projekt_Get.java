package by.domos.Kostkuj.server.listener.cmds.projekty;

import by.domos.Kostkuj.connect.MySQL.Projekty.MySQLGetProjekt;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Projekt_Get {

    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();
    SendSystem ss = new SendSystem();

    public void getProjekts(CommandSender sr, String[] args){
        if (args.length == 1){
            mySQLGetProjekt.getProjekts(sr);
            return;
        }

        mySQLGetProjekt.getProjekt(args[1], sr);
    }
}