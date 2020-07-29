package me.domos.kostkuj.bukkit.listeners.cmds.shop;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.general.connect.mysql.shop.GetItem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class Usecode implements CommandExecutor {

    SendSystem ss = new SendSystem();
    private GetItem gi = new GetItem();
    private ItBuilder ib = ItBuilder.getInstance();
    private ConfigCrates crates = ConfigCrates.getInstance();
    private AddItem ass = new AddItem();

    public boolean onCommand(CommandSender sr, Command command, String s, String[] args) {

        if (!sr.hasPermission(ECmd.USECODE.getPerm())){
            ss.noPerm(sr);
            return false;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.USECODE.getLastarg() + " [code]");
            return false;
        }

        boolean addItem = false;

        ItemStack item = null;

        String key = args[0];

        Player p = Bukkit.getPlayer(sr.getName());

        HashMap<Integer, String> map = gi.getItem(key, (Player) sr);

        if (map.get(1).equalsIgnoreCase("NENALEZEN")) {

            ss.info(sr, "§4Kód §a'" + key + "'§4 neexistuje, nebo už je použitý.");

        }else if(map.get(1).equalsIgnoreCase("ITEM_ERROR")){

            ss.info(sr, "§4Vyskytla se chyba, prosím kontaktuj vedení serveru.");

        } else if (map.get(1).equalsIgnoreCase("BOOK")){

            item = ib.enchBook("ENCHANTED_BOOK", Integer.parseInt(map.get(2)), map.get(3));

            addItem = true;

        } else if (map.get(1).equalsIgnoreCase("ITEM")){

            if (map.get(4) == null){
                item = ib.normitem(map.get(2), Integer.parseInt(map.get(3)));

                addItem = true;
            } else {
                item = ib.enchitem(map.get(2), Integer.parseInt(map.get(3)), map.get(4));

                addItem = true;
            }

        } else if(map.get(1).equalsIgnoreCase("PREMIUM")){
            ConfigManager.CONFIG.reloadConfig();

            String vips = map.get(2);

            if (!map.get(2).equalsIgnoreCase("vip")){
                vips = vips + ",1";
            }

            String[] vip = vips.split(",");

            List<String> list = ConfigManager.CONFIG.getConfig().getStringList("premium." + vip[0]);

            for (int i = 0; list.size() > i; i++){

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), list.get(i).replace("[player]", p.getName()).replace("[time]", Integer.parseInt(vip[1]) * 24 * 60 * 60 + ""));
            }

        } else if (map.get(1).equalsIgnoreCase("KEY")){

            for (int i = 0; crates.getKeys().size() > i; i++) {

                String[] name = crates.getKeys().get(i).split(",");

                if (map.get(2).equalsIgnoreCase(name[0])) {
                    item = ib.key("TRIPWIRE_HOOK", Integer.parseInt(map.get(3)), name[1]);
                    addItem = true;
                }
            }
        }

        if (addItem){
            ass.add(item, p);
        }

        return false;
    }
}
