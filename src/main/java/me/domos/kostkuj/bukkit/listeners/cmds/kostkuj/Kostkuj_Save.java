package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Kostkuj_Save {

    private SendSystem ss = new SendSystem();

    public void saveWorld(){
             this.ss.broadCast("Ukládám mapy.");

            for (World world : Bukkit.getServer().getWorlds()){
                Bukkit.getServer().getWorld(world.getName()).save();
                Bukkit.getConsoleSender().sendMessage("Ukládám " + world.getName());
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.saveData();
                Bukkit.getConsoleSender().sendMessage("Uložil jsem " + p.getName());
            }

            this.ss.broadCast("Mapy uloženy.");
    }

}
