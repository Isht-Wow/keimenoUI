package wow.ishit.v2_beta_keimenoUI;

/**
 * <p>
 * CoreState serves as the central, mutable state and configuration object for the
 * entire text formatting, layout, colour, buffering, audio, and rendering system.
 * It encapsulates all runtime options and formatting properties that affect output,
 * including width, alignment, indentation, padding, borders, colour codes, ASCII
 * art aspect ratios, audio playback settings, buffer management, and more.
 * </p>
 *
 * <p>
 * <b>Usage and Sharing:</b>
 * <ul>
 *   <li>
 *     <b>Reference Sharing:</b> A single CoreState instance is shared <i>by reference</i>
 *     across all major components such as {@code Formatter}, {@code AudioManager},
 *     {@code AsciiArt}, and others, ensuring consistent configuration and coordinated
 *     changes throughout the formatting and rendering pipeline.
 *   </li>
 *   <li>
 *     <b>Default Values &amp; Reset:</b> Upon creation, all settings are initialized to
 *     sensible defaults (see constants such as {@code DEFAULT_WIDTH}, {@code DEFAULT_ALIGNMENT},
 *     etc.). The {@link #resetValues()} method restores all properties to these defaults,
 *     making it easy to clear customizations between outputs or sections.
 *   </li>
 *   <li>
 *     <b>Buffering:</b> The internal {@code StringBuilder buffer} accumulates all formatted
 *     output, which can be printed, flushed, or cleared using provided methods.
 *   </li>
 * </ul>
 *
 * <p>
 * <b>Typical Usage Example:</b>
 * <pre>{@code
 *   // Obtain CoreState via Settings (singleton or factory pattern)
 *   CoreState coreState = Settings.getCoreState();
 *   // Use Formatter or other components, which internally reference the same CoreState
 *   Formatter.format("Hello, World!", coreState);
 *   // Print the buffered output
 *   coreState.print();
 *   // Reset all formatting and state to defaults for the next section
 *   coreState.resetValues();
 * }</pre>
 *
 * <p>
 * <b>Note:</b> All mutations to this state immediately affect subsequent formatting,
 * rendering, and audio output, making it the single source of truth for runtime
 * configuration.
 * </p>
 */
public class CoreState {

    /**
     * Internal buffer that accumulates all formatted output.
     */
    protected StringBuilder buffer = new StringBuilder();

    /**
     * Constructs a new CoreState and initializes all settings to their default values.
     * <p>
     * This constructor sets up the state with sensible defaults for all configuration
     * options, ensuring a consistent starting point for formatting, buffering, and rendering.
     * </p>
     */
    public CoreState() {
        resetValues();
    }

    // ------------------ ROOKIE SETTINGS ------------------

    /** Width of the formatted output. */
    protected int width;

    /** String used as the border around formatted output. */
    protected String border;

    /** Character used as filler in formatting. */
    protected char filler;

    /** Alignment setting for text formatting. */
    protected Alignment alignment;

    /** Flag indicating if ANSI color support is enabled. */
    protected boolean ansiSupport;

    /** Colour code used for the main content text. */
    protected String contentColour;

    /** Colour code used for filler characters. */
    protected String fillColour;

    // ------------------ AMATEUR SETTINGS ------------------

    /** Character used to fill boxes in formatting. */
    protected char boxFiller;

    /** Colour code used for box filler characters. */
    protected String boxFillColour;

    /** Colour code used for borders. */
    protected String borderColour;

    /** Number of spaces to indent on the left side. */
    protected int leftIndent;

    /** Number of spaces to indent on the right side. */
    protected int rightIndent;

    /** Character used for left indentation. */
    protected char leftIndentChar;

    /** Character used for right indentation. */
    protected char rightIndentChar;

    /** Aspect ratio for ASCII art images. */
    protected double aspectRatio;

    /** Flag indicating if audio playback is enabled. */
    protected boolean audioEnabled;

    // ------------------ VETERAN SETTINGS ------------------

    /** Colour code used for left indentation. */
    protected String leftIndentColour;

    /** Colour code used for right indentation. */
    protected String rightIndentColour;

    /** Character used for left padding. */
    protected char leftPaddingChar;

    /** Character used for right padding. */
    protected char rightPaddingChar;

    /** Colour code used for left padding. */
    protected String leftPaddingColour;

    /** Colour code used for right padding. */
    protected String rightPaddingColour;

    /** Flag indicating if text wrapping is enabled. */
    protected boolean warpEnabled;

    /** Width to wrap text at if wrapping is enabled. */
    protected int warpWidth; // width to warp if warpEnabled

    /** Character height ratio used for ASCII art scaling. */
    protected double charHeightRatio;

    /** Volume level for audio playback (0.0 to 1.0). */
    protected float audioVolume;

    /** Flag indicating if audio looping is enabled. */
    protected boolean audioLoopEnabled;

    /** Maximum frame rate for rendering. */
    protected int maxFrameRate;

    /** Character used as vertical separator in tables. */
    protected char verticalSeparator;

    /** Character used as horizontal separator in tables. */
    protected char horizontalSeparator;

