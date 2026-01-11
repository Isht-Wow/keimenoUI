package wow.ishit.v2_beta_keimenoUI;

/**
 * Enum representing ANSI console colors for text formatting in the console.
 * <p>
 * Each enum constant corresponds to an ANSI escape code that can be used to change
 * the color of text output in a console or terminal that supports ANSI codes.
 * </p>
 * <p>
 * The enum includes standard colors (BLACK, RED, GREEN, etc.), bright variants 
 * (BRIGHT_BLACK, BRIGHT_RED, etc.), as well as special constants for resetting 
 * the color (RESET) and no color (NONE).
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * System.out.println(Colour.RED.getCode() + "This text will be red." + Colour.RESET.getCode());
 * }
 * </pre>
 * This will print the text "This text will be red." in red color, then reset the color back to default.
 * </p>
 */
public enum Colour {

    RESET("\u001B[0m"),
    NONE(""),

    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BRIGHT_BLACK("\u001B[90m"),
    BRIGHT_RED("\u001B[91m"),
    BRIGHT_GREEN("\u001B[92m"),
    BRIGHT_YELLOW("\u001B[93m"),
    BRIGHT_BLUE("\u001B[94m"),
    BRIGHT_MAGENTA("\u001B[95m"),
    BRIGHT_CYAN("\u001B[96m"),
    BRIGHT_WHITE("\u001B[97m");

    private final String code;

    Colour(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
