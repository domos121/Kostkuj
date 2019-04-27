package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class Kostkuj_Pozemek {
    private SendSystem ss = new SendSystem();

    public Kostkuj_Pozemek() {
    }

    private void setPozemek(final String pozemek, final String user, final String owner) {
        (new BukkitRunnable() {
            int i = 0;

            public void run() {
                if (this.i == 0) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "/expand vert");
                } else if (this.i == 1) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg define " + pozemek + " " + owner);
                } else if (this.i == 2) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg setparent " + pozemek + " spawnii");
                } else if (this.i == 3) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(user), "rg setpriority " + pozemek + " 40");
                } else if (this.i > 3) {
                    this.cancel();
                }

                ++this.i;
            }
        }).runTaskTimer(Main.plugin, 20L, 40L);
    }

    public boolean onCommand(CommandSender sr, String[] args) {
        if (args.length == 1) {
            this.ss.info(sr, "Use: /kostkuj pozemek <jmeno hrace>");
            return true;
        } else {
            String pozemek = "spawn_" + args[1];
            this.setPozemek(pozemek, sr.getName(), args[1]);
            return false;
        }
    }
}
