package by.domos.Kostkuj.server.player.event;

import by.domos.Kostkuj.connect.MySQL.player.Bans.MySQLJsonBuilderForPlayerJoin;
import by.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLis;
import by.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLset;
import by.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.enums.EParticleShapes;
import by.domos.Kostkuj.server.player.KPlayer;
import by.domos.Kostkuj.server.player.ipmanager.IpHasher;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class EventPlayerJoin {

    MySQLis mysqlis = new MySQLis();
    MySQLset mysqlset = new MySQLset();
    MySQLJsonBuilderForPlayerJoin mjb = new MySQLJsonBuilderForPlayerJoin();
    MTrestsIP mti = new MTrestsIP();
    IpHasher iph = IpHasher.getInstance();


    public void playerIpEdit(Player p){
        KPlayer kplayer = new KPlayer(p, EParticleShapes.STOP, Particle.FLAME);
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
            DiscordConnect.sendMsg("```css\n" + p.getName() + " joined the game!\n```");
        }
        p.setGameMode(GameMode.SURVIVAL);
    }
}
