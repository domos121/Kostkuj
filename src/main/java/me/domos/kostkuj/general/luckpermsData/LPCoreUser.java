package me.domos.kostkuj.general.luckpermsData;

import me.domos.kostkuj.general.connect.mysql.player.luckPerms.MySQLLuckPerms;
import org.bukkit.entity.Player;

public class LPCoreUser {

    GetUserGroup group = new GetUserGroup();
    MySQLLuckPerms mysqllp = new MySQLLuckPerms();

    public void core(Player p){
        String name = p.getName();
        String role = mysqllp.isWebVip(name);
        String prgroup = group.getUserPrimaryGroup(name);
        boolean isvip = group.isPrimaryGroupVIP(prgroup);
        boolean isviponweb = role.contains("VIP");
        boolean isdefault = role.equals("a:0:{}");

        if (isviponweb || isdefault) {
            if (isvip && !isviponweb) {
                mysqllp.setVip(name, "a:1:{i:0;s:8:\"ROLE_VIP\";}");
                return;
            } else if (!isvip && isviponweb) {
                mysqllp.setVip(name, "a:0:{}");
                return;
            }
        }
    }
}
