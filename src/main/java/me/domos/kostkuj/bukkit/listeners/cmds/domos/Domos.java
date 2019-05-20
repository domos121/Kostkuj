package me.domos.kostkuj.bukkit.listeners.cmds.domos;

import me.domos.kostkuj.models.inventoryMenuModel.InventoryMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Domos implements CommandExecutor, TabCompleter {



    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if(!sr.getName().equalsIgnoreCase("domos121")){
            return true;
        }
        InventoryMenu inventoryMenu = new InventoryMenu((Player)sr, "§ctest",54);
        inventoryMenu.inventoryBuilder().setPaginator(Integer.parseInt(args[0]));
        inventoryMenu.openInventory();
        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        return tab;
    }
}
