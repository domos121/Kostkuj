package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class Kostkuj_Pozemek {

    SendSystem ss = new SendSystem();

    private void setPozemek(String pozemek, String user, String owner){
        new BukkitRunnable(){
            int i = 0;  public void run() {

                if (i == 0) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "/expand vert");
                } else if (i == 1){
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg define " + pozemek + " " + owner);
                } else if (i == 2){
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg setparent " + pozemek + " spawnii");
                } else if (i == 3){
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg setpriority " + pozemek + " 40");
                } else if (i > 3){
                    this.cancel();
                }
                i++;
            }

        }.runTaskTimer(Main.plugin, 20, 20*2);
    }

    public boolean onCommand(CommandSender sr, String[] args) {
        if (args.length == 1){
            ss.info(sr, "Use: /kostkuj pozemek <jmeno hrace>");
            return true;
        }

        String pozemek = "spawn_" + args[1];

        setPozemek(pozemek, sr.getName(), args[1]);

        return false;
    }
}
