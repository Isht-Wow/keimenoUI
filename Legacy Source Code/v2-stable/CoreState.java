package wow.ishit.v2_stable_keimenoUI;

/**
 * <p>
 * <b>CoreState</b> is the central configuration and shared mutable state object for the
 * entire text formatting, layout, colorization, buffering, ASCII art, audio, and rendering
 * system in BetterkeimenoUI.
 * </p>
 *
 * <p>
 * <b>Purpose:</b> This class encapsulates all runtime settings and options that control
 * how output is formatted, colored, buffered, rendered, and played back. It manages
 * output width, alignment, indentation, padding, border and separator styles, ANSI color
 * codes, ASCII art aspect and scaling ratios, audio playback and volume, buffer management,
 * and more.
 * </p>
 *
 * <p>
 * <b>Usage:</b>
 * <ul>
 *   <li>
 *     <b>Shared Reference:</b> A single CoreState instance is <i>shared by reference</i>
 *     among all major components (such as {@code Formatter}, {@code AudioManager},
 *     {@code AsciiArt}, etc.), ensuring that all formatting, audio, and rendering
 *     operations use a consistent and synchronized configuration.
 *   </li>
 *   <li>
 *     <b>Default Values and Reset:</b> All fields are initialized to sensible defaults
 *     (see the {@code DEFAULT_*} constants). The {@link #resetValues()} method can be
 *     called to restore all settings to these defaults, useful for clearing customizations
 *     between sections of output.
 *   </li>
 *   <li>
 *     <b>Buffering:</b> The internal {@link #buffer} accumulates all formatted output
 *     (including headers, ASCII art, tables, etc.) until it is printed or flushed.
 *   </li>
 * </ul>
 *
 * <p>
 * <b>Shared Reference Behavior:</b> All modifications to a CoreState instance immediately
 * affect subsequent formatting, rendering, and audio output across all components that
 * reference it. This ensures a single source of truth for runtime configuration.
 * </p>
 *
 * <p>
 * <b>Example Usage:</b>
 * <pre>{@code
 *   // Obtain the shared CoreState instance (via Settings or dependency injection)
 *   CoreState coreState = Settings.getCoreState();
 *   // Use a Formatter or other component that references the same CoreState
 *   Formatter.formatIntoBuffer("Welcome!");
 *   // Print the accumulated output
 *   coreState.print();
 *   // Reset formatting and state for the next use
 *   coreState.resetValues();
 * }</pre>
 *
 */
public class CoreState {

    /**
     * Internal buffer that accumulates all formatted output, including text, tables,
     * ASCII art, and other renderable content.
     * <p>
     * Usage: All formatting and rendering operations append their output to this buffer.
     * Methods such as {@link #print()}, {@link #flush()}, and {@link #clearingFlush()} print or clear its contents.
     * <p>
     * Default: Empty upon initialization or after {@link #clearBuffer()}.
     */
    StringBuilder buffer = new StringBuilder();

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

    /**
     * Width of the formatted output area, in characters.
     * <p>Controls line wrapping and the width of tables, boxes, and ASCII art.</p>
     * <b>Default:</b> {@link #DEFAULT_WIDTH} (100)
     */
    int width;

    /**
     * String used as the border around formatted output.
     * <p>Displayed at the left and right edges of formatted lines or boxes.</p>
     * <b>Default:</b> {@link #DEFAULT_BORDER} ("||")
     */
    String border;

    /**
     * Character used as filler for padding and empty space in formatted output.
     * <p>Fills the area between borders and content, or for padding lines.</p>
     * <b>Default:</b> {@link #DEFAULT_FILLER} (' ')
     */
    char filler;

    /**
     * Alignment setting for text formatting (e.g., LEFT, RIGHT, CENTER).
     * <b>Default:</b> {@link #DEFAULT_ALIGNMENT} (LEFT)
     */
    Alignment alignment;

    /**
     * Flag indicating if ANSI color support is enabled.
     * <p>When true, color codes are applied to output; when false, output is colorless.</p>
     * <b>Default:</b> {@link #DEFAULT_ANSI_SUPPORT} (true)
     */
    boolean ansiSupport;

    /**
     * ANSI color code used for the main content text.
     * <p>Controls the foreground color of text output (see {@link Colour}).</p>
     * <b>Default:</b> {@link #DEFAULT_CONTENT_COLOUR} (no color)
     */
    String contentColour;

    /**
     * ANSI color code used for filler characters (padding).
     * <p>Determines the color of filler regions around content.</p>
     * <b>Default:</b> {@link #DEFAULT_FILL_COLOUR} (no color)
     */
    String fillColour;

    // ------------------ AMATEUR SETTINGS ------------------

