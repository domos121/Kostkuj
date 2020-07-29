package me.domos.kostkuj.general.fileManager;

import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public enum ECfg {

    //MYSQL
    MYSQL_PREFIX(ConfigManager.CONFIG.getConfig().getString("MySQL.prefix")),
    MYSQL_HOST(ConfigManager.CONFIG.getConfig().getString("MySQL.host")),
    MYSQL_PORT(ConfigManager.CONFIG.getConfig().getString("MySQL.port")),
    MYSQL_DATABASE(ConfigManager.CONFIG.getConfig().getString("MySQL.database")),
    MYSQL_URL("jdbc:mysql://" + MYSQL_HOST.getValue() + ":" + MYSQL_PORT.getValue() + "/" + MYSQL_DATABASE.getValue() + "?autoReconnect=true&characterEncoding=utf8"),
    MYSQL_USERNAME(ConfigManager.CONFIG.getConfig().getString("MySQL.username")),
    MYSQL_PASSWORD(ConfigManager.CONFIG.getConfig().getString("MySQL.password")),
    MYSQL_TABLE_USERS(ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableusers.tablename")),
    MYSQL_TABLE_USERS_COLUMN_ID(ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableusers.columns.id")),
    MYSQL_TABLE_USERS_COLUMN_UUID(ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableusers.columns.uuid")),
    MYSQL_TABLE_USERS_COLUMN_USERNAME(ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableusers.columns.username")),
    MYSQL_TABLE_USERS_COLUMN_ENABLED(ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableusers.columns.enabled")),
    MYSQL_TABLE_PLACE(MYSQL_PREFIX.getValue() + ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tableplace.tablename")),
    MYSQL_TABLE_BREAK(MYSQL_PREFIX.getValue() + ConfigManager.CONFIG.getConfig().getString("MySQL.tables.tablebreak.tablename")),

    //DISCORD
    DISCORD_TOKEN(ConfigManager.CONFIG.getConfig().getString("Discord.botToken")),
    DISCORD_CHANNEL(ConfigManager.CONFIG.getConfig().getString("Discord.botChannel")),
    DISCORD_USER_NAME_PREFIX(ConfigManager.CONFIG.getConfig().getString("Discord.userNamePrefix")),

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
        return ConfigManager.CONFIG.getConfig().getInt("repeteTime");
    }

    public static boolean isAllowAutoWriter(){
        return ConfigManager.CONFIG.getConfig().getBoolean("allowAutoWriter");
    }

    public static boolean isAllowdebugMod(){
        return true;
    }

    public static boolean isAcceptItemForAntiXray(Block b){
        String blockType = b.getType().name();
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(ConfigManager.CONFIG.getConfig().getStringList("itemlistchacker"));
        return obj.contains(blockType);
    }

    public static List<String> getListAutoMessages(){
        ArrayList<String>  autoMessages = new ArrayList<String>();
        autoMessages.addAll(ConfigManager.CONFIG.getConfig().getStringList("autoMessage"));
        return autoMessages;
    }

    public static String[][] getTimeListWithEvents(){
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(ConfigManager.CONFIG.getConfig().getStringList("timeArray")); // 00-000,test
        String[][] time = new String[obj.size()][];
        for (int i = 0; i < obj.size(); i++){
            time[i] = obj.get(i).split(",");
        }
        return time;
    }

    public static boolean isMutedCommand(String command){
        ArrayList<String> obj = new ArrayList<String>();
        obj.addAll(ConfigManager.CONFIG.getConfig().getStringList("muteCommand"));
        return obj.contains(command);
    }
}
