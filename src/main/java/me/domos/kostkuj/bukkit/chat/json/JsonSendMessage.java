package me.domos.kostkuj.bukkit.chat.json;


import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JsonSendMessage {

    public void jsonBcKostkuj(Player p, String JSON){
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                .replace("#prefix#", "§8[§cKostkuj§8]:§r"));
        PacketPlayOutChat packet = new PacketPlayOutChat(comp, ChatMessageType.CHAT, p.getUniqueId());
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    public void jsonBcKostkuj(CommandSender sr, String JSON){
        Player p = Bukkit.getPlayer(sr.getName()).getPlayer();
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                .replace("#prefix#", "§8[§cKostkuj§8]:§r"));
        PacketPlayOutChat packet = new PacketPlayOutChat(comp, ChatMessageType.CHAT, p.getUniqueId());
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
}
