package by.domos.Kostkuj.server.listener.cmds.projekty;

import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMDProject implements CommandExecutor {

    SendSystem ss = new SendSystem();
    Projekt_SetItem projekt_setItem = new Projekt_SetItem();
    Projekt_Get projekt_get = new Projekt_Get();
    Projekt_Accept projekt_accept = new Projekt_Accept();
    Projekt_Complete projekt_complete = new Projekt_Complete();
    Projekt_Cslose projekt_cslose = new Projekt_Cslose();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.project")){
           ss.noPerm(sr);
           return true;
        }

        if (args.length == 0){
            ss.info(sr, "Use: /projekt help");
            return true;
        }

        if (args[0].equalsIgnoreCase("setitem")){
            if (!sr.hasPermission("kostkuj.project.setitem")){
                ss.noPerm(sr);
                return true;
            }
            projekt_setItem.setItem(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase("get")){
            if (!sr.hasPermission("kostkuj.project.get")){
                ss.noPerm(sr);
                return true;
            }
            projekt_get.getProjekts(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase("accept")){
            if (!sr.hasPermission("kostkuj.project.accept")){
                ss.noPerm(sr);
                return true;
            }
            projekt_accept.accept(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase("complete")){
            if (!sr.hasPermission("kostkuj.project.complete")){
                ss.noPerm(sr);
                return true;
            }
            projekt_complete.complete(sr, args);
            return true;
        } else if(args[0].equalsIgnoreCase("close")){
            if (!sr.hasPermission("kostkuj.project.close")){
                ss.noPerm(sr);
                return true;
            }
            projekt_cslose.complete(sr, args);
            return true;
        }else if (args[0].equalsIgnoreCase("help")){
            sr.sendMessage("§8===== §7Project: §cHELP §8=====");
            sr.sendMessage("§a/project help §8- §cPomoc s projekty.");
            sr.sendMessage("§a/project get §8- §cVrátí projekty.");
            sr.sendMessage("§a/project get <user>§8- §cVrátí projekty hráče.");
            sr.sendMessage("§a/project accept <id> §8- §cSchválí projekt.");
            sr.sendMessage("§a/project setitem <id> §8- §cSkompiluje itemy hráče.");
            sr.sendMessage("§a/project complete <id> §8- §cOznačí projekt jako dokončen.");
            sr.sendMessage("§a/project close <id> §8- §cOznačí projekt jako zrušen.");
        }

        return false;
    }
}
