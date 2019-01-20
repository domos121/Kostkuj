package by.domos.Kostkuj.server.commandRequests;

import org.bukkit.Bukkit;

public class CmdSender {

    public void sendCMD(int id, String cmd, String user, String settings) {
        if (settings == null){
            settings = "nic";
        }

        String[] nastaveni = settings.split(",");

        String command = cmd.
                replace("[user]", user);

        for (int i = 0; nastaveni.length > i; i++){
            command = command.replace("[args" + i + "]", nastaveni[i]);
        }


        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

}
