package by.domos.Kostkuj;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLfunction;
import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.connect.discord.DiscordListener;
import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.enums.EMessages;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.listener.cmds.CraftCoin.Voucher;
import by.domos.Kostkuj.server.listener.cmds.*;
import by.domos.Kostkuj.server.listener.cmds.bans.*;
import by.domos.Kostkuj.server.listener.cmds.domos.Domos;
import by.domos.Kostkuj.server.listener.cmds.kostkuj.Kostkuj;
import by.domos.Kostkuj.server.listener.cmds.playerinfo.*;
import by.domos.Kostkuj.server.listener.cmds.projekty.CMDProject;
import by.domos.Kostkuj.server.listener.cmds.stavba.CMDStavba;
import by.domos.Kostkuj.server.listener.cmds.votemute.VoteMute;
import by.domos.Kostkuj.server.listener.events.*;
import by.domos.Kostkuj.server.chat.AutoMessager;
import by.domos.Kostkuj.server.time.Timer;
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
    public static FileManager settings = FileManager.getInstance();
    public static Chat chat = null;
    public static Permission perm = null;
    public static final SendSystem ss = new SendSystem();
    private PluginManager pm = Bukkit.getPluginManager();
    private AutoMessager am = new AutoMessager();


    public static Plugin plugin = null;

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
        Bukkit.getConsoleSender().sendMessage("[Kostkuj] Is aktivated!");
    }

    public void onDisable() {
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
