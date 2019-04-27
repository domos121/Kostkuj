package me.domos.kostkuj.bukkit.chat.json;


import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JsonSendMessage {

    public void jsonBcKostkuj(Player p, String JSON){
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                .replace("#prefix#", "§8[§cKostkuj§8]:§r"));
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    public void jsonBcKostkuj(CommandSender sr, String JSON){
        Player p = Bukkit.getPlayer(sr.getName()).getPlayer();
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSON
                .replace("#prefix#", "§8[§cKostkuj§8]:§r"));
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
}
