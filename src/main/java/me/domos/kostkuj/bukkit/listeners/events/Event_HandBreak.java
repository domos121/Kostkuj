package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.player.inventory.InvSklad;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.projekty.MySQLGetProjekt;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigSklad;
import me.domos.kostkuj.general.forumProjects.ProjektSettings;
import me.domos.kostkuj.models.crateModel.CratesOpen;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Event_HandBreak implements Listener {

    SendSystem ss = new SendSystem();
    Time time = new Time();
    MySQLGetProjekt mySQLGetProjekt = new MySQLGetProjekt();


    @EventHandler
    public void onPlayerHandBreak(PlayerInteractEvent e){
        compileChest(e);
        checkOpenCrates(e);
    }

    private void compileChest(PlayerInteractEvent e) {
        if (ProjektSettings.isCompileCommand(e.getPlayer().getName())) {
            if ((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if (e.getClickedBlock().getType() == Material.CHEST) {
                    e.setCancelled(true);
                    Chest chest = (Chest) e.getClickedBlock().getState();
                    String author = mySQLGetProjekt.getForumAuthor(ProjektSettings.getProjectId(e.getPlayer().getName()));
                    ItemStack[] items = chest.getInventory().getStorageContents();
                    List<String> lore = new ArrayList<String>();
                    HashMap<String, Integer> map = new HashMap<>();
                    lore.add("§aProject: §c#" + ProjektSettings.getProjectId(e.getPlayer().getName()) + "");
                    lore.add("§aAutor: §c" + author);
                    lore.add("§aDaroval: §c" + e.getPlayer().getDisplayName());
                    lore.add("§aCas: §c" + time.getTimeFromTimeStamp(Time.getTimeSec(0)));
                    for (int i = 0; i < items.length; i++){
                        if (items[i] != null) {
                            //sitems = sitems + items[i].getType().name() + "(" + items[i].getData().getItemTypeId() + ":" +(byte) items[i].getData().getData() + ")" + "x" + items[i].getAmount() + ", ";
                            ItemMeta meta = items[i].getItemMeta();
                            String itemname = items[i].getType().name() + "(" + items[i].getData().getItemType().toString() + ")";
                            int amount = items[i].getAmount();
                            if (map.containsKey(itemname) && i != 0){
                                int in = map.get(itemname) + amount;
                                map.remove(itemname);
                                map.put(itemname, in);
                            } else {
                                map.put(itemname, amount);
                            }
                            meta.setLore(lore);
                            ItemStack itemhoto = items[i];
                            itemhoto.setItemMeta(meta);
                            chest.getInventory().setItem(i, itemhoto);
                        }
                    }
                    String[] poleitem = new String[map.size()];
                    poleitem = map.toString().replace("{", "").replace("}", "").replace("=", "x").split(",");
                    String html = "<ul>";
                    for (int u = 0; u < poleitem.length; u++){
                        html = html + "<li>" + poleitem[u] + "</li>";
                    }
                    Location lokace = e.getClickedBlock().getLocation();
                    String truhla = "x:" + lokace.getBlockX() + ", y:" + lokace.getBlockY() + ", z:" + lokace.getBlockZ() + ", svět" + lokace.getWorld().getName();
                    html = html + "</ul>";
                    mySQLGetProjekt.sendMessage(ProjektSettings.getProjectId(e.getPlayer().getName()),"<p>Lokace truhly: " + truhla + "</p>" + "<h4><p>Darované items:</p></h4>" + html, e.getPlayer().getName());
                    ProjektSettings.removeCompileCommand(e.getPlayer().getName());
                    ss.info(e.getPlayer(), "Truhla skompilovana");
                    return;
                }
            }
        }
    }

    public void checkOpenCrates(PlayerInteractEvent e){
        ConfigCrates cCrates = ConfigCrates.getInstance();
        ConfigSklad cSklad = new ConfigSklad();
        InvSklad invSklad = new InvSklad();
        CratesOpen cco = new CratesOpen();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CHEST) {
            List<String> chests = cCrates.getChests();
            List<String> sklad = cSklad.getSklads();

            Location l = e.getClickedBlock().getLocation();

            String chestlocation = l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + "," + l.getWorld().getName().trim();

            for (int i = 0; chests.size() > i; i++) {
                if (chestlocation.equalsIgnoreCase(chests.get(i))) {
                    e.setCancelled(true);
                    cco.openCrates(e, chests.get(i));
                    break;
                }
            }

            for (int i = 0; sklad.size() > i; i++) {
                if (chestlocation.equalsIgnoreCase(sklad.get(i))) {
                    e.setCancelled(true);
                    invSklad.openSklad(e.getPlayer());
                    break;
                }
            }
        }
    }
}
