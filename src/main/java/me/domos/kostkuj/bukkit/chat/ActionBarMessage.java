package me.domos.kostkuj.bukkit.chat;


import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ActionBarMessage {

    public void sendMsg(String message, @NotNull Player player){
        CraftPlayer craftPlayer = (CraftPlayer)player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, player.getUniqueId());
        craftPlayer.getHandle().playerConnection.sendPacket(ppoc);
    }

    public void sendBroadcastMsg(String message){
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
            PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, player.getUniqueId());
            craftPlayer.getHandle().playerConnection.sendPacket(ppoc);
        }
    }

}
