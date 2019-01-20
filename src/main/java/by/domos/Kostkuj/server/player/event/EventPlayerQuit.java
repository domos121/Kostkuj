package by.domos.Kostkuj.server.player.event;

import by.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.server.player.KPlayer;
import by.domos.Kostkuj.server.player.ipmanager.IpHasher;
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
            DiscordConnect.sendMsg("```css\n" + p.getName() + " left the game!\n```");
        }
        mti.updateIPLogout(uuid, hash);
    }
}
