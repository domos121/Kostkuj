package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.menu.ChatMenuBuilder;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Kostkuj implements CommandExecutor, TabCompleter {

    private SendSystem ss = new SendSystem();
    private ChatMenuBuilder mb = new ChatMenuBuilder();
    private Kostkuj_Save s = new Kostkuj_Save();
    private Kostkuj_Pozemek pozemek = new Kostkuj_Pozemek();
    private Kostkuj_TopTime topTime = new Kostkuj_TopTime();
    private Kostkuj_Death pd = new Kostkuj_Death();
    private Kostkuj_Restart restart = new Kostkuj_Restart();
    private Kostkuj_Stop stop = new Kostkuj_Stop();
    private Kostkuj_DiscordAuth discordAuth = new Kostkuj_DiscordAuth();
    private Kostkuj_CommandBlockList commadBlockList = new Kostkuj_CommandBlockList();
    private Kostkj_Writer kostkjWriter = new Kostkj_Writer();
    private Kostkuj_NyniJeAfk kostkuj_nyniJeAfk = new Kostkuj_NyniJeAfk();

    //suggest_command  run_command open_url

    private String[][] Main = {
            {"Help", "Potrebujes pomoct s prikazama?", "kostkuj.menu", "/cmd", "run_command"},
            {"Pravidla", "Potřebuješ zjistit pravidla?", "kostkuj.menu", "/pravidla", "run_command"},
            {"HLASUJ!", "Odkaz na web s hlasováním", "kostkuj.menu", "https://czech-craft.eu/server/kostkuj/vote/?user=#name#", "open_url"},
            {"Web", "Odkaz na nas web\\n§6www.kostkuj.cz", "kostkuj.menu", "http://www.kostkuj.cz/", "open_url"},
            {"Forum", "Odkaz na nas web\\n§6www.kostkuj.cz/forum", "kostkuj.menu", "http://www.kostkuj.cz/forum", "open_url"},
            {"Shop", "Odkaz na nas web\\n§6www.kostkuj.cz/shop", "kostkuj.menu", "http://www.kostkuj.cz/shop", "open_url"},
    };


    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if(args.length == 0){
            if (!(sr instanceof Player)) {
                ss.noPlayer(sr);
                return true;
            }
            this.mb.menu(sr, Main, "Main");
            return true;
        }

        if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_RESTART.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_RESTART.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            this.restart.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_STOP.getLastarg())) {
            if (!sr.hasPermission(ECmd.KOSTKUJ_STOP.getPerm())) {
                this.ss.noPerm(sr);
                return true;
            }
            this.stop.onCommand(sr, args);
            return true;
        }else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_SAVE.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_SAVE.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.s.saveWorld();
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_POZEMEK.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_POZEMEK.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.pozemek.onCommand(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_TOPTIME.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_TOPTIME.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.topTime.onCommand(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_DEATH.getLastarg())) {
            if (!sr.hasPermission(ECmd.KOSTKUJ_DEATH.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.pd.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_DISCORDAUTH.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_DISCORDAUTH.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.discordAuth.auth(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_COMMADBLOCKLIST.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_COMMADBLOCKLIST.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.commadBlockList.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_WRITER.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_WRITER.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.kostkjWriter.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_NYNIJEAFK.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_NYNIJEAFK.getPerm())){
                this.ss.noPerm(sr);
                return true;
            }
            this.kostkuj_nyniJeAfk.onCommand(sr, args);
            return true;
        } else {
            this.ss.info(sr, "Neplatný argument!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        if (agrs.length == 1){
            if (sr.hasPermission(ECmd.KOSTKUJ_SAVE.getPerm())){
                cmd.add(ECmd.KOSTKUJ_SAVE.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_STOP.getPerm())){
                cmd.add(ECmd.KOSTKUJ_STOP.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_TOPTIME.getPerm())){
                cmd.add(ECmd.KOSTKUJ_TOPTIME.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_DEATH.getPerm())){
                cmd.add(ECmd.KOSTKUJ_DEATH.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_RESTART.getPerm())){
                cmd.add(ECmd.KOSTKUJ_RESTART.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_POZEMEK.getPerm())){
                cmd.add(ECmd.KOSTKUJ_POZEMEK.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_COMMADBLOCKLIST.getPerm())){
                cmd.add(ECmd.KOSTKUJ_COMMADBLOCKLIST.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_WRITER.getPerm())){
                cmd.add(ECmd.KOSTKUJ_WRITER.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_NYNIJEAFK.getPerm())){
                cmd.add(ECmd.KOSTKUJ_NYNIJEAFK.getLastarg());
            }
            if (agrs[0] == null){
                tab = cmd;
                return tab;
            }
            for (int i = 0; i < cmd.size(); i++){
                if (cmd.get(i).contains(agrs[0])){
                    tab.add(cmd.get(i));
                }
            }
        } else if (agrs.length == 2){
            if (agrs[0].equalsIgnoreCase(ECmd.KOSTKUJ_RESTART.getLastarg())) {
                cmd.clear();
                tab.clear();
                cmd.add("stop");
                cmd.add("30");
                cmd.add("60");
                cmd.add("100");
                cmd.add("120");
                cmd.add("180");
                cmd.add("240");
                cmd.add("300");
                if (agrs[1] == null){
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++){
                    if (cmd.get(i).contains(agrs[1])){
                        tab.add(cmd.get(i));
                    }
                }
            } else if (agrs[0].equalsIgnoreCase(ECmd.KOSTKUJ_POZEMEK.getLastarg())) {
                cmd.clear();
                tab.clear();
                for (Player p : Bukkit.getOnlinePlayers()){
                    cmd.add(p.getName());
                }
                if (agrs[1] == null){
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++){
                    if (cmd.get(i).contains(agrs[1])){
                        tab.add(cmd.get(i));
                    }
                }
            }
        }
        return tab;
    }
}
