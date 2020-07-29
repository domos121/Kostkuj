package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonBroadCast;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandMres implements IDiscordCommand{

    private JsonBroadCast jbc = new JsonBroadCast();
    private CustomJsonBuilder jb = new CustomJsonBuilder();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        jbc.jsonBcKostkuj("[\"\",{\"text\":\"#prefix# \"},{\"text\":\"Residence jsou součástí balíčku \",\"color\":\"gray\"},{\"text\":\"[\"},{\"text\":\"D-VIP\",\"color\":\"yellow\"},{\"text\":\"]\"},{\"text\":\". V případě zájmu je lze samostatně dokoupit na našem \",\"color\":\"gray\"},{\"text\":\"➥Shopu\",\"color\":\"dark_aqua\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://kostkuj.cz/shop\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\">> \",\"color\":\"gray\"},{\"text\":\"Klikni\",\"color\":\"dark_aqua\"},{\"text\":\" <<\",\"color\":\"gray\"}]}},{\"text\":\", avšak nejsou zde potřebné, na serveru se nachází plugin, díky kterému jsou Moderátoři schopni navrátit veškeré škody a viníka potrestat. Stačí pouze kontaktovat některého \",\"color\":\"gray\"},{\"text\":\"[\"},{\"text\":\"Mod\",\"color\":\"dark_green\"},{\"text\":\"]\"},{\"text\":\".\",\"color\":\"gray\"}]");
        DiscordConnect.sendMsg("Příkaz /mres odeslán!");
        return false;
    }

}
