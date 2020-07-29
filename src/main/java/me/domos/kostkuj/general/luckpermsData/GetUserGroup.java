package me.domos.kostkuj.general.luckpermsData;

import me.domos.kostkuj.general.connect.mysql.player.luckPerms.MySQLLuckPerms2;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.OptionalInt;

public class GetUserGroup {

    RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    LuckPerms api = provider.getProvider();
    MySQLLuckPerms2 mylp = new MySQLLuckPerms2();

    public String getUserPrimaryGroup(String name){
        return api.getUserManager().getUser(name).getPrimaryGroup();
    }

    public boolean isPrimaryGroupVIP(String group){
        return group.contains("vip");
    }

    public int getWeight(String name){
        String group = mylp.getPrimaryGroup(name);
        OptionalInt weight = api.getGroupManager().getGroup(group).getWeight();
        if (weight == null){
            return -1;
        } else {
            return weight.getAsInt();
        }
    }
}
