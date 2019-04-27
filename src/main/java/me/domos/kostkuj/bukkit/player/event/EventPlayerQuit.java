package me.domos.kostkuj.bukkit.player.event;

import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.KPlayer;
import me.domos.kostkuj.bukkit.player.ipmanager.IpHasher;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.mysql.trests.MTrestsIP;
import org.bukkit.entity.Player;

public class EventPlayerQuit {

    MTrestsIP mti = new MTrestsIP();
    IpHasher iph = IpHasher.getInstance();

    public void playerIpEdit(Player p){
        KPlayer kplayer = KPlayer.getPlayer(p);
        KPlayer.unregisterPlayer(kplayer);

        String uuid = p.getUniqueId().toString();
        // ip = ipadresa pripojeneho hrace
        String ip = p.getAddress().getAddress().toString().replace("/", "");
        // hashovana ip hrace
        String hash = iph.hashIp(ip);
        // set time ip logout
        if(!p.hasPermission(ECmd.KOSTKUJ_JOIN_SLIENT.getPerm())) {
            DiscordConnect.sendYellowMsg(p.getName() + " left the game!");
        }
        mti.updateIPLogout(uuid, hash);
    }
}
