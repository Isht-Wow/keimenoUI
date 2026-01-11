package wow.ishit.keimenoUI;
public class FormatterSettings {
    // ----- ANSI color codes -----
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[91m";
    public static final String GREEN = "\u001B[92m";
    public static final String YELLOW = "\u001B[93m";
    public static final String BLUE = "\u001B[94m";
    public static final String MAGENTA = "\u001B[95m";
    public static final String CYAN = "\u001B[96m";
    public static final String WHITE = "\u001B[97m";

    public static final int ERROR_THRESHOLD = 3;

    public static int defaultWidth = 80;
    public static String defaultBorder = "||";
    public static boolean ansiSupport = true;
    public static char defaultFiller = ' ';
    public static String defaultContentColour = "";
    public static String defaultFillerColour = "";

    public static double CharHeightRatio = 0.5;
    public static void setDefaultWidth(int width) {
        defaultWidth = width;
    }
    public static void setDefaultBorder(String border) {
        defaultBorder = border;
    }
    public static void setAnsiSupport(boolean enabled){
        ansiSupport = enabled;
    }
    public static void setDefaultFiller(char filler) {
        defaultFiller = filler;
    }
    public static void setDefaultContentColour(String colour) {
        defaultContentColour = colour;
    }
    public static void setDefaultFillerColour(String colour) {
        defaultFillerColour = colour;
    }
    public static void setAllDefaults(int width, String border, boolean ansiEnabled, char filler, String contentColour, String fillerColour) {
        defaultWidth = width;
        defaultBorder = border;
        ansiSupport = ansiEnabled;
        defaultFiller = filler;
        defaultContentColour = contentColour;
        defaultFillerColour = fillerColour;
    }
    public static void setCharHeightRatio(double ratio){
        CharHeightRatio = ratio;
    }
}