    /** Character used as the corner piece in tables. */
    protected char tableCornerPiece;

    /**
     * Resets all BufferState properties to their default values.
     *
     * <p>
     * This method restores the BufferState to its initial state as if a new instance
     * had been created. All configuration options—including width, alignment,
     * colors,
     * padding, borders, warp settings, and ASCII image aspect ratio—are set to
     * their
     * predefined defaults.
     * </p>
     *
     * <p>
     * Typical use cases:
     * </p>
     * <ul>
     * <li>Clearing any customizations before reusing the BufferState.</li>
     * <li>Resetting the BufferState after printing a section to ensure consistency
     * for the next section.</li>
     * </ul>
     */
    public void resetValues() {
        width = DEFAULT_WIDTH;
        border = DEFAULT_BORDER;
        filler = DEFAULT_FILLER;
        alignment = DEFAULT_ALIGNMENT;
        ansiSupport = DEFAULT_ANSI_SUPPORT;
        contentColour = DEFAULT_CONTENT_COLOUR;
        fillColour = DEFAULT_FILL_COLOUR;

        boxFiller = DEFAULT_BOX_FILLER;
        boxFillColour = DEFAULT_BOX_FILLER_COLOUR;
        borderColour = DEFAULT_BORDER_COLOUR;
        leftIndent = DEFAULT_LEFT_INDENT;
        rightIndent = DEFAULT_RIGHT_INDENT;
        leftIndentChar = DEFAULT_LEFT_INDENT_CHAR;
        rightIndentChar = DEFAULT_RIGHT_INDENT_CHAR;
        aspectRatio = DEFAULT_ASPECT_RATIO;
        audioEnabled = DEFAULT_AUDIO_ENABLED;

        leftIndentColour = DEFAULT_LEFT_INDENT_COLOUR;
        rightIndentColour = DEFAULT_RIGHT_INDENT_COLOUR;
        leftPaddingChar = DEFAULT_LEFT_PADDING_CHAR;
        rightPaddingChar = DEFAULT_RIGHT_PADDING_CHAR;
        leftPaddingColour = DEFAULT_LEFT_PADDING_COLOUR;
        rightPaddingColour = DEFAULT_RIGHT_PADDING_COLOUR;
        warpEnabled = DEFAULT_WARP_ENABLED;
        warpWidth = DEFAULT_WRAP_WIDTH;
        charHeightRatio = DEFAULT_CHAR_HEIGHT_RATIO;
        audioVolume = DEFAULT_AUDIO_VOLUME;
        audioLoopEnabled = DEFAULT_AUDIO_LOOP_ENABLED;
        maxFrameRate = DEFAULT_MAX_FRAME_RATE;
        verticalSeparator = DEFAULT_VERTICAL_SEPERATOR;
        horizontalSeparator = DEFAULT_HORIZONTAL_SEPERATOR;
        tableCornerPiece = DEFAULT_TABLE_CORNER_PIECE;
    }

    /** Default width of formatted output. */
    protected static final int DEFAULT_WIDTH = 100;

    /** Default border string. */
    protected static final String DEFAULT_BORDER = "||";

    /** Default filler character. */
    protected static final char DEFAULT_FILLER = ' ';

    /** Default text alignment. */
    protected static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;

    /** Default ANSI support flag. */
    protected static final boolean DEFAULT_ANSI_SUPPORT = true;

    /** Default content colour code (no colour). */
    protected static final String DEFAULT_CONTENT_COLOUR = Colour.NONE.getCode();

    /** Default fill colour code (no colour). */
    protected static final String DEFAULT_FILL_COLOUR = Colour.NONE.getCode();

    /** Default box filler character. */
    protected static final char DEFAULT_BOX_FILLER = '-';

    /** Default box filler colour code (no colour). */
    protected static final String DEFAULT_BOX_FILLER_COLOUR = Colour.NONE.getCode();

    /** Default border colour code (no colour). */
    protected static final String DEFAULT_BORDER_COLOUR = Colour.NONE.getCode();

    /** Default left indentation amount. */
    protected static final int DEFAULT_LEFT_INDENT = 0;

    /** Default right indentation amount. */
    protected static final int DEFAULT_RIGHT_INDENT = 0;

    /** Default left indentation character (null char). */
    protected static final char DEFAULT_LEFT_INDENT_CHAR = 0;

    /** Default right indentation character (null char). */
    protected static final char DEFAULT_RIGHT_INDENT_CHAR = 0;

    /** Default ASCII art aspect ratio. */
    protected static final double DEFAULT_ASPECT_RATIO = 0;

    /** Default audio enabled flag. */
    protected static final boolean DEFAULT_AUDIO_ENABLED = true;

    /** Default left indentation colour code (no colour). */
    protected static final String DEFAULT_LEFT_INDENT_COLOUR = Colour.NONE.getCode();

    /** Default right indentation colour code (no colour). */
    protected static final String DEFAULT_RIGHT_INDENT_COLOUR = Colour.NONE.getCode();

    /** Default left padding character (null char). */
    protected static final char DEFAULT_LEFT_PADDING_CHAR = 0;

