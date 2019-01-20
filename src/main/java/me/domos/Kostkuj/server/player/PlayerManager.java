package me.domos.Kostkuj.server.player;

import me.domos.Kostkuj.enums.ECmd;
import me.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import me.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;


public class PlayerManager{




    public boolean isPlayerGamemode(Player p){
        String gm = p.getGameMode().toString();
        if (gm == GameMode.CREATIVE.toString()){
            return true;
        }
        return false;
    }

    public String getNameFromUuid(UUID uuid){
        try {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            String name = player.getName().trim();
            return name;
        }catch (NullPointerException e){
            return uuid.toString();
        }
    }

    public static void helpMenu(CommandSender sr, String equels){
        JsonSendMessage jsm = new JsonSendMessage();
        CustomJsonBuilder cjb = new CustomJsonBuilder();
        sr.sendMessage("§8===== §7HELP: §c" + equels.replace(".", "") + " §8=====");
        for (int i = 0; ECmd.values().length > i; i++) {
            if (ECmd.values()[i].getPerm().contains(equels)) {
                String allow = "§8[§c✖§8] §7/";
                String suggest = "";
                String allowed = "§cOdepren";
                if (sr.hasPermission(ECmd.values()[i].getPerm())) {
                    allow = "§8[§a✔§8] §7/";
                    suggest = "suggest_command";
                    allowed = "§aPovolen";
                }
                String json = cjb.clickhoverText(allow + ECmd.values()[i].getCmd(), "", "§c" + ECmd.values()[i].getCmd() + "§7:\n Pristup: §c" + allowed + "§7\n Permission: §c" + ECmd.values()[i].getPerm(), "/" + ECmd.values()[i].getCmd(), suggest);
                jsm.jsonBcKostkuj(sr, json);
            }
        }
    }



}
