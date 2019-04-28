package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Crates implements CommandExecutor, TabCompleter {

    private SendSystem ss = new SendSystem();
    private CratesReload cmdCrates_reload = new CratesReload();
    private CratesCreateKey cmdCrates_createKey = new CratesCreateKey();
    private ConfigCrates cCrates = ConfigCrates.getInstance();

    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission(ECmd.CRATE.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.CRATE_HELP.getCmd());
            return true;
        }

        if (args[0].equalsIgnoreCase(ECmd.CRATE_HELP.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            new PlayerManager().helpMenu(sr, "crate.");
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_RELOAD.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_RELOAD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdCrates_reload.reload(sr);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_CREATEKEY.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdCrates_createKey.createKey(sr, args);
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        if (args.length == 1){
            if (sr.hasPermission(ECmd.CRATE_HELP.getPerm())){
                cmd.add(ECmd.CRATE_HELP.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_RELOAD.getPerm())){
                cmd.add(ECmd.CRATE_RELOAD.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())){
                cmd.add(ECmd.CRATE_CREATEKEY.getLastarg());
            }
            if (args[0] == null){
                tab = cmd;
                return tab;
            }
            for (int i = 0; i < cmd.size(); i++){
                if (cmd.get(i).contains(args[0])){
                    tab.add(cmd.get(i));
                }
            }
        } else if (args.length == 2) {
            if (!sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())){
                return null;
            }
            if (args[0].equalsIgnoreCase(ECmd.CRATE_CREATEKEY.getLastarg())) {
                cmd.clear();
                tab.clear();
                for (int i = 0; cCrates.getKeys().size() > i; i++){
                    String[] keys = cCrates.getKeys().get(i).split(",");
                    cmd.add(keys[0].replace("KEY_", ""));
                }
                if (args[1] == null) {
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(args[1])) {
                        tab.add(cmd.get(i));
                    }
                }
            }
        }
        return tab;
    }
}
