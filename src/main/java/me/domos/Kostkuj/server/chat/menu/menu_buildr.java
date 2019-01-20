package me.domos.Kostkuj.server.chat.menu;

import me.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import org.bukkit.command.CommandSender;

public class menu_buildr {

    JsonSendMessage jsm = new JsonSendMessage();

    // String [0] [1]
    // String [radek] [sloupec]

    final String basejson = "[\n" +
            "  {\"text\":\" ===== \",\"color\":\"dark_gray\"},\n" +
            "  {\n" +
            "   \"text\": \"KOSKUJ: \", \n" +
            "    \"color\":\"gray\"\n" +
            "  },\n" +
            "  {\n" +
            "  \"text\":\"#jmenomenu#\",\n" +
            "    \"color\":\"red\"\n" +
            "  },\n" +
            "  {\n" +
            "  \"text\":\" =====\",\n" +
            "    \"color\":\"dark_gray\"\n" +
            "  },\n";
    private String json = basejson;

    public void menu(CommandSender sr, String[][] seznampoloek, String jmenomenu) {
        for (int i = 0; i < seznampoloek.length; i++) {
            if(sr.hasPermission(seznampoloek[i][2])){
                this.json = this.json + "\n" +
                        "{\n" +
                        "  \"text\": \"\\n§8 [§a✔§8] §7#prikaz#\",\n" +
                        "  \"hoverEvent\": {\n" +
                        "    \"action\": \"show_text\",\n" +
                        "    \"value\": \"§c#command#:\\n§7#popis#\"\n" +
                        "  },\n" +
                        "  \"clickEvent\": {\n" +
                        "    \"action\": \"#typcmd#\",\n" +
                        "    \"value\": \"#command#\"\n" +
                        "  }\n" +
                        "}\n" +
                        ",";
            } else {
                this.json = this.json + "\n" +
                        "{\n" +
                        "  \"text\": \"\\n§8 [§c✖§8] §7#prikaz#\",\n" +
                        "  \"hoverEvent\": {\n" +
                        "    \"action\": \"show_text\",\n" +
                        "    \"value\": \"§c#prikaz#:\\n§7#popis#\"\n" +
                        "  }\n" +
                        "}\n" +
                        ",";
            }
            this.json = this.json
                    .replace("#prikaz#", seznampoloek[i][0])
                    .replace("#popis#", seznampoloek[i][1])
                    .replace("#command#", seznampoloek[i][3])
                    .replace("#typcmd#", seznampoloek[i][4])
                    .replace("#jmenomenu#", jmenomenu)
                    .replace("#name#", sr.getName().trim());
        }
        this.json = this.json + "{\"text\":\"\"}" + "]";
        jsm.jsonBcKostkuj(sr, this.json);
        this.json = basejson;
    }
}
