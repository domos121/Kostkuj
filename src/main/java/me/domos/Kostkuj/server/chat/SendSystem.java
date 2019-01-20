package me.domos.Kostkuj.server.chat;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendSystem {

    public void info(CommandSender sr, String msg){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + msg);
    }

    public void info(Player p, String msg){
        p.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + msg);
    }

    public void noPerm(Player p){
        p.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + EMessages.NO_PERMISSIONS.getValue());
    }

    public void noPerm(CommandSender sr){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + EMessages.NO_PERMISSIONS.getValue());
    }

    public void broadCast(String msg){
        Bukkit.getServer().broadcastMessage(EMessages.PLUGIN_PREFIX.getValue() + msg.replace("&", "§"));
        DiscordConnect.sendMsg("```fix\n" + EMessages.PLUGIN_PREFIX.getValue() + msg + "\n```");
    }

    public void use(CommandSender sr, String msg){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + "Use: /" + msg + ".");
    }

    public String boolenTranslate(boolean ss){
        if (ss){
            return "§aAno";
        } else {
            return "§cNe";
        }
    }

    public String boolenTranslateIsOnline(boolean ss){
        if (ss){
            return "§aOnline";
        } else {
            return "§4Offline";
        }
    }

    public String boolenTranslate(String ss){
        if(ss.equals("true")){
            return "§aAno";
        }else if(ss.equals("false")){
            return "§cNe";
        }else {
            return "§cVyskytla se chyba";
        }
    }

    public String boolenTranslate(int i){
        if(i == 1){
            return "§aAno";
        }else if(i == 0){
            return "§cNe";
        }else {
            return "§cVyskytla se chyba";
        }
    }
}
