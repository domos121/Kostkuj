package by.domos.Kostkuj.enums;

public enum EKostkujRole {

    Admin("admin", "§f[§4Admin§f] §4", "§c"),
    Poradce("poradce", "§f[§1Poradce§f] §1", "§3"),
    Moderator("moderator", "§f[§2MOD§f] §2", "§a"),
    Hl_Moderator("hl. moderator", "§f[§aHL.MOD§f] §a", "§2"),
    Nikdo("", "", "");

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

}
