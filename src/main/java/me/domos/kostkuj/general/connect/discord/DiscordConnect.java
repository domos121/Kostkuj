package me.domos.kostkuj.general.connect.discord;

import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;

import static me.domos.kostkuj.Main.discordListener;

public class DiscordConnect {

    public static JDA jda;

    public static String channel = ECfg.DISCORD_CHANNEL.getValue();

    private static String[] colorList = {"§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f", "§k", "§l", "§m", "§n", "§o", "§r"};

    private static Time time = new Time();

    public static void startBot(){
        try {
            DiscordConnect.jda = new JDABuilder(AccountType.BOT).setToken(ECfg.DISCORD_TOKEN.getValue()).buildBlocking();
            sendMsg(time.getDayTime() + " " + ":white_check_mark:**kostkuj** *se připojil*.");
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DiscordConnect.jda.addEventListener(discordListener);
    }

    public static void stopBot(){
        sendMsg(":octagonal_sign:**kostkuj** *se odpojil*.");
        jda.getEventManager().unregister(discordListener);
        jda.shutdown();
    }

    public static void sendMsg(String msg){
        TextChannel text = jda.getTextChannelsByName(channel, true).get(0);
        String var0 = chatTranslateToDiscord(msg);
        text.sendMessage(var0).queue();
    }
    public static void sendMsg(String msg, String channel){
        TextChannel text = jda.getTextChannelsByName(channel, true).get(0);
        String var0 = chatTranslateToDiscord(msg);
        text.sendMessage(var0).queue();
    }

    public static void sendPlayerMsg(String msg, Player p){
        msg = time.getDayTime() + " " + ECfg.DISCORD_USER_NAME_PREFIX.getValue() + "**" + p.getDisplayName() + "** » " + msg.replace("@", "[ZAVINÁČ]");
        sendMsg(msg);
    }

    public static void sendInfoMsg(String msg){
        DiscordConnect.sendOrangeMsg(EMessages.PLUGIN_PREFIX.getValue() + msg);
    }

    public static void sendOrangeMsg(String msg){
        DiscordConnect.sendMsg("```fix\n" + time.getDayTime() + " " + msg +  "\n```");
    }

    public static void sendYellowMsg(String msg){
        DiscordConnect.sendMsg("```css\n" + time.getDayTime() + " " + msg +  "\n```");
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
