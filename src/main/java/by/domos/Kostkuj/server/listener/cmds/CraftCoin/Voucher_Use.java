package by.domos.Kostkuj.server.listener.cmds.CraftCoin;

import by.domos.Kostkuj.connect.MySQL.CraftCoin.MVoucher;
import by.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Voucher_Use {

    MySQLget mySQLget = new MySQLget();
    SendSystem ss = new SendSystem();
    MVoucher mVoucher = new MVoucher();

    public void use(CommandSender sr, String[] args){
        if(mySQLget.getBoolean("SELECT * FROM shop_voucher WHERE serial_key = '" + args[1] + "' AND is_complete = 0")){
            if (mVoucher.voucherUpdate(sr, args[1])){
                MVoucher.ResAddCC addcc = mVoucher.addCC(sr, args[1]);
                if(addcc.isComplete()){
                    ss.info(sr, "Voucher aktivovan. \nNa tvůj účet bylo přičteno §a" + addcc.getCost() + "CC§7.");
                } else {
                    ss.info(sr, "Nepodařilo se přičíst CC.");
                }
            } else {
                ss.info(sr, "Nepodařilo se aktivovat kod!");
            }
        } else {
            ss.info(sr, "Kod §a" + args[1] + " §7není platný nebo je použitý.");
        }
    }


}
