package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.FileManager;
import by.domos.Kostkuj.connect.discord.DiscordAuth;
import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.server.KeyGenerator;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.player.KPlayer;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_DiscordAuth {

    KeyGenerator keyGenerator = new KeyGenerator();
    SendSystem ss = new SendSystem();
    FileManager fm = FileManager.getInstance();

    public void auth(CommandSender sr, String[] args){
        Player p = Bukkit.getPlayer(sr.getName());
        KPlayer kp = KPlayer.getPlayer(p);

        if (args[1].contains("uid:")){
            String discordId = args[1].replace("uid:", "");
            if (!DiscordAuth.isIdExist(discordId)){
                ss.info(sr, "Tento uživatel nepožádal o autorizaci.");
                return;
            }
            User dcuser = DiscordConnect.jda.getUserById(discordId);
            DiscordAuth.discordIdList.remove(discordId);
            String userAuthKey = keyGenerator.getSerialKey(30);
            kp.setDiscordUserKey(discordId);
            kp.setAuthKey(userAuthKey);
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth key:" + userAuthKey, dcuser);
            ss.info(sr, "Byl vám odeslán autorizační klíč na discord.");
        } else if (args[1].contains("key:")){
            String userAuthKey = args[1].replace("key:", "");
            if (kp.getDiscordUserKey() == null) return;
            if (kp.getAuthKey() == null){
                ss.info(sr, "Autorizaci se nepodařilo dokončit, zkus to znova.");
                return;
            }
            if (!kp.getAuthKey().equals(userAuthKey)){
                ss.info(sr, "Tento klíč nepatří k vašemu účtu");
                return;
            }
            ss.info(sr, "Discord authorizován. Nyní můžeš odesílazt zprávy z Discordu.");
            fm.getDiscordAuth().set("DiscordAuth." + kp.getDiscordUserKey(), p.getUniqueId().toString());
            fm.saveDiscordAuth();
        }
    }
}
