package me.domos.kostkuj.bukkit.listeners.cmds.craftCoin;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.general.KeyGenerator;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MVoucher;
import me.domos.kostkuj.general.connect.mysql.mysqlListener.MySQLget;
import net.minecraft.server.v1_13_R2.ChatClickable;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Voucher_Create {

    SendSystem ss = new SendSystem();
    MySQLget mySQLget = new MySQLget();
    KeyGenerator keyGenerator = new KeyGenerator();
    MVoucher mVoucher = new MVoucher();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    public void add(CommandSender sr,String[] args){
        if (args.length == 1){
            ss.use(sr, ECmd.VOUCHER_ADD.getCmd() + " [pocet]");
            return;
        }

        if (!StringUtils.isNumeric(args[1])) {
            ss.info(sr, "Spatne zadany pocet!");
            return;
        }

        String key = keyGenerator.getSerialKey(10);

        if (!mySQLget.getBoolean("SELECT serial_key FROM shop_voucher WHERE serial_key = '" + key + "'")) {
            if (mVoucher.createVoucher(Integer.parseInt(args[1]), key, "")){
                jsm.jsonBcKostkuj(sr,cjb.vetaClickHoverText("§7Voucher §a", "", key, "", "§6Kliknutím kod skopíruješ.", ChatClickable.EnumClickAction.SUGGEST_COMMAND.b(), key, " §7 (§a" + args[1] + "§7) byl úspěšně vytvořen.", ""));
            } else {
                ss.info(sr, "Nepodarilo se vytvorit kod, nastala chyba!");
            }
        } else {
            ss.info(sr, "Nepodarilo se vytvorit kod, zkus to prosim znova");
        }

    }
}
