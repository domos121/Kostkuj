package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Kostkuj_Save {

    SendSystem ss = new SendSystem();

    public void saveWorld(){

        List<World> world = Bukkit.getServer().getWorlds();

        ss.broadCast("Ukladam mapy.");

        for (int i = 0; i < world.size(); i++){
            Bukkit.getServer().getWorld(world.get(i).getName()).save();
            Bukkit.getConsoleSender().sendMessage("Ukladam " + world.get(i).getName());
        }
        List<Player> onlinePlayers = new ArrayList<Player>();
        for (Player c : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(c);
            c.saveData();
            Bukkit.getConsoleSender().sendMessage("Ulozil jsem " + c.getName());
        }

        ss.broadCast("Mapy ulozeny.");
    }

}
