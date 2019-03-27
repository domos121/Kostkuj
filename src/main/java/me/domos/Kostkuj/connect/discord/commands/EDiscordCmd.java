package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public enum EDiscordCmd {

    HELP("/help", EKostkujRole.Moderator, new DiscorfCommandHelp(),"Zobrazí seznam dostupných příkazů."),
    LAG("/lag", EKostkujRole.Moderator, new DiscordCommandLag(),"Informace o stavu serveru."),
    LIST("/list", EKostkujRole.Moderator, new DiscordCommandList(),"Seznam hráčů na serveru."),
    BROADCAST("/bc", EKostkujRole.Hl_Moderator, new DiscordCommandBroadCast(),"Globální správa do chatu."),
    SAVE("/save", EKostkujRole.Moderator, new DiscordCommandSave(), "Uloží mapy."),
    RESTART("/restart", EKostkujRole.Hl_Moderator, new DiscordCommandRestart(), "Restart serveru."),
    TREST("/trest", EKostkujRole.Moderator, new DiscordCommandTrest(), "Banovací systém.");

    private String command;
    private EKostkujRole minRole;
    private IDiscordCommand discordCommand;
    private String description;

    EDiscordCmd(String command, EKostkujRole minRole, IDiscordCommand discordCommand, String description){
        this.command = command;
        this.minRole = minRole;
        this.discordCommand = discordCommand;
        this.description = description;
    }

    public static String getCommandList(EKostkujRole userRole){
        String helpList = "**HelpList**\n";
        List<EKostkujRole> accesRole = EKostkujRole.getAccesRoleList(userRole);
        for (EDiscordCmd cmds : EDiscordCmd.values()){
            if (accesRole.contains(cmds.minRole)){
                helpList = helpList + "**" + cmds.command  + "**: " + cmds.description + "\n";
            }
        }
        return helpList;
    }

    public static EDiscordCmd getCmd(String cmd){
        for (EDiscordCmd dcCmd : EDiscordCmd.values()){
            if (dcCmd.getCommand().equalsIgnoreCase(cmd)){
                return dcCmd;
            }
        }
        return EDiscordCmd.HELP;
    }

    public boolean executeCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role){
        if (!EKostkujRole.getAccesRoleList(role).contains(this.getMinRole())){
            DiscordConnect.sendPrivateMsg("**Pro příkaz '__" + args[0] + "__' nemáš dostaztečná oprávnění!**", event.getAuthor());
            return false;
        }
        String[] args2 = new String[args.length-1];
        for (int i = 0; args.length > i; i++){
            if (i != 0){
                args2[i-1] = args[i];
            }
        }
        return this.discordCommand.onCommand(args2, event, role);
    }

    public String getCommand(){
        return this.command;
    }

    public String getDescription(){
        return description;
    }

    public EKostkujRole getMinRole(){
        return minRole;
    }
}
