package me.domos.kostkuj.models.crateModel;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CratesOpen {


    private ConfigCrates cCrates = ConfigCrates.getInstance();
    private Main plugin = Main.getPlugin(Main.class);
    private ItBuilder ib = ItBuilder.getInstance();
    private SendSystem ss = new SendSystem();

    public void openCrates(PlayerInteractEvent e, String chest){
        String[] name = cCrates.getMapKeys().get(chest).split(",");
        String needitem = ib.key("TRIPWIRE_HOOK", e.getPlayer().getInventory().getItemInMainHand().getAmount(), name[1]).toString();
        String playeritem = e.getPlayer().getInventory().getItemInMainHand().toString();
        if (needitem.equalsIgnoreCase(playeritem)){
            int pocet = e.getPlayer().getInventory().getItemInMainHand().getAmount();
            e.getPlayer().getInventory().getItemInMainHand().setAmount(pocet - 1);
            mat(chest, ib.koloItems(cCrates.getMapItems().get(chest)), e);
        } else {
            ss.info(e.getPlayer(), "§7K této truhle potřebuješ §a'" + name[1] + "§a'§7!");
        }

    }

    public void mat(final String chest, final List<ItemStack> items, final PlayerInteractEvent e){
        final AddItem ai = new AddItem();
        final Random r = new Random();
        ItBuilder ib = ItBuilder.getInstance();
        final Inventory i = plugin.getServer().createInventory(null, 54, cCrates.getMapName().get(chest) + "_GiftChest");
        e.getPlayer().openInventory(i);
        final HashMap<Integer, Integer> slots = new HashMap<Integer, Integer>();
        slots.put(0, 6);
        slots.put(1, 16);
        slots.put(2, 25);
        slots.put(3, 34);
        slots.put(4, 42);
        slots.put(5, 50);
        slots.put(6, 49);
        slots.put(7, 48);
        slots.put(8, 38);
        slots.put(9, 28);
        slots.put(10, 19);
        slots.put(11, 10);
        slots.put(12, 2);
        slots.put(13, 4);
        final List<Integer> cisla = getCisla();
        final List<ItemStack> glass = new ArrayList<ItemStack>();
        glass.add(ib.item(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1));
        glass.add(ib.item(Material.YELLOW_STAINED_GLASS_PANE, 1));
        glass.add(ib.item(Material.BLUE_STAINED_GLASS_PANE, 1));
        glass.add(ib.item(Material.RED_STAINED_GLASS_PANE, 1));
        glass.add(ib.item(Material.PINK_STAINED_GLASS_PANE, 1));
        new BukkitRunnable(){
            int is = 0;
            int slot = -1;
            public void run() {
                for (int k = 0; cisla.size() > k; k++){
                    i.setItem(cisla.get(k), glass.get(r.nextInt(glass.size())));
                }
                if (slots.size() > slot + 1){
                    slot++;
                } else {
                    slot = 0;
                }

                int ran = r.nextInt(items.size());
                ItemStack winitem = items.get(ran);
                i.setItem(slots.get(slot), winitem);
                if (is > 68){
                    i.setItem(22, winitem);
                    ai.add(winitem, e.getPlayer());
                    cancel();
                }
                is++;
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

    private List<Integer> getCisla(){
        final List<Integer> cisla = new ArrayList<Integer>();
        cisla.add(0);
        cisla.add(1);
        cisla.add(3);
        cisla.add(5);
        cisla.add(7);
        cisla.add(8);
        cisla.add(9);
        cisla.add(11);
        cisla.add(12);
        cisla.add(13);
        cisla.add(14);
        cisla.add(15);
        cisla.add(17);
        cisla.add(18);
        cisla.add(20);
        cisla.add(21);
        cisla.add(22);
        cisla.add(23);
        cisla.add(24);
        cisla.add(26);
        cisla.add(27);
        cisla.add(29);
        cisla.add(30);
        cisla.add(31);
        cisla.add(32);
        cisla.add(33);
        cisla.add(35);
        cisla.add(36);
        cisla.add(37);
        cisla.add(39);
        cisla.add(40);
        cisla.add(41);
        cisla.add(43);
        cisla.add(44);
        cisla.add(45);
        cisla.add(46);
        cisla.add(47);
        cisla.add(51);
        cisla.add(52);
        cisla.add(53);
        return cisla;
    }

}
