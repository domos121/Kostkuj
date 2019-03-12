package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.server.chat.ActionBarMessage;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.listener.events.Event_Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Kostkuj_CommandBlockList {

    private ActionBarMessage actionBarMessage = new ActionBarMessage();
    private SendSystem ss = new SendSystem();

    public boolean onCommand(CommandSender sr, String[] args) {

        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }

            Event_Chat.commandBlockCheck = true;
            ss.info(sr, "Hledám CommandBlocky.");
            commandTimer(15*20, (Player) sr);
        return false;
    }

    // setTime = 100%
    // time = x%

    public void commandTimer(final int setTime, final Player player){
        new BukkitRunnable(){
            int time = setTime;
            public void run() {
                time--;
                    actionBarMessage.sendMsg(("§6§lProgress: §a" + (int) (100-(time*100/setTime))) + "%", player);
                if (time <= 0){
                    Event_Chat.commandBlockCheck = false;
                    stopSendMsg(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

    private void stopSendMsg(Player player){
        ss.info(player, "Seznam §8(§a" + Event_Chat.commandList.size() + "§8) §7commandBlocků byl odeslán na discord.");
        if (Event_Chat.commandList.size() != 0) {
            DiscordConnect.sendMsg("CommandBlockActivList: ");
            for (String chat : Event_Chat.commandList) {
                DiscordConnect.sendMsg("```json\n" + chat + "\n```");
            }
        }
        Event_Chat.commandList.clear();
    }
}