    /**
     * Character used to fill boxes in formatted output (e.g., table or box lines).
     * <b>Default:</b> {@link #DEFAULT_BOX_FILLER} ('-')
     */
    char boxFiller;

    /**
     * ANSI color code used for box filler characters.
     * <b>Default:</b> {@link #DEFAULT_BOX_FILLER_COLOUR} (no color)
     */
    String boxFillColour;

    /**
     * ANSI color code used for borders.
     * <b>Default:</b> {@link #DEFAULT_BORDER_COLOUR} (no color)
     */
    String borderColour;

    /**
     * Number of spaces to indent on the left side of each line.
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT} (0)
     */
    int leftIndent;

    /**
     * Number of spaces to indent on the right side of each line.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT} (0)
     */
    int rightIndent;

    /**
     * Character used for left indentation (if set, overrides space).
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT_CHAR} (null char)
     */
    char leftIndentChar;

    /**
     * Character used for right indentation (if set, overrides space).
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT_CHAR} (null char)
     */
    char rightIndentChar;

    /**
     * Aspect ratio for ASCII art images (width/height).
     * <p>Used to scale ASCII art to maintain proportions.</p>
     * <b>Default:</b> {@link #DEFAULT_ASPECT_RATIO} (0)
     */
    double aspectRatio;

    /**
     * Flag indicating if audio playback is enabled for output.
     * <b>Default:</b> {@link #DEFAULT_AUDIO_ENABLED} (true)
     */
    boolean audioEnabled;

    // ------------------ VETERAN SETTINGS ------------------

    /**
     * ANSI color code used for left indentation region.
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT_COLOUR} (no color)
     */
    String leftIndentColour;

    /**
     * ANSI color code used for right indentation region.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT_COLOUR} (no color)
     */
    String rightIndentColour;

    /**
     * Character used for left padding (between border and content).
     * <b>Default:</b> {@link #DEFAULT_LEFT_PADDING_CHAR} (null char)
     */
    char leftPaddingChar;

    /**
     * Character used for right padding (between content and border).
     * <b>Default:</b> {@link #DEFAULT_RIGHT_PADDING_CHAR} (null char)
     */
    char rightPaddingChar;

    /**
     * ANSI color code used for left padding region.
     * <b>Default:</b> {@link #DEFAULT_LEFT_PADDING_COLOUR} (no color)
     */
    String leftPaddingColour;

    /**
     * ANSI color code used for right padding region.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_PADDING_COLOUR} (no color)
     */
    String rightPaddingColour;

    /**
     * Flag indicating if text wrapping (word wrap) is enabled.
     * <p>When true, lines longer than {@link #warpWidth} are wrapped.</p>
     * <b>Default:</b> {@link #DEFAULT_WARP_ENABLED} (false)
     */
    boolean warpEnabled;

    /**
     * Width (in characters) to wrap text at if wrapping is enabled.
     * <b>Default:</b> {@link #DEFAULT_WRAP_WIDTH} (100)
     */
    int warpWidth; // width to warp if warpEnabled

    /**
     * Character height ratio for ASCII art scaling (height/width).
     * <p>Used to properly scale ASCII art vertically.</p>
     * <b>Default:</b> {@link #DEFAULT_CHAR_HEIGHT_RATIO} (2)
     */
    double charHeightRatio;

    /**
     * Volume level for audio playback (range: 0.0 to 1.0).
     * <b>Default:</b> {@link #DEFAULT_AUDIO_VOLUME} (1)
     */
    float audioVolume;

    /**
     * Flag indicating if audio playback should loop.
     * <b>Default:</b> {@link #DEFAULT_AUDIO_LOOP_ENABLED} (false)
     */
    boolean audioLoopEnabled;

    /**
     * Maximum frame rate for rendering dynamic output (e.g., animations).
     * <b>Default:</b> {@link #DEFAULT_MAX_FRAME_RATE} (5)
     */
    int maxFrameRate;

    /**
     * Character used as vertical separator in tables.
     * <b>Default:</b> {@link #DEFAULT_VERTICAL_SEPERATOR} ('|')
     */
    char verticalSeparator;

    /**
     * Character used as horizontal separator in tables.
     * <b>Default:</b> {@link #DEFAULT_HORIZONTAL_SEPERATOR} ('-')
     */
    char horizontalSeparator;

    /**
     * Character used as the corner piece in tables.
     * <b>Default:</b> {@link #DEFAULT_TABLE_CORNER_PIECE} ('+')
     */
    char tableCornerPiece;

