package by.domos.Kostkuj.server.listener.cmds;

import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadCast implements CommandExecutor {

    SendSystem ss = new SendSystem();

    private String msg = "";

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String commandlabel, String[] args) {
        if (!sr.hasPermission("kostkuj.broadcast")){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.info(sr, "Use: /bc <msg>");
            return true;
        }

        for (int i = 0; i < args.length; i++)
            this.msg = this.msg + args[i] + " ";
        this.msg = this.msg.trim();

        ss.broadCast(this.msg);

        this.msg = "";
        return false;
    }
}
