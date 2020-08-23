package me.domos.kostkuj.general.forFun.gameEvents.writer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EWriterTyps {

    MATH(),
    WRITE_TEXT();
    //QESTIONS()

    private String name;

    EWriterTyps(){
        this.name = this.toString();
    }

    private static final List<EWriterTyps> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EWriterTyps randomQestionsTyps()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static EWriterTyps getDificlutyFromName(String name){
        for (EWriterTyps dificluty : EWriterTyps.values()) {
            if (dificluty.getName().equalsIgnoreCase(name)) {
                return dificluty;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
