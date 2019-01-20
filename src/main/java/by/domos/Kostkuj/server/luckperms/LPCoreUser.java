package by.domos.Kostkuj.server.luckperms;

import by.domos.Kostkuj.connect.MySQL.player.LuckPerms.MySQLLuckPerms;
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
