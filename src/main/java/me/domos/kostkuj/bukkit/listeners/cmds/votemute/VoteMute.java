package me.domos.kostkuj.bukkit.listeners.cmds.votemute;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonBroadCast;
import me.domos.kostkuj.bukkit.chat.manage.BayPass;
import me.domos.kostkuj.bukkit.chat.manage.voteMute.VoteMuteSettings;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.bukkit.time.Timer;
import me.domos.kostkuj.general.connect.mysql.MVoteMute;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteMute implements CommandExecutor {

    private VoteMuteSettings vmt = new VoteMuteSettings();
    private SendSystem ss = new SendSystem();
    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private JsonBroadCast jbc = new JsonBroadCast();
    private MVoteMute mvm = new MVoteMute();
    private Timer t = new Timer();
    private BayPass bp = new BayPass();
    private VoteMute_Get vmg = new VoteMute_Get();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission(ECmd.VOTEMUTE.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }

        int minpeopel = 5;

        if (Bukkit.getServer().getOnlinePlayers().size() >= 20){
            minpeopel = 6;
        }

        if (Bukkit.getServer().getOnlinePlayers().size() >= 30){
            minpeopel = 7;
        }

        if (args.length == 0){
            if (vmt.isActive()){
                if (!sr.hasPermission(ECmd.VOTEMUTE_VOTE.getPerm())){
                    return true;
                }
                if (vmt.getList().contains(sr.getName())){
                    ss.info(sr, "Už jsi hlasoval!");
                    return true;
                }
                ss.info(sr, "Hlas zapocitan.");

                mvm.setAddVote(sr.getName(), vmt.getMuteId());

                vmt.addVote(sr.getName());


                int id = vmt.getMuteId();

                String list = vmt.getList().toString().replace("[", "").replace("]", "");

                String hover = "§cVoteMute: §7#" + vmt.getMuteId() + "\n §7Hrisnik: §c" + vmt.getUser() + "\n §7Vyvolal: §c" + vmt.getActivated() + "\n Seznam hlasů: §8[§c" + vmt.getList().size() + "§7/§c" + minpeopel + "§8]\n  §8- §c" + vmt.getList().toString().replace("[", "").replace("]", "").replace(",","\n  §8-§c");
                jbc.jsonBcKostkuj(cjb.clickhoverText("§bHráč §f" + sr.getName() + "§b hlasoval pro mute§b.§8 [§c" + vmt.getList().size() + "§7/§c" + minpeopel + "§8]", "", hover, "run_command", "/voteMute"));

                if(vmt.getList().size() >= minpeopel){
                    if (vmt.clearVote()){
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "trest a:mute u:" + vmt.getUser() + " r:105 t:1h d:Schválili: " + list + " id-#" + id);
                        mvm.setUpdateVote(id);
                        vmt.setActiv(false, null, null, 0);
                    }
                    return true;
                }
            } else {
                if (sr.hasPermission(ECmd.VOTEMUTE_CREATE.getPerm())){
                    ss.info(sr, "Právě neprobíhá žádné hlasování, v případě nutnosti použij /voteMute [user] pro vyvoření nového.");
                } else {
                    ss.info(sr, "Neprobíhá žádné hlasování, v případě nutnosti kontaktuj pověřenou osobu (VIP).");
                }
            }
        }

        if (args[0].equalsIgnoreCase(ECmd.VOTEMUTE_GET.getLastarg())){
            if (!sr.hasPermission(ECmd.VOTEMUTE_GET.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            vmg.get(sr, args);
            return true;
        } else if (args[0].equalsIgnoreCase(ECmd.VOTEMUTE_HELP.getLastarg())){
            if (!sr.hasPermission(ECmd.VOTEMUTE_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            PlayerManager.helpMenu(sr, "voteMute.");
            return true;
        }

        if (args.length >= 1) {
            if (vmt.isActive()) {
                if (sr.hasPermission(ECmd.VOTEMUTE_CREATE.getPerm())){
                    ss.info(sr, "Vote mute už je aktivované.");
                } else {
                    ss.noPerm(sr);
                }
            } else {

                if (!sr.hasPermission(ECmd.VOTEMUTE_CREATE.getPerm())) {
                    ss.noPerm(sr);
                    return true;
                }
                OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
                if (!op.isOnline()){
                    ss.info(sr, "Hrac neexistuje, nebo není online!");
                    return true;
                }
                if ((!bp.isPovolenoWeight(sr.getName(), args[0]))){
                    ss.info(sr, "Nemůžeš trestat své nadřízené");
                    return true;
                }
                int voteMuteId = mvm.setCreateMute(args[0], sr.getName());
                t.voteMuteClose();
                vmt.addVote(sr.getName());
                vmt.setActiv(true, op.getName(), sr.getName(), voteMuteId);
                String hover = "§cVoteMute: §7#" + voteMuteId + "\n §7Hrisnik: §c" + op.getName() + "\n §7Vyvolal: §c" + sr.getName();
                jbc.jsonBcKostkuj(cjb.clickhoverText("§bHráč §f" + sr.getName() + "§b vyhlásil voteMute na hráče §f" + op.getName() + "§b.", "", hover, "run_command", "/voteMute"));
                mvm.setAddVote(sr.getName(), vmt.getMuteId());
            }
        }
        return false;
    }
}