    /**
     * Resets all CoreState properties to their default values.
     * <p>
     * This method restores the CoreState to its initial configuration as if a new instance
     * had just been created. All formatting, color, padding, border, separator, ASCII art,
     * audio, and rendering properties are set to their respective {@code DEFAULT_*} values.
     * </p>
     * <p>
     * <b>Typical use cases:</b>
     * <ul>
     *   <li>Clear all customizations before reusing the CoreState for a new output section.</li>
     *   <li>Ensure consistent settings after printing a section, to avoid unintended carryover.</li>
     * </ul>
     * <p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.resetValues(); // All settings revert to defaults
     * }</pre>
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
    static final int DEFAULT_WIDTH = 100;

    /** Default border string. */
    static final String DEFAULT_BORDER = "||";

    /** Default filler character. */
    static final char DEFAULT_FILLER = ' ';

    /** Default text alignment. */
    static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;

    /** Default ANSI support flag. */
    static final boolean DEFAULT_ANSI_SUPPORT = true;

    /** Default content colour code (no colour). */
    static final String DEFAULT_CONTENT_COLOUR = Colour.NONE.getCode();

    /** Default fill colour code (no colour). */
    static final String DEFAULT_FILL_COLOUR = Colour.NONE.getCode();

    /** Default box filler character. */
    static final char DEFAULT_BOX_FILLER = '-';

    /** Default box filler colour code (no colour). */
    static final String DEFAULT_BOX_FILLER_COLOUR = Colour.NONE.getCode();

    /** Default border colour code (no colour). */
    static final String DEFAULT_BORDER_COLOUR = Colour.NONE.getCode();

    /** Default left indentation amount. */
    static final int DEFAULT_LEFT_INDENT = 0;

    /** Default right indentation amount. */
    static final int DEFAULT_RIGHT_INDENT = 0;

    /** Default left indentation character (null char). */
    static final char DEFAULT_LEFT_INDENT_CHAR = 0;

    /** Default right indentation character (null char). */
    static final char DEFAULT_RIGHT_INDENT_CHAR = 0;

    /** Default ASCII art aspect ratio. */
    static final double DEFAULT_ASPECT_RATIO = 0;

    /** Default audio enabled flag. */
    static final boolean DEFAULT_AUDIO_ENABLED = true;

    /** Default left indentation colour code (no colour). */
    static final String DEFAULT_LEFT_INDENT_COLOUR = Colour.NONE.getCode();

    /** Default right indentation colour code (no colour). */
    static final String DEFAULT_RIGHT_INDENT_COLOUR = Colour.NONE.getCode();

    /** Default left padding character (null char). */
    static final char DEFAULT_LEFT_PADDING_CHAR = 0;

    /** Default right padding character (null char). */
    static final char DEFAULT_RIGHT_PADDING_CHAR = 0;

    /** Default left padding colour code (no colour). */
    static final String DEFAULT_LEFT_PADDING_COLOUR = Colour.NONE.getCode();

    /** Default right padding colour code (no colour). */
    static final String DEFAULT_RIGHT_PADDING_COLOUR = Colour.NONE.getCode();

    /** Default warp enabled flag. */
    static final boolean DEFAULT_WARP_ENABLED = false;

    /** Default wrap width for text wrapping. */
    static final int DEFAULT_WRAP_WIDTH = 100;

    /** Default character height ratio for ASCII art scaling. */
    static final double DEFAULT_CHAR_HEIGHT_RATIO = 2;

    /** Default audio volume level. */
    static final float DEFAULT_AUDIO_VOLUME = 1;

    /** Default audio loop enabled flag. */
    static final boolean DEFAULT_AUDIO_LOOP_ENABLED = false;

    /** Default maximum frame rate for rendering. */
    static final int  DEFAULT_MAX_FRAME_RATE = 5;

    /** Default vertical separator character for tables. */
    static final char DEFAULT_VERTICAL_SEPERATOR = '|';

    /** Default horizontal separator character for tables. */
    static final char DEFAULT_HORIZONTAL_SEPERATOR = '-';

    /** Default corner piece character for tables. */
    static final char DEFAULT_TABLE_CORNER_PIECE = '+';

    // ==================== TERMINAL METHODS ============================

    /**
     * Prints the current contents of the internal buffer to the console.
     * <p>
     * Outputs all accumulated formatted text, tables, and ASCII art stored in {@link #buffer}.
     * This method does <b>not</b> clear the buffer.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.print(); // Output contents of buffer to terminal
     * }</pre>
     */
    public void print() {
        System.out.print(buffer);
    }

    /**
     * Clears the terminal screen, prints the current buffer contents, then clears the buffer.
     * <p>
     * Equivalent to calling {@link #clearScreen()}, {@link #print()}, and {@link #clearBuffer()} in sequence.
     * Useful for presenting a fresh output view and discarding the buffer after display.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.clearingFlush();
     * }</pre>
     */
    public void clearingFlush() {
        clearScreen();
        print();
        clearBuffer();
    }

