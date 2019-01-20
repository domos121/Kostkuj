package me.domos.Kostkuj.server.listener.events;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.ECfg;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.event.writer.WriteConfig;
import me.domos.Kostkuj.server.time.Time;
import me.domos.Kostkuj.server.trests.GetTrest;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class Event_Chat implements Listener {

    public static List<String> commandList = new ArrayList<>();

    public static boolean commandBlockCheck = false;

    private SendSystem ss = new SendSystem();
    private Time time = new Time();


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("kostkuj.mute.baypass")) {
            GetTrest gt = new GetTrest(e.getPlayer().getUniqueId().toString());
            if (gt.isIsbanned()) {
                ss.info(e.getPlayer(), "Jsi umlcen, datum expirace §c" + time.getTimeFromTimeStamp(gt.getExpirytime()) + "§7.");
                e.setCancelled(true);
            }
        }
        if (WriteConfig.isStart()){
            if (WriteConfig.getAnswer().equals(e.getMessage())) {
                WriteConfig.setWinPlayer(e.getPlayer());
                e.setCancelled(true);
            }
        }
        DiscordConnect.sendMsg(e.getMessage(), e.getPlayer());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if (!e.getPlayer().hasPermission("kostkuj.mute.baypass")) {
            String[] args = e.getMessage().split(" ");
            if (ECfg.isMutedCommand(args[0])) {
                GetTrest gt = new GetTrest(e.getPlayer().getUniqueId().toString());
                if(gt.isIsbanned()) {
                    ss.info(e.getPlayer(), "Jsi umlcen, datum expirace §c" + time.getTimeFromTimeStamp(gt.getExpirytime()) + "§7.");
                    e.setCancelled(true);
                }
            }
        }
        if(!e.getPlayer().hasPermission("kostkuj.vip")){
            String[] args = e.getMessage().split(" ");
            if (args[0].equalsIgnoreCase("/skin")){
                ss.noPerm(e.getPlayer());
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void CommandBlock(ServerCommandEvent e) {
        if (commandBlockCheck) {
            if (e.getSender() instanceof BlockCommandSender) {
                BlockCommandSender sender = (BlockCommandSender) e.getSender();
                String msg =  "/tppos " + sender.getBlock().getX() + " " + sender.getBlock().getY() + " " + sender.getBlock().getZ() + " " + sender.getBlock().getWorld().getName() + " Command: " + e.getCommand();

                if (commandList.isEmpty()){
                    commandList.add(msg);
                } else {
                    if (!commandList.contains(msg)){
                        commandList.add(msg);
                    }
                }
            }
        }
    }
}
