package me.domos.kostkuj.bukkit.listeners.cmds.projekty;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.projekty.MySQLGetProjekt;
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
