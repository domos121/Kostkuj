package me.domos.Kostkuj.server.player.event;

import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLis;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLset;
import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.ECfg;
import me.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import me.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import me.domos.Kostkuj.server.player.PlayerManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockBreak {

    // String [0] [1]
    // String [radek] [sloupec]

    private String[][] xrayblock = {{"DIAMOND_ORE", "aqua", "Diamanty", "§b"}, {"EMERALD_ORE", "green", "Emeraldy", "§a"}};


    MySQLis mysqlis = new MySQLis();
    MySQLset mysqlset = new MySQLset();
    JsonBroadCast jbc = new JsonBroadCast();

    public void checkPlayerBreak(BlockBreakEvent b) {
        Boolean isPolozeno = mysqlis.isPlacedBlock(b.getBlock());
        boolean itemos = ECfg.isAcceptItemForAntiXray(b.getBlock());
        //b.getPlayer().sendMessage("Is array? " + itemos);
        if (!isPolozeno) {
            if (itemos) {
                mysqlset.setBreakBlock(b);
            }
            xraySender(b.getPlayer(), b.getBlock());
        }
    }

    private void xraySender(Player p, Block b) {
        PlayerManager pm = new PlayerManager();
        String item = b.getType().name().toString();
        String namePlayer = p.getDisplayName().toString();
        for (int i = 0; i < xrayblock.length; i++) {
            if (item.equals(xrayblock[i][0])) {
                if (!pm.isPlayerGamemode(p)) {
                    CustomJsonBuilder cjb = new CustomJsonBuilder();
                    Location l = p.getLocation();
                    Location block = new Location(l.getWorld(), l.getX(), l.getY() , l.getZ());
                    int lightlevel = block.getBlock().getLightLevel();
                    String svetlost;
                    if (lightlevel < 4){
                        svetlost = "§c" + lightlevel;
                    } else if (lightlevel >= 5 && lightlevel < 9){
                        svetlost = "§6" + lightlevel;
                    } else {
                        svetlost = "§a" + lightlevel;
                    }
                    MySQLget mySQLget = new MySQLget();
                    int pocet = mySQLget.getCount("SELECT SUM(pocet) FROM game_kostkuj_break WHERE item = '" + xrayblock[i][0] + "' AND uuid = '" + p.getUniqueId().toString().trim() + "' AND time >= (now() - INTERVAL 2 DAY)");
                    String json = cjb.vetaClickHoverText("➥Hrac ", xrayblock[i][1], "§r" + p.getDisplayName(),"",p.getDisplayName() + "§7:\nLokace: §cx:" + l.getBlockX() + ", y:" + l.getBlockY() + ", z:" + l.getBlockZ() + "\n§7Svet: §c" + l.getWorld().getName().trim() + "\n§7Pocet(-48h): §c" + pocet,"suggest_command","/tppos " + l.getBlockX() + " "+ l.getBlockY() + " " + l.getBlockZ() + " " + l.getWorld().getName().trim(), " nasel " + xrayblock[i][2] + ". §7Svetlost: " + svetlost + xrayblock[i][3] + ".", xrayblock[i][1]);
                    DiscordConnect.sendMsg("Hrac **" + p.getName() + "** našel " + xrayblock[i][2] + ". Světlost **[" + lightlevel + "]**. /tppos " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
                    jbc.jsonBcKostkuj(json, "kostkujplg.anticheat.use");
                }
            }
        }
    }
}
