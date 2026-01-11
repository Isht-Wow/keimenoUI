package wow.ishit.v2_beta_keimenoUI;

/**
 * Enum representing ANSI console colors for text formatting in the console.
 * <p>
 * Each enum constant corresponds to an ANSI escape code that can be used to change
 * the color of text output in a console or terminal that supports ANSI codes.
 *
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
 */
public enum Colour {

    /** Resets the console color to default. */
    RESET("\u001B[0m"),
    /** Represents no color or no formatting. */
    NONE(""),

    /** Standard black color. */
    BLACK("\u001B[30m"),
    /** Standard red color. */
    RED("\u001B[31m"),
    /** Standard green color. */
    GREEN("\u001B[32m"),
    /** Standard yellow color. */
    YELLOW("\u001B[33m"),
    /** Standard blue color. */
    BLUE("\u001B[34m"),
    /** Standard magenta color. */
    MAGENTA("\u001B[35m"),
    /** Standard cyan color. */
    CYAN("\u001B[36m"),
    /** Standard white color. */
    WHITE("\u001B[37m"),

    /** Bright black (gray) color. */
    BRIGHT_BLACK("\u001B[90m"),
    /** Bright red color. */
    BRIGHT_RED("\u001B[91m"),
    /** Bright green color. */
    BRIGHT_GREEN("\u001B[92m"),
    /** Bright yellow color. */
    BRIGHT_YELLOW("\u001B[93m"),
    /** Bright blue color. */
    BRIGHT_BLUE("\u001B[94m"),
    /** Bright magenta color. */
    BRIGHT_MAGENTA("\u001B[95m"),
    /** Bright cyan color. */
    BRIGHT_CYAN("\u001B[96m"),
    /** Bright white color. */
    BRIGHT_WHITE("\u001B[97m");

    private final String code;

    Colour(String code) {
        this.code = code;
    }

    /**
     * Returns the ANSI escape code string associated with this color.
     *
     * @return the ANSI escape code string for this color
     */
    public String getCode() {
        return code;
    }
}
