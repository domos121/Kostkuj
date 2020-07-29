package me.domos.kostkuj.bukkit.player.event;

import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.KPlayer;
import me.domos.kostkuj.bukkit.player.ipmanager.IpHasher;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.mysql.player.bans.MySQLJsonBuilderForPlayerJoin;
import me.domos.kostkuj.general.connect.mysql.trests.MTrestsIP;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class EventPlayerJoin {

    MySQLJsonBuilderForPlayerJoin mjb = new MySQLJsonBuilderForPlayerJoin();
    MTrestsIP mti = new MTrestsIP();
    IpHasher iph = IpHasher.getInstance();


    public void playerIpEdit(Player p){
        KPlayer kplayer = new KPlayer(p);
        KPlayer.registerPlayer(kplayer);

        // uuid hrace
        String uuid = p.getUniqueId().toString();
        // ip = ipadresa pripojeneho hrace
        String ip = p.getAddress().getAddress().toString().replace("/", "");
        // hashovana ip hrace
        String hash = iph.hashIp(ip);
        // pokud ip existuje
        if(mti.isIpExist(hash)){
            // pokud info o ip existuji
            if (mti.isIpInfoExist(uuid, hash)){
                mti.updateIPLogin(uuid, hash);
                // pokud ne
            } else {
                mti.setIPFirstLogin(uuid, hash);
            }
            // v opacnem pripade
        } else {
            // set hash and ip
            mti.setFirstHas(uuid, hash);
        }
        if(!p.hasPermission(ECmd.KOSTKUJ_JOIN_SLIENT.getPerm())) {
            mjb.setInfoPlayerConnection(uuid);
            DiscordConnect.sendYellowMsg(p.getName() + " joined the game!");
        }
        p.setGameMode(GameMode.SURVIVAL);
    }
}
