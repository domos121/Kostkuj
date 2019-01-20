package by.domos.Kostkuj.connect.discord;

import by.domos.Kostkuj.FileManager;
import by.domos.Kostkuj.Main;
import by.domos.Kostkuj.enums.EKostkujRole;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class DiscordListener extends ListenerAdapter {

    DiscordCommandTranslator dct = new DiscordCommandTranslator();
    FileManager fm = FileManager.getInstance();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getChannel().getName().equalsIgnoreCase(DiscordConnect.channel))return;
        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;
        String[] args = event.getMessage().getContentRaw().split("");
        if (event.getMessage().getContentRaw().equalsIgnoreCase("/auth")){
            DiscordAuth.discordIdList.add(event.getAuthor().getId());
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth uid:" + event.getAuthor().getId(), event.getAuthor());
            return;
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(fm.getDiscordAuth().getString("DiscordAuth." + event.getAuthor().getId()));
        } catch (NullPointerException e){
            DiscordConnect.sendPrivateMsg("Tvůj účet není aktivován pro odesílání zpráv z discordu. Pro aktivaci se drž pokynů.", event.getAuthor());
            DiscordAuth.discordIdList.add(event.getAuthor().getId());
            DiscordConnect.sendPrivateMsg("**SEND COMMAND IN GAME**: /k discordauth uid:" + event.getAuthor().getId(), event.getAuthor());
            return;
        }
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
        if (args[0].equalsIgnoreCase("/")) {
            dct.CommandTranslate(event.getMessage().getContentRaw().split(" "), event.getAuthor());
            return;
        }

        Member member = event.getMember();
        if (member.getRoles().get(0) == null) return;
        String roleFromDiscord = member.getRoles().get(0).getName();
        EKostkujRole role = EKostkujRole.getRoleFromName(roleFromDiscord);
        Bukkit.getServer().broadcastMessage("<[§dDC§7§f] " + role.getPrefix() + op.getName() + "§f> " + role.getChatcolor() + event.getMessage().getContentRaw().replace("&",  "§"));
    }
}
