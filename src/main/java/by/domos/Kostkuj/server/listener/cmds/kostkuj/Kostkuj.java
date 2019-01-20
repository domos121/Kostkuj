package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.enums.EParticle;
import by.domos.Kostkuj.enums.EParticleShapes;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.chat.menu.menu_buildr;
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
    private menu_buildr mb = new menu_buildr();
    private Kostkuj_Save s = new Kostkuj_Save();
    private Kostkuj_CopyHome copyhome = new Kostkuj_CopyHome();
    private Kostkuj_Pozemek pozemek = new Kostkuj_Pozemek();
    private Kostkuj_TopTime topTime = new Kostkuj_TopTime();
    private Kostkuj_Death pd = new Kostkuj_Death();
    private Kostkuj_Restart restart = new Kostkuj_Restart();
    private Kostkuj_Stop stop = new Kostkuj_Stop();
    private Kostkuj_Particle particle = new Kostkuj_Particle();
    private Kostkuj_DiscordAuth discordAuth = new Kostkuj_DiscordAuth();
    private Kostkuj_CommandBlockList commadBlockList = new Kostkuj_CommandBlockList();
    private Kostkj_Writer kostkjWriter = new Kostkj_Writer();
    private Kostkuj_NyniJeAfk kostkuj_nyniJeAfk = new Kostkuj_NyniJeAfk();

    //suggest_command  run_command open_url

    private String[][] Main = {
            {"Help", "Potrebujes pomoct s prikazama?", "kostkuj.menu", "/cmd", "run_command"},
            {"Pravidla", "Potřebuješ zjistit pravidla?", "kostkuj.menu", "/pravidla", "run_command"},
            {"HLASUJ!", "Odkaz na web s hlasovanim", "kostkuj.menu", "https://czech-craft.eu/vote?id=12614&user=#name#", "open_url"},
            {"Web", "Odkaz na nas web\\n§6www.kostkuj.cz", "kostkuj.menu", "http://www.kostkuj.cz/", "open_url"},
            {"Forum", "Odkaz na nas web\\n§6www.kostkuj.cz/forum", "kostkuj.menu", "http://www.kostkuj.cz/forum", "open_url"},
            {"Shop", "Odkaz na nas web\\n§6www.kostkuj.cz/shop", "kostkuj.menu", "http://www.kostkuj.cz/shop", "open_url"},
    };


    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if(args.length == 0){
            mb.menu(sr, Main, "Main");
            return true;
        }

        if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_RESTART.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_RESTART.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            restart.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_STOP.getLastarg())) {
            if (!sr.hasPermission(ECmd.KOSTKUJ_STOP.getPerm())) {
                ss.noPerm(sr);
                return true;
            }
            stop.onCommand(sr, args);
            return true;
        }else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_SAVE.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_SAVE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            s.saveWorld();
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_COPYHOME.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_COPYHOME.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            copyhome.onCommand(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_POZEMEK.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_POZEMEK.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            pozemek.onCommand(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_TOPTIME.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_TOPTIME.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            topTime.onCommand(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_DEATH.getLastarg())) {
            if (!sr.hasPermission(ECmd.KOSTKUJ_DEATH.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            pd.onCommand(sr, args);
            return true;
        }  else if (args[0].equalsIgnoreCase(ECmd.KOSTKUJ_PARTICLE.getLastarg())) {
            if (!sr.hasPermission(ECmd.KOSTKUJ_PARTICLE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            particle.Particle(args, sr);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_DISCORDAUTH.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_DISCORDAUTH.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            discordAuth.auth(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_COMMADBLOCKLIST.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_COMMADBLOCKLIST.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            commadBlockList.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_WRITER.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_WRITER.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            kostkjWriter.onCommand(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase(ECmd.KOSTKUJ_NYNIJEAFK.getLastarg())){
            if (!sr.hasPermission(ECmd.KOSTKUJ_NYNIJEAFK.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            kostkuj_nyniJeAfk.onCommand(sr, args);
            return true;
        } else {
            ss.info(sr, "Neplatný argument!");
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
            if (sr.hasPermission(ECmd.KOSTKUJ_COPYHOME.getPerm())){
                cmd.add(ECmd.KOSTKUJ_COPYHOME.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_POZEMEK.getPerm())){
                cmd.add(ECmd.KOSTKUJ_POZEMEK.getLastarg());
            }
            if (sr.hasPermission(ECmd.KOSTKUJ_PARTICLE.getPerm())){
                cmd.add(ECmd.KOSTKUJ_PARTICLE.getLastarg());
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
            } else if (agrs[0].equalsIgnoreCase(ECmd.KOSTKUJ_PARTICLE.getLastarg())){
                if (sr.hasPermission(ECmd.KOSTKUJ_PARTICLE_START.getPerm())) {
                    cmd.add(ECmd.KOSTKUJ_PARTICLE_START.getLastarg());
                }
                if (sr.hasPermission(ECmd.KOSTKUJ_PARTICLE_STOP.getPerm())) {
                    cmd.add(ECmd.KOSTKUJ_PARTICLE_STOP.getLastarg());
                }
                if (sr.hasPermission(ECmd.KOSTKUJ_PARTICLE_SHAPE.getPerm())) {
                    cmd.add(ECmd.KOSTKUJ_PARTICLE_SHAPE.getLastarg());
                }
                if (sr.hasPermission(ECmd.KOSTKUJ_PARTICLE_TYP.getPerm())) {
                    cmd.add(ECmd.KOSTKUJ_PARTICLE_TYP.getLastarg());
                }
                if (agrs[1] == null) {
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(agrs[1])) {
                        tab.add(cmd.get(i));
                    }
                }
            }
        } else if(agrs.length == 3){
            if (agrs[1].equalsIgnoreCase(ECmd.KOSTKUJ_PARTICLE_SHAPE.getLastarg())){
                for (int i = 0; EParticleShapes.values().length > i; i++) {
                    if (sr.hasPermission(EParticleShapes.values()[i].getPerm())){
                        cmd.add(EParticleShapes.values()[i].getName());
                    }
                }
                if (agrs[2] == null) {
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(agrs[2])) {
                        tab.add(cmd.get(i));
                    }
                }
            } else if (agrs[1].equalsIgnoreCase(ECmd.KOSTKUJ_PARTICLE_TYP.getLastarg())){
                for (int i = 0; EParticle.values().length > i; i++) {
                    if (sr.hasPermission(EParticle.values()[i].getPerm())){
                        cmd.add(EParticle.values()[i].getName());
                    }
                }
                if (agrs[2] == null) {
                    tab = cmd;
                    return tab;
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(agrs[2])) {
                        tab.add(cmd.get(i));
                    }
                }
            }
        }
        return tab;
    }
}
