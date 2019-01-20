package me.domos.Kostkuj.server.listener.cmds;

import me.domos.Kostkuj.FileManager;
import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLis;
import me.domos.Kostkuj.connect.MySQL.Projekty.MySQLGetProjekt;
import me.domos.Kostkuj.connect.MySQL.commandRequest.MRequestGet;
import me.domos.Kostkuj.connect.MySQL.player.Bans.MySQLJsonBuilderForPlayerJoin;
import me.domos.Kostkuj.connect.MySQL.player.LuckPerms.MySQLLuckPerms2;
import me.domos.Kostkuj.connect.MySQL.trests.MTrests;
import me.domos.Kostkuj.server.KeyGenerator;
import me.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import me.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.itemy.EnchBuilder;
import me.domos.Kostkuj.server.listener.cmds.kostkuj.Kostkuj_Save;
import me.domos.Kostkuj.server.luckperms.LPCoreUser;
import me.domos.Kostkuj.server.player.KPlayer;
import me.domos.Kostkuj.server.player.PlayerManager;
import me.domos.Kostkuj.server.player.event.EventPlayerJoin;
import me.domos.Kostkuj.server.player.ipmanager.IpHasher;
import me.domos.Kostkuj.server.time.Time;
import me.domos.Kostkuj.server.votemute.VoteMuteSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



public class Test implements CommandExecutor {
    FileManager sett = FileManager.getInstance();
    SendSystem ss = new SendSystem();
    Time time = new Time();
    PlayerManager pm = new PlayerManager();
    MySQLJsonBuilderForPlayerJoin mjb = new MySQLJsonBuilderForPlayerJoin();
    JsonBroadCast jbc = new JsonBroadCast();
    JsonSendMessage jsm = new JsonSendMessage();
    Kostkuj_Save s = new Kostkuj_Save();
    MySQLis mis = new MySQLis();
    MySQL mi = new MySQL();
    MySQLget mget = new MySQLget();
    LPCoreUser lpCoreUser = new LPCoreUser();
    MySQLLuckPerms2 mlps = new MySQLLuckPerms2();
    MySQLGetProjekt mgp = new MySQLGetProjekt();
    EnchBuilder eb = EnchBuilder.getInstance();
    MRequestGet mrg = new MRequestGet();
    IpHasher ih = IpHasher.getInstance();
    MTrests mt = new MTrests();
    EventPlayerJoin epj = new EventPlayerJoin();
    KeyGenerator keyGenerator = new KeyGenerator();
    VoteMuteSettings vmt = new VoteMuteSettings();

    // /projekt <user> <jmeno>

    private Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if (!sr.hasPermission("kostkuj.admin.test")){
            ss.noPerm(sr);
            return true;
        }

        KPlayer p = KPlayer.getPlayer(sr);
        sr.sendMessage(p.getPlayer().getName());

        /*Player p = Bukkit.getServer().getPlayer(sr.getName());

        epj.playerIpEdit(p);*/

        /*int[] rule = {1,2,3};

        String user = null;

        int id = mt.setTrest(1, "123", user, 1, "Ano, ne", Time.getTimeSec(0), Time.getTimeSec(0), 0, rule);

        Bukkit.getServer().broadcastMessage(id + "");*/

///////////////////////////////////////////////////////////////////////////////////////////////////////

        //CMDTrest trest = new CMDTrest(sr, args);

        /*String[] pole = args[0].split("");

        String[] nuber = ih.nuberReplace(pole);

        for (int i = 0; pole.length > i; i++){
            numer = numer + nuber[i];
        }*/

        //Bukkit.getServer().broadcastMessage("§cVlozenaIP: §7" + args[0]);
        //Bukkit.getServer().broadcastMessage(ChatColor.RED + "HashIP: §7" + ih.hashIp(args[0]));

        /*Player p = Bukkit.getServer().getPlayer(sr.getName());

        ItemStack item = ib.item(Material.BOW, (byte) 0, 1, eb.iEnch(args[0]));

        p.getInventory().addItem(item);*/


        /*InetAddress ip = Bukkit.getServer().getPlayer(sr.getName()).getAddress().getAddress();

        if(ip == null){
            ss.info(sr, "IP je null");
            return true;
        }

        if(ip.isAnyLocalAddress() || ip.isLoopbackAddress()){
            ss.info(sr, "IP je localni");
            return true;
        }

        GeoIP geo = new GeoIP(ip);
        String stat = geo.countryCode;
        Bukkit.getServer().broadcastMessage("stat: " + stat);

        ///////////////////////

        String ench = "mending";
        String enchs = "mending, unr, inf";

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta im = item.getItemMeta();
        im.addEnchant(Enchantment.MENDING, 1, true);
        im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        im.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
        item.setItemMeta(im);
        Bukkit.getServer().getPlayer(sr.getName()).getInventory().addItem(item);
        }*/
        return false;
    }

}
