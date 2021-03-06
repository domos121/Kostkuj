package me.domos.kostkuj.bukkit.listeners.cmds.stavba;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMDStavba implements CommandExecutor, TabCompleter {


    private SendSystem ss = new SendSystem();

    private CMDStavba_Create cmdStavba_create = new CMDStavba_Create();
    private CMDStavba_Get cmdStavba_get = new CMDStavba_Get();
    private CMDStavba_Add cmdStavba_add = new CMDStavba_Add();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission(ECmd.STAVBA.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.STAVBA_HELP.getCmd());
            return true;
        }



        if (args[0].equalsIgnoreCase("help")){
            if (!sr.hasPermission(ECmd.STAVBA_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            PlayerManager.helpMenu(sr, "stavba.");
            return true;
        } else if (args[0].equalsIgnoreCase("create")){
            if (!sr.hasPermission(ECmd.STAVBA_CREATE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdStavba_create.createVote(sr, args);
        } else if (args[0].equalsIgnoreCase("add")) {
            if (!sr.hasPermission(ECmd.STAVBA_ADD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdStavba_add.add(sr, args);
        } else if (args[0].equalsIgnoreCase("start")) {
            if (!sr.hasPermission(ECmd.STAVBA_START.getPerm())){
                ss.noPerm(sr);
                return true;
            }
        } else if (args[0].equalsIgnoreCase("close")) {
            if (!sr.hasPermission(ECmd.STAVBA_CLOSE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
        } else if (args[0].equalsIgnoreCase("voteModel")){
            if (!sr.hasPermission(ECmd.STAVBA_VOTE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
        } else if (args[0].equalsIgnoreCase("get")){
            if (!sr.hasPermission(ECmd.STAVBA_GET.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdStavba_get.get(sr, args);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] args) {
        List<String> cmd = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        cmd.add("help");
        cmd.add("create");
        cmd.add("add");
        cmd.add("start");
        cmd.add("close");
        cmd.add("voteModel");
        cmd.add("get");
        if (args[0] == null){
            tab = cmd;
            return tab;
        }
        for (int i = 0; i < cmd.size(); i++){
            if (cmd.get(i).contains(args[0])){
                tab.add(cmd.get(i));
            }
        }
        return tab;
    }
}
