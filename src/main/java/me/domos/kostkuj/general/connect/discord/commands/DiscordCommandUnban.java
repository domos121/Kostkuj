package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonBroadCast;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.mysql.trests.MTrests;
import me.domos.kostkuj.general.connect.mysql.trests.MTrestsIP;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import me.domos.kostkuj.models.banModel.GetTrest;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class DiscordCommandUnban implements IDiscordCommand{

    private MTrestsIP mti = new MTrestsIP();
    private MTrests mt = new MTrests();
    private JsonBroadCast jbc = new JsonBroadCast();
    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private ConfigManager fm = ConfigManager.getInstance();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {

        if (args.length == 0) {
            DiscordConnect.sendInfoMsg("Use:  /unban <id:[idtrestu] | ip:[ipid] | [user] | um:[muted user]>");
            return true;
        }

        int idtrestu = 0;
        int ip = 0;

        GetTrest gt;

        if(args[0].contains("id:")){
            String sidtrestu = args[0].replace("id:", "");
            if(!StringUtils.isNumeric(sidtrestu)){
                DiscordConnect.sendInfoMsg("Id trestu neni cislo!");
                return false;
            }
            idtrestu = Integer.parseInt(sidtrestu);
        } else if (args[0].contains("ip:")){
            String sip = args[0].replace("ip:", "");
            if(!StringUtils.isNumeric(sip)){
                DiscordConnect.sendInfoMsg("IP trestu neni cislo!");
                return false;
            }
            ip = Integer.parseInt(sip);
            gt = new GetTrest(null, ip);
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                DiscordConnect.sendInfoMsg("Uzivatel neni zabanovan!");
                return true;
            }
        } else if (args[0].contains("um:")){
            String sunmute = args[0].replace("um:", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(sunmute);
            gt = new GetTrest(op.getUniqueId().toString());
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                DiscordConnect.sendInfoMsg("Uzivatel neni umlcen!");
                return true;
            }
        } else {
            String uunban = args[0].replace("u:", "").replace("p:", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(uunban);
            int lastIP = mti.getLastIP(op.getUniqueId().toString());
            gt = new GetTrest(op.getUniqueId().toString(), lastIP);
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                DiscordConnect.sendInfoMsg("Uzivatel neni zabanovan!");
                return true;
            }
        }

        if (idtrestu == 0 || !mti.isTrestExist(idtrestu)) {
            DiscordConnect.sendInfoMsg("Nepodařilo se nalézt správný trest.");
            return false;
        }
        String reason = "";

        for (int i = 1; args.length > i; i++){
            reason = reason + args[i] + " ";
        }

        UUID uuid = UUID.fromString(fm.getDiscordAuth().getString("DiscordAuth." + event.getAuthor().getId()));
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);

        String hover = "§7Unban:\n§7ID: §c#" + idtrestu + "\n§7Admin: §c" + op.getName() + "\n§7Duvod: §c" + reason + "";

        mti.unban(idtrestu);

        String json = cjb.vetaClickHoverText("§8[§cKostkuj§8]: §7Trest §c➥#", "light_gray", String.valueOf(idtrestu), "red", hover, "suggest_command", "", " §7byl prominut.", "gray");

        DiscordConnect.sendInfoMsg("Trest " + idtrestu + " byl prominut.");

        jbc.jsonBcKostkuj(json);

        mt.setUnban(idtrestu, op.getUniqueId().toString(), reason);
        return false;
    }
}
