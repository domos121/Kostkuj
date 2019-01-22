package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.FileManager;
import me.domos.Kostkuj.connect.discord.DiscordAuth;
import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.server.KeyGenerator;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.player.KPlayer;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_DiscordAuth {

    private KeyGenerator keyGenerator = new KeyGenerator();
    private SendSystem ss = new SendSystem();
    private FileManager fm = FileManager.getInstance();

    public void auth(CommandSender sr, String[] args) {
        Player p = Bukkit.getPlayer(sr.getName());
        KPlayer kp = KPlayer.getPlayer(p);
        String userAuthKey;
        if (args[1].contains("uid:")) {
            userAuthKey = args[1].replace("uid:", "");
            if (!DiscordAuth.isIdExist(userAuthKey)) {
                this.ss.info(sr, "Tento uživatel nepožádal o autorizaci.");
                return;
            }

            User dcuser = DiscordConnect.jda.getUserById(userAuthKey);
            DiscordAuth.discordIdList.remove(userAuthKey);
            userAuthKey = this.keyGenerator.getSerialKey(30);
            kp.setDiscordUserKey(userAuthKey);
            kp.setAuthKey(userAuthKey);
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth key:" + userAuthKey, dcuser);
            this.ss.info(sr, "Byl vám odeslán autorizační klíč na discord.");
        } else if (args[1].contains("key:")) {
            userAuthKey = args[1].replace("key:", "");
            if (kp.getDiscordUserKey() == null) {
                return;
            }

            if (kp.getAuthKey() == null) {
                this.ss.info(sr, "Autorizaci se nepodařilo dokončit, zkus to znova.");
                return;
            }

            if (!kp.getAuthKey().equals(userAuthKey)) {
                this.ss.info(sr, "Tento klíč nepatří k vašemu účtu");
                return;
            }

            this.ss.info(sr, "Discord authorizován. Nyní můžeš odesílazt zprávy z Discordu.");
            this.fm.getDiscordAuth().set("DiscordAuth." + kp.getDiscordUserKey(), p.getUniqueId().toString());
            this.fm.saveDiscordAuth();
        }

    }
}
