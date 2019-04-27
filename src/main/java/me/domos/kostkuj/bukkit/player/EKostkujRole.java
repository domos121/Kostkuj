package me.domos.kostkuj.bukkit.player;

import java.util.ArrayList;
import java.util.List;

public enum EKostkujRole {

    Nikdo("", "", ""),
    Moderator("moderator", "§f[§2MOD§f] §2", "§a"),
    Hl_Moderator("hl. moderator", "§f[§aHL.MOD§f] §a", "§2"),
    Poradce("poradce", "§f[§1Poradce§f] §1", "§3"),
    Admin("admin", "§f[§4Admin§f] §4", "§c");

    private String name;
    private String prefix;
    private String chatcolor;

    EKostkujRole(String name, String prefix, String chatcolor){
        this.name = name;
        this.prefix = prefix;
        this.chatcolor = chatcolor;
    }

    public String getName(){
        return this.name;
    }

    public String getPrefix(){
        return this.prefix;
    }

    public String getChatcolor(){
        return this.chatcolor;
    }

    public static EKostkujRole getRoleFromName(String name){
        for (EKostkujRole role : EKostkujRole.values()) {
            if (role.getName().equalsIgnoreCase(name.toLowerCase())) {
                return role;
            }
        }
        return EKostkujRole.Nikdo;
    }

    public static List<EKostkujRole> getAccesRoleList(EKostkujRole userRole){
        List<EKostkujRole> accesRole = new ArrayList<>();
        for (EKostkujRole role : EKostkujRole.values()){
            if (role != userRole){
                accesRole.add(role);
            } else {
                accesRole.add(role);
                break;
            }
        }

        return accesRole;
    }
}
