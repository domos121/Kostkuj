package me.domos.kostkuj.bukkit.listeners.cmds.craftCoin;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MVoucher;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Voucher_Gift {

    MVoucher voucher = new MVoucher();
    SendSystem ss = new SendSystem();

    public void gift(CommandSender sr, String[] args){
        Player sender = Bukkit.getPlayer(sr.getName());
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        if (args.length == 1){
            ss.use(sr, ECmd.VOUCHER_GIFT.getCmd() + " <key>");
            return;
        }

        int cost = voucher.getVoucher(args[1]);

        if (cost <= 0){
            ss.info(sender, "Voucher neexistuje, nebo už je použitý.");
            return;
        }

        BaseComponent[] page = new ComponentBuilder("\n\n\n\n\n\n\n\n\n\n§2 >>§6VYZVEDNUTÍ ZDE§2<<")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/voucher use " + args[1]))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Kliknutím bude voucher použit a CraftCoiny připsány na váš virtuální účet.").create()))
                .create();
        BaseComponent[] pages = new ComponentBuilder("        §6§lVOUCHER\n")
                .append("§1§m===================")
                .append("\n§0§lHodnota:")
                .append(" " + cost + "CC")
                .append("\n§0§lKod: ")
                .append(args[1])
                .append(page)
                .create();
        bookMeta.spigot().addPage(pages);
        bookMeta.setTitle("Voucher na " + cost + "CC");
        bookMeta.setAuthor(sender.getDisplayName());

        book.setItemMeta(bookMeta);
        sender.getInventory().addItem(book);
    }
}
