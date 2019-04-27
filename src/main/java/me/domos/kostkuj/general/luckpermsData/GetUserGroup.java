package me.domos.kostkuj.general.luckpermsData;

import me.domos.kostkuj.general.connect.mysql.player.luckPerms.MySQLLuckPerms2;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.OptionalInt;

public class GetUserGroup {

    RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
    LuckPermsApi api = provider.getProvider();
    MySQLLuckPerms2 mylp = new MySQLLuckPerms2();

    public String getUserPrimaryGroup(String name){
        return api.getUser(name).getPrimaryGroup();
    }

    public boolean isPrimaryGroupVIP(String group){
        return group.contains("vip");
    }

    public int getWeight(String name){
        String group = mylp.getPrimaryGroup(name);
        OptionalInt weight = api.getGroup(group).getWeight();
        if (weight == null){
            return -1;
        } else {
            return weight.getAsInt();
        }
    }
}
