package by.domos.Kostkuj.server.listener.cmds.CraftCoin;

import by.domos.Kostkuj.server.player.PlayerManager;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import org.bukkit.command.CommandSender;

public class Voucher_Help{


    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    public void help(CommandSender sr, String[] args){
        PlayerManager.helpMenu(sr, "voucher.");
    }
}
