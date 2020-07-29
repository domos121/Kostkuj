package me.domos.kostkuj.bukkit.chat.json;

import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JsonBroadCast {

    public void jsonBcKostkuj(String JSON){
        List<Player> onlinePlayers = new ArrayList<Player>();
        for (Player c : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(c);
                IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                        .replace("#prefix#", "§8[§cKostkuj§8]:§r")
                        .replace("#info#", "§8[§cInfo§8]:§r")
                        .replace("#sr#", c.getName()));
                PacketPlayOutChat packet = new PacketPlayOutChat(comp, ChatMessageType.CHAT, c.getUniqueId());
                ((CraftPlayer) c).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void jsonBcKostkuj(String JSON, String permissions){
        List<Player> onlinePlayers = new ArrayList<Player>();
        for (Player c : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(c);
            if (c.hasPermission(permissions)) {
                IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                        .replace("#prefix#", "§8[§cKostkuj§8]:§r")
                        .replace("#info#", "§8[§cInfo§8]:§r"));
                PacketPlayOutChat packet = new PacketPlayOutChat(comp, ChatMessageType.CHAT, c.getUniqueId());
                ((CraftPlayer) c).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
}
