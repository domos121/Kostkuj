package by.domos.Kostkuj.server.chat;

import by.domos.Kostkuj.Main;
import by.domos.Kostkuj.enums.ECfg;
import by.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AutoMessager {

    public void autoMessage() {
        new BukkitRunnable() {

            private int i = 0;

            private List<String> messages = ECfg.getListAutoMessages();

            private final JsonBroadCast jbc = new JsonBroadCast();

            public void run() {
                ++i;
                if(!(i <= messages.size()-1)) i = 0;
                jbc.jsonBcKostkuj(messages.get(i));
            }
        }.runTaskTimer(Main.plugin, 20 * ECfg.getTimeForAutoMessages(), 20 * ECfg.getTimeForAutoMessages());
    }
}
