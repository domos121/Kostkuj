package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CratesCreateKey {

    private ConfigCrates cCrates = ConfigCrates.getInstance();
    private AddItem ai = new AddItem();
    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private JsonSendMessage jsm = new JsonSendMessage();
    private ItBuilder ib = ItBuilder.getInstance();
    private SendSystem ss = new SendSystem();

    public void createKey(CommandSender sr, String[] args){

        if (args.length == 1){
            sr.sendMessage("§8====== §7KEYS §8======");
            for (int i = 0; cCrates.getChests().size() > i; i++){
                String jm = cCrates.getMapName().get(cCrates.getChests().get(i));
                jsm.jsonBcKostkuj(sr, cjb.hoverText("§8 - §a" + jm, "", "§a" + jm + ":"));
            }
        } else {
            for (int is = 0; cCrates.getKeys().size() > is; is++){
                if (cCrates.getKeys().get(is).contains("KEY_" + args[1])){
                    int pocet = 1;
                    if (args.length > 2) {
                        if (StringUtils.isNumeric(args[2])) {
                            pocet = Integer.parseInt(args[2]);
                        }
                    }
                    String[] display = cCrates.getKeys().get(is).split(",");
                    ai.add(ib.key("TRIPWIRE_HOOK", pocet, display[1]), Bukkit.getPlayer(sr.getName()));
                    break;
                }
            }
        }
    }

}
