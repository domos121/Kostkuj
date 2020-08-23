package me.domos.kostkuj.bukkit.listeners.cmds.domos;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.ActionBarMessage;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.EPermission;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Domos implements CommandExecutor, TabCompleter {


   SendSystem ss = new SendSystem();
    private Main plugin = Main.getPlugin(Main.class);


    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if(!sr.getName().equalsIgnoreCase("domos121")){
            return false;
        }

        new ActionBarMessage().sendMsg(ChatColor.of("#32bf40") +  "Ahoj", (Player) sr);



        ////// NECHAT ////////
        /*SerializeDeserializeItem serializeDeserializeItem = new SerializeDeserializeItem();
        List<ItemStack> itemStacks = serializeDeserializeItem.deserializeItem(new MPlayerDeath().getDropItems(Integer.parseInt(args[0])));

        if (itemStacks.isEmpty()){
            Bukkit.getServer().broadcastMessage("V tomto id nic není");
            return false;
        }

        final Inventory i = plugin.getServer().createInventory(null, 54, "DeathItemDrops id: " + args[0]);


        for (ItemStack it : itemStacks){
            i.addItem(it);
        }

        Bukkit.getServer().getPlayer(sr.getName()).openInventory(i);

        //EPermission.executeCommand(sr, cmd, s, args);*/

        return true;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        return EPermission.tabCompletor(sr, command, s, agrs);
    }
}
