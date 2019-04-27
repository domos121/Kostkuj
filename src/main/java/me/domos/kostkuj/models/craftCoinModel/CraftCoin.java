package me.domos.kostkuj.models.craftCoinModel;

import me.domos.kostkuj.general.connect.mysql.CraftCoin.MCraftCoins;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CraftCoin {

    private int count;

    public CraftCoin(int count){
        this.count = count;
    }

    public CraftCoin(@NotNull Player player){
        this.count = new MCraftCoins().getCC(player.getName());
    }

    public CraftCoin(){
        this.count = 0;
    }

    public boolean updateCraftCoin(Player player, CCTransactionTyp typ) {
        if (new MCraftCoins().getCC(player.getName()) + (this.count) < 0){
            return false;
        }
        new MCraftCoins().setCC(player.getName(), this.count, typ.getId(), false);
        return true;
    }
}