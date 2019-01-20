package by.domos.Kostkuj.server.listener.cmds.CraftCoin;

import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Voucher implements CommandExecutor, TabCompleter {

    SendSystem ss = new SendSystem();
    Voucher_Create voucher_create = new Voucher_Create();
    Voucher_Get voucher_get = new Voucher_Get();
    Voucher_Use voucher_use = new Voucher_Use();
    Voucher_Help voucher_help = new Voucher_Help();
    Voucher_Gift voucher_gift = new Voucher_Gift();


    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission(ECmd.VOUCHER.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.VOUCHER_HELP.getCmd());
            return true;
        }

        if (args[0].equalsIgnoreCase(ECmd.VOUCHER_ADD.getLastarg())){
            if (!sr.hasPermission(ECmd.VOUCHER_ADD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voucher_create.add(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.VOUCHER_GET.getLastarg())) {
            if (!sr.hasPermission(ECmd.VOUCHER_GET.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voucher_get.get(sr, args);
        } else if(args[0].equalsIgnoreCase(ECmd.VOUCHER_USE.getLastarg())){
            if (!sr.hasPermission(ECmd.VOUCHER_USE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voucher_use.use(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.VOUCHER_HELP.getLastarg())){
            if (!sr.hasPermission(ECmd.VOUCHER_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voucher_help.help(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.VOUCHER_GIFT.getLastarg())){
            if (!sr.hasPermission(ECmd.VOUCHER_GIFT.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voucher_gift.gift(sr, args);
        }


        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        if (agrs.length == 1) {
            if (sr.hasPermission(ECmd.VOUCHER_GIFT.getPerm())) {
                cmd.add(ECmd.VOUCHER_GIFT.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOUCHER_GET.getPerm())) {
                cmd.add(ECmd.VOUCHER_GET.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOUCHER_HELP.getPerm())) {
                cmd.add(ECmd.VOUCHER_HELP.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOUCHER_ADD.getPerm())) {
                cmd.add(ECmd.VOUCHER_ADD.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOUCHER_USE.getPerm())) {
                cmd.add(ECmd.VOUCHER_USE.getLastarg());
            }
            if (agrs[0] == null) {
                tab = cmd;
                return tab;
            }
            for (int i = 0; i < cmd.size(); i++){
                if (cmd.get(i).contains(agrs[0])){
                    tab.add(cmd.get(i));
                }
            }
        }
        return tab;
    }
}
