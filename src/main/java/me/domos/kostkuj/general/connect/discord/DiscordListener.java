package me.domos.kostkuj.general.connect.discord;

import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.general.connect.discord.commands.DiscordCommandRouter;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class DiscordListener extends ListenerAdapter {

    private DiscordCommandRouter dct = new DiscordCommandRouter();
    private ConfigManager fm = ConfigManager.getInstance();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        //  SKONTROLUJE SPRÁVNEJ CHANNEL;
        if (!event.getChannel().getName().equalsIgnoreCase(DiscordConnect.channel))return;
        //  POKUD TO JE BOT/FAKE STORNUJE;
        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;
        // NAČTE PROMĚNNÉ;
        UUID uuid;
        Member member = event.getMember();
        String roleFromDiscord = member.getRoles().get(0).getName();
        EKostkujRole role = EKostkujRole.getRoleFromName(roleFromDiscord);
        String[] args = event.getMessage().getContentRaw().split("");

        //  KONTROLA ZDA SE JEDNA O OVĚŘOVACÍ PŘÍKAZ;
        if (event.getMessage().getContentRaw().equalsIgnoreCase("/auth")){
            DiscordAuth.discordIdList.add(event.getAuthor().getId());
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth uid:" + event.getAuthor().getId(), event.getAuthor());
            return;
        }
        // KONTROLA ZDA JE ÚČET OVĚŘEN ZE HRY;
        try {
            uuid = UUID.fromString(fm.getDiscordAuth().getString("DiscordAuth." + event.getAuthor().getId()));
        } catch (NullPointerException e){
            DiscordConnect.sendPrivateMsg("Tvůj účet není aktivován pro odesílání zpráv z discordu. Pro aktivaci se drž pokynů.", event.getAuthor());
            DiscordAuth.discordIdList.add(event.getAuthor().getId());
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth uid:" + event.getAuthor().getId(), event.getAuthor());
            return;
        }

        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);

        //  KONTROLA ZDA SE JEDNÁ O PŘÍKAZ.
        if (args[0].equalsIgnoreCase("/")) {
            dct.CommandRoute(event.getMessage().getContentRaw().split(" "), event, role);
            return;
        }

        if (member.getRoles().get(0) == null) return;

        Bukkit.getServer().broadcastMessage("<[§dDC§7§f] " + role.getPrefix() + op.getName() + "§f> " + role.getChatcolor() + event.getMessage().getContentRaw().replace("&",  "§"));
    }

}
