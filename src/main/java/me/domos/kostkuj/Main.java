package me.domos.kostkuj;

import me.domos.kostkuj.bukkit.chat.AutoMessager;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.listeners.cmds.BroadCast;
import me.domos.kostkuj.bukkit.listeners.cmds.Pravidla;
import me.domos.kostkuj.bukkit.listeners.cmds.Prikazy;
import me.domos.kostkuj.bukkit.listeners.cmds.bans.CMDTrest;
import me.domos.kostkuj.bukkit.listeners.cmds.bans.UnBan;
import me.domos.kostkuj.bukkit.listeners.cmds.craftCoin.Voucher;
import me.domos.kostkuj.bukkit.listeners.cmds.domos.Domos;
import me.domos.kostkuj.bukkit.listeners.cmds.kostkuj.Kostkuj;
import me.domos.kostkuj.bukkit.listeners.cmds.kostkuj.Kostkuj_Save;
import me.domos.kostkuj.bukkit.listeners.cmds.playerInfo.CheckBan;
import me.domos.kostkuj.bukkit.listeners.cmds.playerInfo.CheckIp;
import me.domos.kostkuj.bukkit.listeners.cmds.playerInfo.PlayerInfo;
import me.domos.kostkuj.bukkit.listeners.cmds.projekty.CMDProject;
import me.domos.kostkuj.bukkit.listeners.cmds.stavba.CMDStavba;
import me.domos.kostkuj.bukkit.listeners.cmds.votemute.VoteMute;
import me.domos.kostkuj.bukkit.listeners.events.*;
import me.domos.kostkuj.bukkit.time.Timer;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.discord.DiscordListener;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.connect.mysql.mysqlListener.MySQLfunction;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public class Main extends JavaPlugin implements Listener{

    public static Random random = new Random();
    public static ConfigManager settings = ConfigManager.getInstance();
    public static Chat chat = null;
    public static Permission perm = null;
    public static final SendSystem ss = new SendSystem();
    private PluginManager pm = Bukkit.getPluginManager();
    private AutoMessager am = new AutoMessager();
    public static Plugin plugin = null;
    public static Long startTime;

    @Override
    public void onEnable(){
        setupChat();
        setupPermission();
        plugin = this;
        settings.setup(this);
        EMessages.checkConfig();
        MySQL mysql = new MySQL();
        MySQLfunction mysqlfunction = new MySQLfunction();
        commandLoad();
        eventLoad();
        mysql.connect();
        mysqlfunction.createTables();
        am.autoMessage();
        Timer.Time();
        Timer.saveMap();
        DiscordConnect.startBot();
        DiscordConnect.jda.addEventListener(new DiscordListener());
        Bukkit.getConsoleSender().sendMessage("[kostkuj] Is aktivated!");
        startTime = System.currentTimeMillis();
    }

    public void onDisable() {
        new Kostkuj_Save().saveWorld();
        DiscordConnect.stopBot();
    }
    
    private void commandLoad(){
        getCommand("kostkuj").setExecutor(new Kostkuj());
        getCommand("unban").setExecutor(new UnBan());
        getCommand("checkip").setExecutor(new CheckIp());
        getCommand("test").setExecutor(new Test());
        getCommand(ECmd.PLAYERINFO.getCmd()).setExecutor(new PlayerInfo());
        getCommand("broadcast").setExecutor(new BroadCast());
        getCommand("pravidla").setExecutor(new Pravidla());
        getCommand("prikazy").setExecutor(new Prikazy());
        getCommand("trest").setExecutor(new CMDTrest());
        getCommand("checkban").setExecutor(new CheckBan());
        getCommand("project").setExecutor(new CMDProject());
        getCommand(ECmd.VOUCHER.getCmd()).setExecutor(new Voucher());
        getCommand(ECmd.VOTEMUTE.getCmd()).setExecutor(new VoteMute());
        getCommand(ECmd.STAVBA.getCmd()).setExecutor(new CMDStavba());
        getCommand(ECmd.STAVBA.getCmd()).setTabCompleter(new CMDStavba());
        getCommand(ECmd.DOMOS.getCmd()).setExecutor(new Domos());
    }

    private void eventLoad(){
        pm.registerEvents(new Event_PlayerDeath(), this);
        pm.registerEvents(new Event_BlockBreakEvent(), this);
        pm.registerEvents(new Event_BlockPlaceEvent(), this);
        pm.registerEvents(new Event_PlayerJoinEvent(), this);
        pm.registerEvents(new Event_PlayerQuitEvent(), this);
        pm.registerEvents(new Event_PlayerPreLoginEvent(), this);
        pm.registerEvents(new Event_Chat(), this);
        pm.registerEvents(new Event_PingServer(), this);
        pm.registerEvents(new Event_HandBreak(), this);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }

    private boolean setupPermission() {
        RegisteredServiceProvider<Permission> permissinProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissinProvider != null) {
            perm = permissinProvider.getProvider();
        }
        return (perm != null);
    }



}
