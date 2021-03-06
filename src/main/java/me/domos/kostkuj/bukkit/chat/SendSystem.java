package me.domos.kostkuj.bukkit.chat;

import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
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

    public void noPlayer(CommandSender sr){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + EMessages.NO_PLAYER.getValue());
    }

    public void noPerm(CommandSender sr){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + EMessages.NO_PERMISSIONS.getValue());
    }

    public void broadCast(String msg){
        Bukkit.getServer().broadcastMessage(EMessages.PLUGIN_PREFIX.getValue() + msg.replace("&", "§"));
        DiscordConnect.sendOrangeMsg(EMessages.PLUGIN_PREFIX.getValue() + msg);
    }

    public void use(CommandSender sr, String msg){
        sr.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + "Use: /" + msg + ".");
    }

    public static void domosTest(String msg){
        Bukkit.getServer().getConsoleSender().sendMessage("§6[DOMOS TEST]: §c" + msg);
    }

    public void debugMsg(String msg){
        if (ECfg.isAllowdebugMod()) {
            Bukkit.getServer().getConsoleSender().sendMessage("§8[§cKostkujDEBUG§8]:§a " + msg);
        }
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
