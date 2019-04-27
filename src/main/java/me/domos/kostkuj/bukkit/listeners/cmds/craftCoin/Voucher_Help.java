package me.domos.kostkuj.bukkit.listeners.cmds.craftCoin;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import org.bukkit.command.CommandSender;

public class Voucher_Help{


    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    public void help(CommandSender sr, String[] args){
        PlayerManager.helpMenu(sr, "voucher.");
    }
}
