package me.domos.kostkuj.models.craftCoinModel;

public enum CCTransactionTyp {
    WEB_VOUCHER(1),
    WEB_PURCHASE_ITEM(2),
    GAME_CONSOLE_BAN(3),
    GAME_EVENT_BREAK(4),
    GAME_KOLOSTESTI(5),
    RUCNE_DOMOS(6),
    WRITE_EVENT(7),
    GAME(8);

    private int id;

    CCTransactionTyp(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
