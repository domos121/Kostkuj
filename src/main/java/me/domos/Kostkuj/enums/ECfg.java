package me.domos.Kostkuj.enums;

import me.domos.Kostkuj.Main;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public enum ECfg {

    //MYSQL
    MYSQL_PREFIX(Main.settings.getConfig().getString("MySQL.prefix")),
    MYSQL_HOST(Main.settings.getConfig().getString("MySQL.host")),
    MYSQL_PORT(Main.settings.getConfig().getString("MySQL.port")),
    MYSQL_DATABASE(Main.settings.getConfig().getString("MySQL.database")),
    MYSQL_URL("jdbc:mysql://" + MYSQL_HOST.getValue() + ":" + MYSQL_PORT.getValue() + "/" + MYSQL_DATABASE.getValue() + "?autoReconnect=true"),
    MYSQL_USERNAME(Main.settings.getConfig().getString("MySQL.username")),
    MYSQL_PASSWORD(Main.settings.getConfig().getString("MySQL.password")),
    MYSQL_TABLE_USERS(Main.settings.getConfig().getString("MySQL.tables.tableusers.tablename")),
    MYSQL_TABLE_USERS_COLUMN_ID(Main.settings.getConfig().getString("MySQL.tables.tableusers.columns.id")),
    MYSQL_TABLE_USERS_COLUMN_UUID(Main.settings.getConfig().getString("MySQL.tables.tableusers.columns.uuid")),
    MYSQL_TABLE_USERS_COLUMN_USERNAME(Main.settings.getConfig().getString("MySQL.tables.tableusers.columns.username")),
    MYSQL_TABLE_USERS_COLUMN_ENABLED(Main.settings.getConfig().getString("MySQL.tables.tableusers.columns.enabled")),
    MYSQL_TABLE_PLACE(MYSQL_PREFIX.getValue() + Main.settings.getConfig().getString("MySQL.tables.tableplace.tablename")),
    MYSQL_TABLE_BREAK(MYSQL_PREFIX.getValue() + Main.settings.getConfig().getString("MySQL.tables.tablebreak.tablename")),

    //DISCORD
    DISCORD_TOKEN(Main.settings.getConfig().getString("Discord.botToken")),
    DISCORD_CHANNEL(Main.settings.getConfig().getString("Discord.botChannel")),
    DISCORD_USER_NAME_PREFIX(Main.settings.getConfig().getString("Discord.userNamePrefix")),

    //settings
    LOGIN_TIME_OUT_TIME("30")
    ;

    private String value;

    ECfg(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getInt(){
        return Integer.parseInt(value);
    }

    public static int getTimeForAutoMessages() {
        return Main.settings.getConfig().getInt("repeteTime");
    }

    public static boolean isAllowAutoWriter(){
        return Main.settings.getConfig().getBoolean("allowAutoWriter");
    }

    public static boolean isAcceptItemForAntiXray(Block b){
        String blockType = b.getType().name();
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(Main.settings.getConfig().getStringList("itemlistchacker"));
        return obj.contains(blockType);
    }

    public static List<String> getListAutoMessages(){
        ArrayList<String>  autoMessages = new ArrayList<String>();
        autoMessages.addAll(Main.settings.getConfig().getStringList("autoMessage"));
        return autoMessages;
    }

    public static String[][] getTimeListWithEvents(){
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(Main.settings.getConfig().getStringList("timeArray")); // 00-000,test
        String[][] time = new String[obj.size()][];
        for (int i = 0; i < obj.size(); i++){
            time[i] = obj.get(i).split(",");
        }
        return time;
    }

    public static boolean isMutedCommand(String command){
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(Main.settings.getConfig().getStringList("muteCommand"));
        return obj.contains(command);
    }
}