    /**
     * Rewrites the terminal screen (moves cursor to home), prints the buffer, and clears the buffer.
     * <p>
     * This method calls {@link #rewriteScreen()} to refresh the terminal display without scrolling,
     * then prints the current buffer contents, and finally clears the buffer.
     * Useful for updating dynamic content in-place.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.rewritingFlush();
     * }</pre>
     */
    public void rewritingFlush(){
        rewriteScreen();
        print();
        clearBuffer();
    }

    /**
     * Prints the current buffer contents and then clears the buffer.
     * <p>
     * Equivalent to calling {@link #print()} followed by {@link #clearBuffer()}.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.flush();
     * }</pre>
     */
    public void flush() {
        print();
        clearBuffer();
    }

    /**
     * Clears the internal buffer, removing all accumulated formatted content.
     * <p>
     * After calling this method, the buffer is empty and ready for new output.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.clearBuffer();
     * }</pre>
     */
    public void clearBuffer() {
        buffer = new StringBuilder();
    }

    /**
     * Pauses the current thread for the specified number of milliseconds.
     * <p>
     * Useful for creating timed pauses between output frames or sections.
     * </p>
     * @param milliseconds Number of milliseconds to delay.
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.delay(500); // Pause for half a second
     * }</pre>
     */
    public void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ======================== UTILS ========================
    /**
     * Calculates the visible length of a string, ignoring ANSI escape codes.
     * <p>
     * Useful for alignment, centering, and wrapping when colored text is present.
     * </p>
     * @param text The string to measure.
     * @return The number of visible (printable) characters.
     * <b>Example:</b>
     * <pre>{@code
     *   int len = coreState.visible("\u001B[31mRed Text\u001B[0m"); // returns 8
     * }</pre>
     */
    int visible(String text) {
        if (text == null)
            return 0;
        return text.replaceAll("\\u001B\\[[0-9;]*[A-Za-z]", "").length();
    }

    /**
     * Applies the given ANSI color code to the provided text if ANSI support is enabled.
     * <p>
     * If {@link #ansiSupport} is true and a valid color code is provided, the text
     * is wrapped with the color code and reset sequence.
     * Otherwise, the text is returned unmodified.
     * </p>
     * @param text The text to colorize.
     * @param colourCode The ANSI color code to apply (see {@link Colour}).
     * @return The colorized text if applicable; otherwise, the original text.
     * <b>Example:</b>
     * <pre>{@code
     *   String red = coreState.applyColour("Error", Colour.RED.getCode());
     * }</pre>
     */
    String applyColour(String text, String colourCode) {
        if ((colourCode != null && ansiSupport) || text != null || !text.isEmpty())
            return colourCode + text + Colour.RESET.getCode();
        return text;
    }

    /**
     * Applies the given ANSI color code to the provided text if ANSI support is enabled.
     * <p>
     * If {@link #ansiSupport} is true and a valid color code is provided, the text
     * is wrapped with the color code and reset sequence.
     * Otherwise, the text is returned unmodified.
     * </p>
     * @param text The text to colorize.
     * @param colour The ANSI color code to apply (see {@link Colour}).
     * @return The colorized text if applicable; otherwise, the original text.
     * <b>Example:</b>
     * <pre>{@code
     *   String red = coreState.applyColour("Error", Colour.RED);
     * }</pre>
     */
    public String applyColour(String text, Colour colour) {
        if ((colour != null && ansiSupport) || text != null || !text.isEmpty())
            return colour.getCode() + text + Colour.RESET.getCode();
        return text;
    }

    /**
     * Repeats the specified string a given number of times and returns the concatenated result.
     * <p>
     * Useful for generating repeated padding, separators, or box lines.
     * </p>
     * @param content The string to repeat.
     * @param count The number of times to repeat the string.
     * @return A new string consisting of {@code content} repeated {@code count} times.
     * <b>Example:</b>
     * <pre>{@code
     *   String line = coreState.repeat("-", 10); // "----------"
     * }</pre>
     */
    String repeat(String content, int count) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < count; i++){
            output.append(content);
        }
        return output.toString();
    }

    /**
     * Clears the terminal screen.
     * <p>
     * Uses platform-specific commands: ANSI escape codes for Unix/Linux/macOS, and
     * {@code cls} command for Windows. Falls back to printing newlines if needed.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.clearScreen();
     * }</pre>
     */
    public void clearScreen() {
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
     * Moves the terminal cursor to the home position (top-left) without fully clearing the screen.
     * <p>
     * This is a lighter alternative to {@link #clearScreen()}, useful for refreshing
     * dynamic output in place.
     * </p>
     * <b>Example:</b>
     * <pre>{@code
     *   coreState.rewriteScreen();
     * }</pre>
     */
    public void rewriteScreen(){
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
