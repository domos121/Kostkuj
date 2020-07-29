package me.domos.kostkuj.bukkit.listeners.cmds.domos;

import me.domos.kostkuj.bukkit.player.event.EventPlayerJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Domos implements CommandExecutor, TabCompleter {


   EventPlayerJoin epj = new EventPlayerJoin();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if(!sr.getName().equalsIgnoreCase("domos121")){
            return true;
        }

       // epj.playerIpEdit(Bukkit.getPlayer(sr.getName()));

        /*JSONArray object = new JSONArray();
        JSONObject a = new JSONObject();
        a.put("text", "ahoj");
        a.put("color", "red");
        JSONObject b = new JSONObject();
        b.put("text", " jak");
        b.put("color", "white");
        JSONObject c = new JSONObject();
        c.put("text", " se");

        object.add(a);
        object.add(b);
        object.add(c);

        sr.sendMessage(object.toJSONString());
        new JsonBroadCast().jsonBcKostkuj(object.toJSONString());

        /*InventoryMenu inventoryMenu = new InventoryMenu((Player)sr, "§ctest",54);
        inventoryMenu.inventoryBuilder().setPaginator(Integer.parseInt(args[0]));
        inventoryMenu.openInventory();*/
        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        return tab;
    }
}
