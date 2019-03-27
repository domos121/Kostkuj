package me.domos.Kostkuj.server.player.event;

import me.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.ECmd;
import me.domos.Kostkuj.server.player.KPlayer;
import me.domos.Kostkuj.server.player.ipmanager.IpHasher;
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
