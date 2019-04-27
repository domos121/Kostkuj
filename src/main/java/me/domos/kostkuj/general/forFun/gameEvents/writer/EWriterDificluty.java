package me.domos.kostkuj.general.forFun.gameEvents.writer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EWriterDificluty {

    VERY_EASY(2),
    EASY(3),
    NORMAL(4),
    HARD(5),
    VERY_HARD(6);

    private String name;
    private int toInt;

    EWriterDificluty(int toInt) {
        this.name = this.toString();
        this.toInt = toInt;
    }

    private static final List<EWriterDificluty> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EWriterDificluty randomDificluty() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static EWriterDificluty getDificlutyFromName(String name){
        for (EWriterDificluty dificluty : EWriterDificluty.values()) {
            if (dificluty.getName().equalsIgnoreCase(name)) {
                return dificluty;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int toInt() {
        return toInt;
    }
}
