package me.domos.kostkuj.bukkit.listeners.cmds.playerInfo;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class PlayerInfoMore {

    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private JsonSendMessage jsm = new JsonSendMessage();
    private SendSystem ss = new SendSystem();

    boolean onCommand(OfflinePlayer op, CommandSender sr, MPlayerInfo mpi){

        String acite = "§4Neaktivovan";
        if (mpi.getEnabled() == 1) {
            acite = "§aAktivovan";
        }
        
        sr.sendMessage("§8====== §7PLAYER: §c" + op.getName() + " §8======");
        this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7User: §c" + op.getName() + " §8[" + this.ss.boolenTranslateIsOnline(op.isOnline()) + "§8]", "", "§c" + op.getName() + ":\n §7Aktivace: " + acite + "\n §7UUID: §c" + op.getUniqueId().toString() + "\n §7UserID: §c" + mpi.getUser_id() + "\n §7RegisterDate: §c" + mpi.getRegisterdate() + "\n §7Op: §c" + this.ss.boolenTranslate(op.isOp()), "suggest_command", op.getUniqueId().toString()));
        this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Email: §c" + mpi.getEmail(), "", "", "", ""));
        this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7CraftCoins: §c" + mpi.getCc(), "", "", "", ""));
        return false;
    }
}
