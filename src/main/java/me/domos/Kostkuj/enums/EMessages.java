package me.domos.Kostkuj.enums;

import me.domos.Kostkuj.Main;
import org.bukkit.Bukkit;

public enum  EMessages {

    PLUGIN_PREFIX("§8[§cKostkuj§8]:§7 ", "pluginPrefix"),
    NO_PERMISSIONS("Nemáš patřičná oprávnění.", "noPermissions"),
    USER_ISNT_REGISTERED("&cHrac &6{player} &cnení zaregistrovan. Prosím registruj se na: &6www.kostkuj.cz", "userIsntRegistred"),
    USER_ISNT_AKTIV("&cHrac &6{player} &cnemá akivovaný účet. Prosíme o aktivaci pomocí e-mailové adresy, více info na: &6www.kostkuj.cz", "userIsntAktiv"),
    USER_ERROR("&cVyskytla se chyba! Prosím kontaktuj Administrátora &cna &bDiscordu &c! více info na: &6www.kostkuj.cz", "userError"),

    ;

    private String road;
    private String defaultValue;
    private String value;

    EMessages(String defaultValue, String road){
        this.defaultValue = defaultValue;
        this.road = road;
        this.value = Main.settings.getMessages().getString(road);
    }

    public String getRoad(){
        return this.road;
    }

    public static void checkConfig(){
        Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.defaultValue + "§6Checking message.yml");
        for (EMessages msg : EMessages.values()){
            if (!Main.settings.getMessages().isSet(msg.getRoad())){
                Main.settings.getMessages().set(msg.getRoad(), msg.getDefaultValue());
                Main.settings.saveMessages();
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.defaultValue + "Add " + msg.getRoad() + " to message.yml");
            }
        }
        Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.defaultValue + "§6Message.yml is ready");
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getValue() {
        if (this.value == null){
            return defaultValue;
        }
        return value;
    }
}
