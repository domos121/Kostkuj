package by.domos.Kostkuj.server.chat.JSON;

public class CustomJsonBuilder {


    public String hoverText(String text, String text_color, String hover){
        //language=JSON
        String json = "{\n" + "  \"text\":\"#ar1#\",\n" + "  \"color\":\"#ar2#\",\n" + "  \"hoverEvent\": {\n" + "    \"action\":\"show_text\",\n" + "    \"value\":\"#ar3#\"\n" + "  }\n" + "}";
        String replace = json
                .replace("#ar1#", text)
                .replace("#ar2#", text_color)
                .replace("#ar3#", hover);
        return replace;
    }

    public String clickhoverText(String text, String text_color, String hover, String click_action, String commad){
        //language=JSON
        String json = "{\n" + "  \"text\":\"#ar1#\",\n" + "  \"color\":\"#ar2#\",\n" + "  \"hoverEvent\": {\n" + "    \"action\":\"show_text\",\n" + "    \"value\":\"#ar3#\"\n" + "  },\n" + "  \"clickEvent\": {\n" + "    \"action\": \"#ar4#\",\n" + "    \"value\": \"#ar5#\"\n" + "  }\n" + "}";
        String replace = json
                .replace("#ar1#", text)
                .replace("#ar2#", text_color)
                .replace("#ar3#", hover)
                .replace("#ar4#", click_action)
                .replace("#ar5#", commad);
        return replace;
    }

    public String vetaClickHoverText(String textvpredu, String color1, String text, String text_color, String hover, String click_action, String commad, String textvzadu, String color3){
        //language=JSON
        String json = "[\n" + "  {\n" + "    \"text\":\"#ar-1#\",\n" + "    \"color\":\"#ar-1.1#\"\n" + "  },\n" + "  {\n" + "  \"text\":\"#ar1#\",\n" + "  \"color\":\"#ar2#\",\n" + "  \"hoverEvent\": {\n" + "    \"action\":\"show_text\",\n" + "    \"value\":\"#ar3#\"\n" + "  },\n" + "  \"clickEvent\": {\n" + "    \"action\": \"#ar4#\",\n" + "    \"value\": \"#ar5#\"\n" + "  }\n" + "  },\n" + "  {\n" + "    \"text\":\"#ar6#\", \n" + "    \"color\":\"#ar6.1#\"\n" + "}\n" + "]  ";
        String replace = json
                .replace("#ar-1#", textvpredu)
                .replace("#ar-1.1#", color1)
                .replace("#ar1#", text)
                .replace("#ar2#", text_color)
                .replace("#ar3#", hover)
                .replace("#ar4#", click_action)
                .replace("#ar5#", commad)
                .replace("#ar6#", textvzadu)
                .replace("#ar6.1#", color3);
        return replace;
    }


}
