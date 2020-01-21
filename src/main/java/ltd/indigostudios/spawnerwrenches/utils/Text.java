package ltd.indigostudios.spawnerwrenches.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class Text {

    /**
     * Format a string to contain Bukkit colour codes
     * @param msg The message to colour
     * @return The formatted colour coded string
     */
    public static String colour(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Format an array to contain Bukkit colour codes
     * @param msgs The array to colour
     * @return The formatted colour coded array
     */
    public static List<String> colourArray(List<String> msgs) {
        for (int x = 0; x < msgs.size(); x++) {
            msgs.set(x, colour(msgs.get(x)));
        }
        return msgs;
    }

    /**
     * Convert a bukkit string value to a readable string
     * @param old The old string value to convert
     * @return The readable string value
     */
    public static String convertUnformattedString(String old) {

        String convert = old.toLowerCase().replaceAll("\\_", " ");
        String newValue = "";

        boolean space = true;
        for (int i = 0; i < convert.length(); i++) {
            if (space) newValue += Character.toUpperCase(convert.charAt(i));
            else newValue += convert.charAt(i);
            space = convert.charAt(i) == ' ';
        }

        return newValue;
    }

}
