package me.domos.kostkuj.bukkit.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_16_R1.MojangsonParser;
import net.minecraft.server.v1_16_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SerializeDeserializeItem {

    public JsonObject srializeItem(List<ItemStack> items){
        JsonObject finalJsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();


        for(ItemStack item : items){
            JsonObject jsonObject = new JsonObject();


            NBTTagCompound nbtTagCompound = CraftItemStack.asNMSCopy(item).save(new NBTTagCompound());
            jsonObject.addProperty("ntb", nbtTagCompound.toString());
            jsonArray.add(jsonObject);

        }
        finalJsonObject.add("items", jsonArray);
        //Bukkit.getServer().broadcastMessage(finalJsonObject.toString());


        return finalJsonObject;
    }

    public List<ItemStack> deserializeItem(JsonObject json){
        List<ItemStack> items = new ArrayList<>();

        if (json == null){
            return items;
        }

        JsonArray jsonArray = json.getAsJsonArray("items");

        for (JsonElement jsonElement : jsonArray){
            String stringItem = jsonElement.getAsJsonObject().get("ntb").getAsString();
            NBTTagCompound ntb = null;
            try {
                ntb = MojangsonParser.parse(stringItem);
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            ItemStack it = CraftItemStack.asBukkitCopy(net.minecraft.server.v1_16_R1.ItemStack.a(ntb));
            items.add(it);
        }
        return items;
    }
}