    /** Default right padding character (null char). */
    protected static final char DEFAULT_RIGHT_PADDING_CHAR = 0;

    /** Default left padding colour code (no colour). */
    protected static final String DEFAULT_LEFT_PADDING_COLOUR = Colour.NONE.getCode();

    /** Default right padding colour code (no colour). */
    protected static final String DEFAULT_RIGHT_PADDING_COLOUR = Colour.NONE.getCode();

    /** Default warp enabled flag. */
    protected static final boolean DEFAULT_WARP_ENABLED = false;

    /** Default wrap width for text wrapping. */
    protected static final int DEFAULT_WRAP_WIDTH = 100;

    /** Default character height ratio for ASCII art scaling. */
    protected static final double DEFAULT_CHAR_HEIGHT_RATIO = 2;

    /** Default audio volume level. */
    protected static final float DEFAULT_AUDIO_VOLUME = 1;

    /** Default audio loop enabled flag. */
    protected static final boolean DEFAULT_AUDIO_LOOP_ENABLED = false;

    /** Default maximum frame rate for rendering. */
    protected static final int  DEFAULT_MAX_FRAME_RATE = 5;

    /** Default vertical separator character for tables. */
    protected static final char DEFAULT_VERTICAL_SEPERATOR = '|';

    /** Default horizontal separator character for tables. */
    protected static final char DEFAULT_HORIZONTAL_SEPERATOR = '-';

    /** Default corner piece character for tables. */
    protected static final char DEFAULT_TABLE_CORNER_PIECE = '+';

    // ==================== TERMINAL METHODS ============================

    /**
     * Prints the current contents of the internal buffer to the console.
     *
     * <p>
     * This method outputs all accumulated formatted text, headers, and
     * ASCII images stored in the internal {@link StringBuilder} buffer. It
     * does not modify the buffer.
     * </p>
     */
    public void print() {
        System.out.print(buffer);
    }

    /**
     * Clears the internal buffer and prints its contents.
     *
     * <p>
     * This method clears the internal buffer and then prints the contents
     * of the buffer {@link StringBuilder} to the console.
     * </p>
     */
    public void clearingFlush() {
        clearScreen();
        print();
        clearBuffer();
    }

    /**
     * Clears the screen by rewriting it and then prints and clears the buffer.
     *
     * <p>
     * This method is used to refresh the terminal screen before printing the current
     * buffer contents, providing a cleaner update without scrolling.
     * </p>
     */
    public void rewritingFlush(){
        rewriteScreen();
        print();
        clearBuffer();
    }

    /**
     * Prints the buffer contents and then clears the buffer.
     */
    public void flush() {
        print();
        clearBuffer();
    }

    /**
     * Clears the internal {@link StringBuilder} buffer, removing all accumulated
     * content.
     *
     * <p>
     * After calling this method, the buffer will be empty, and subsequent
     * formatting operations will start fresh.
     * </p>
     */
    public void clearBuffer() {
        buffer = new StringBuilder();
    }

    // ======================== UTILS ========================
    /**
     * Calculates the visible length of a string, ignoring ANSI escape codes.
     *
     * <p>
     * This is useful for alignment and wrapping when colored text is present.
     * </p>
     *
     * @param text the string to measure
     * @return the number of visible characters in the text
     */
    protected int visible(String text) {
        if (text == null)
            return 0;
        return text.replaceAll("\\u001B\\[[0-9;]*[A-Za-z]", "").length();
    }

    /**
     * Applies the given ANSI color code to the provided text if ANSI support is
     * enabled.
     *
     * <p>
     * If ANSI support is enabled and a valid color code is provided, the text will
     * be wrapped
     * with the color code and reset sequence. Otherwise, the text is returned
     * unmodified.
     * </p>
     *
     * @param text       the text to colorize
     * @param colourCode the ANSI color code to apply
     * @return the colorized text if applicable, otherwise the original text
     */
    protected String applyColour(String text, String colourCode) {
        if ((colourCode != null && ansiSupport) || text != null || !text.isEmpty())
            return colourCode + text + Colour.RESET.getCode();
        return text;
    }

    /**
     * Repeats the specified string a given number of times and returns the concatenated result.
     *
     * @param content the string to repeat
     * @param count the number of times to repeat the string
     * @return a new string consisting of {@code content} repeated {@code count} times
     */
    protected String repeat(String content, int count) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < count; i++){
            output.append(content);
        }
        return output.toString();
    }

    /**
     * Clears the terminal screen.
     * Works on macOS/Linux (ANSI) and Windows (cmd).
     */
    protected void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix/Linux/macOS
                System.out.print("\033[H\033[2J"); // ANSI escape code
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print many newlines
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }
    /**
     * Clears the terminal screen by moving the cursor to the home position.
     *
     * <p>
     * This method is a lighter alternative to clearScreen(), primarily moving the
     * cursor to the top-left without clearing the entire screen buffer.
     * </p>
     */
    protected void rewriteScreen(){
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix/Linux/macOS
                System.out.print("\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print many newlines
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }
}
