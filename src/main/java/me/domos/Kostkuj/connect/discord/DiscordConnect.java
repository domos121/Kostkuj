package me.domos.Kostkuj.connect.discord;

import me.domos.Kostkuj.enums.ECfg;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;

public class DiscordConnect {

    public static JDA jda;

    public static String channel = ECfg.DISCORD_CHANNEL.getValue();

    private static String[] colorList = {"§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f", "§k", "§l", "§m", "§n", "§o", "§r"};

    public static void startBot(){
        try {
            DiscordConnect.jda = new JDABuilder(AccountType.BOT).setToken(ECfg.DISCORD_TOKEN.getValue()).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendMsg(String msg, Player p){
        TextChannel text = jda.getTextChannelsByName(channel, true).get(0);
        msg = "**" + p.getDisplayName() + ":** " + msg.replace("@", "[ZAVINÁČ]");
        String var0 = chatTranslateToDiscord(msg);
        text.sendMessage(var0).queue();
    }

    public static void sendMsg(String msg){
        TextChannel text = jda.getTextChannelsByName(channel, true).get(0);
        String var0 = chatTranslateToDiscord(msg);
        text.sendMessage(var0).queue();
    }

    public static void sendPrivateMsg(String msg, User user){
        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessage(chatTranslateToDiscord(msg)).queue();
        });
    }

    private static String chatTranslateToDiscord(String var0){
        String var1 = var0;
        for (String color: colorList){
            var1 = var1.replace(color, "");
        }
        var1 = var1.replace("@", "[ZAVINÁČ]");
        return var1;
    }


}
