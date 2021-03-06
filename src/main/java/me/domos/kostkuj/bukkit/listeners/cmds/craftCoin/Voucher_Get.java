package me.domos.kostkuj.bukkit.listeners.cmds.craftCoin;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MVoucher;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class Voucher_Get {


    MVoucher mVoucher = new MVoucher();
    SendSystem ss = new SendSystem();

    public void get(CommandSender sr, String[] args){

        //voucher get [true|false] [int]

        if (args.length == 1){
            ss.use(sr, ECmd.VOUCHER_GET.getCmd() + " <true|false> [limit]");
            return;
        }

        int limit = 0;
        int complete;

        if (args[1].equalsIgnoreCase("true")){
            complete = 1;
        } else if (args[1].equalsIgnoreCase("false")){
            complete = 0;
        } else {
            ss.use(sr, ECmd.VOUCHER_GET.getCmd() + "<true|false> [limit]");
            return;
        }

        if (args.length == 3) {
            if (!StringUtils.isNumeric(args[2])) {
                ss.info(sr, "Limita neni cislo");
                return;
            }
            limit = Integer.parseInt(args[2]) * 10;
        }

        if (limit < 0){
            limit = 0;
        }

        mVoucher.getVouchers(sr, complete ,limit);



    }

}
