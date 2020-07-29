package me.domos.kostkuj.bukkit.chat;


import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarMessage {

    public void sendMsg(String message, Player player){
        CraftPlayer craftPlayer = (CraftPlayer)player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, player.getUniqueId());
        craftPlayer.getHandle().playerConnection.sendPacket(ppoc);
    }

}
