package wow.ishit.v2_beta_keimenoUI;

/**
 * <p>
 * <b>CoreState</b> is the central configuration and shared mutable state object
 * for the
 * entire text formatting, layout, colorization, buffering, ASCII art, audio,
 * and rendering
 * system in BetterkeimenoUI.
 * </p>
 *
 * <p>
 * <b>Purpose:</b> This class encapsulates all runtime settings and options that
 * control
 * how output is formatted, colored, buffered, rendered, and played back. It
 * manages
 * output width, alignment, indentation, padding, border and separator styles,
 * ANSI color
 * codes, ASCII art aspect and scaling ratios, audio playback and volume, buffer
 * management,
 * and more.
 * </p>
 *
 * <p>
 * <b>Usage:</b>
 * <ul>
 * <li>
 * <b>Shared Reference:</b> A single CoreState instance is <i>shared by
 * reference</i>
 * among all major components (such as {@code Formatter}, {@code AudioManager},
 * {@code AsciiArt}, etc.), ensuring that all formatting, audio, and rendering
 * operations use a consistent and synchronized configuration.
 * </li>
 * <li>
 * <b>Default Values and Reset:</b> All fields are initialized to sensible
 * defaults
 * (see the {@code DEFAULT_*} constants). The {@link #resetValues()} method can
 * be
 * called to restore all settings to these defaults, useful for clearing
 * customizations
 * between sections of output.
 * </li>
 * <li>
 * <b>Buffering:</b> The internal {@link #buffer} accumulates all formatted
 * output
 * (including headers, ASCII art, tables, etc.) until it is printed or flushed.
 * </li>
 * </ul>
 *
 * <p>
 * <b>Shared Reference Behavior:</b> All modifications to a CoreState instance
 * immediately
 * affect subsequent formatting, rendering, and audio output across all
 * components that
 * reference it. This ensures a single source of truth for runtime
 * configuration.
 * </p>
 *
 * <p>
 * <b>Example Usage:</b>
 * 
 * <pre>{@code
 * // Obtain the shared CoreState instance (via Settings or dependency
 * // injection)
 * CoreState coreState = Settings.getCoreState();
 * // Use a Formatter or other component that references the same CoreState
 * Formatter.formatIntoBuffer("Welcome!");
 * // Print the accumulated output
 * coreState.print();
 * // Reset formatting and state for the next use
 * coreState.resetValues();
 * }</pre>
 *
 */
public class CoreState {

    /**
     * Internal buffer that accumulates all formatted output, including text,
     * tables,
     * ASCII art, and other renderable content.
     * <p>
     * Usage: All formatting and rendering operations append their output to this
     * buffer.
     * Methods such as {@link #print()}, {@link #flush()}, and
     * {@link #clearingFlush()} print or clear its contents.
     * <p>
     * Default: Empty upon initialization or after {@link #clearBuffer()}.
     */
    StringBuilder buffer = new StringBuilder();

    /**
     * Constructs a new CoreState and initializes all settings to their default
     * values.
     * <p>
     * This constructor sets up the state with sensible defaults for all
     * configuration
     * options, ensuring a consistent starting point for formatting, buffering, and
     * rendering.
     * </p>
     */
    CoreState() {
        resetValues();
    }

    // Content and Filler
    /**
     * ANSI color code used for the main content text.
     * <p>
     * Controls the foreground color of text output (see {@link Colour}).
     * </p>
     * <b>Default:</b> {@link #DEFAULT_CONTENT_COLOUR} (no color)
     */
    Colour contentColour;

    /**
     * ANSI color code used for filler characters (padding).
     * <p>
     * Determines the color of filler regions around content.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_FILL_COLOUR} (no color)
     */
    Colour fillColour;

    /**
     * Character used as filler for padding and empty space in formatted output.
     * <p>
     * Fills the area between borders and content, or for padding lines.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_FILLER} (' ')
     */
    char filler;

    /**
     * Background color code used for the main content text.
     * <p>
     * Controls the background color of text output (see {@link BackgroundColour}).
     * </p>
     * <b>Default:</b> {@link #DEFAULT_CONTENT_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour contentBackgroundColour;

    /**
     * Background color code used for the filler characters.
     * <p>
     * Sets the background color behind filler (padding) characters in formatted output.
     * Use this to visually distinguish the padded regions from the main content or border.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_FILL_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour fillBackgroundColour;

    /**
     * String used as the border around formatted output.
     * <p>
     * Displayed at the left and right edges of formatted lines or boxes.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_BORDER} ("||")
     */
    String border;

    /**
     * ANSI color code used for borders.
     * <b>Default:</b> {@link #DEFAULT_BORDER_COLOUR} (no color)
     */
    Colour borderColour;

    /**
     * Background color code used for border region.
     * <p>
     * Sets the background color behind the border string in formatted output.
     * Useful for highlighting or visually separating the border from other regions.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_BORDER_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour borderBackgroundColour;


    // Basic Formatter Variables

    /**
     * Width of the formatted output area, in characters.
     * <p>
     * Controls line wrapping and the width of tables, boxes, and ASCII art.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_WIDTH} (100)
     */
    int width;
    /**
     * Alignment setting for text formatting (e.g., LEFT, RIGHT, CENTER).
     * <b>Default:</b> {@link #DEFAULT_ALIGNMENT} (LEFT)
     */
    Alignment alignment;

    /**
     * Flag indicating if ANSI color support is enabled.
     * <p>
     * When true, color codes are applied to output; when false, output is
     * colorless.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_ANSI_SUPPORT} (true)
     */
    boolean ansiSupport;  

    /**
     * Flag indicating if text wrapping (word wrap) is enabled.
     * <p>
     * When true, lines longer than {@link #warpWidth} are wrapped.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_WARP_ENABLED} (false)
     */
    boolean warpEnabled;

    /**
     * Width (in characters) to wrap text at if wrapping is enabled.
     * <b>Default:</b> {@link #DEFAULT_WRAP_WIDTH} (100)
     */
    int warpWidth;
    

    //Indentation and Padding
    
    /**
     * Number of spaces to indent on the left side of each line.
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT} (0)
     */
    int leftIndent;

    /**
     * Character used for left indentation (if set, overrides space).
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT_CHAR} (null char)
     */
    char leftIndentChar;

    /**
     * ANSI color code used for left indentation region.
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT_COLOUR} (no color)
     */
    Colour leftIndentColour;

    /**
     * Background color code used for left indentation region.
     * <p>
     * Sets the background color for the left indentation area of each line.
     * Useful for visually separating indented regions.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_LEFT_INDENT_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour leftIndentBackgroundColour;

    /**
     * Number of spaces to indent on the right side of each line.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT} (0)
     */
    int rightIndent;

    /**
     * Character used for right indentation (if set, overrides space).
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT_CHAR} (null char)
     */
    char rightIndentChar;

    /**
     * ANSI color code used for right indentation region.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT_COLOUR} (no color)
     */
    Colour rightIndentColour;

    /**
     * Background color code used for right indentation region.
     * <p>
     * Sets the background color for the right indentation area of each line.
     * Useful for visually separating indented regions.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_RIGHT_INDENT_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour rightIndentBackgroundColour;

    /**
     * Character used for left padding (between border and content).
     * <b>Default:</b> {@link #DEFAULT_LEFT_PADDING_CHAR} (null char)
     */
    char leftPaddingChar;

    /**
     * ANSI color code used for left padding region.
     * <b>Default:</b> {@link #DEFAULT_LEFT_PADDING_COLOUR} (no color)
     */
    Colour leftPaddingColour;

    /**
     * Background color code used for left padding region.
     * <p>
     * Sets the background color for the left padding area (between the border and content).
     * Useful for visually distinguishing the padding area in output.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_LEFT_PADDING_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour leftPaddingBackgroundColour;

    /**
     * Character used for right padding (between content and border).
     * <b>Default:</b> {@link #DEFAULT_RIGHT_PADDING_CHAR} (null char)
     */
    char rightPaddingChar;

    /**
     * ANSI color code used for right padding region.
     * <b>Default:</b> {@link #DEFAULT_RIGHT_PADDING_COLOUR} (no color)
     */
    Colour rightPaddingColour;

    /**
     * Background color code used for right padding region.
     * <p>
     * Sets the background color for the right padding area (between the content and border).
     * Useful for visually distinguishing the padding area in output.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_RIGHT_PADDING_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour rightPaddingBackgroundColour;


    // Headers

    /**
     * Character used to fill boxes in formatted output (e.g., table or box lines).
     * <b>Default:</b> {@link #DEFAULT_BOX_FILLER} ('-')
     */
    char headerBoxFiller;

    /**
     * ANSI color code used for header box filler characters.
     * <b>Default:</b> {@link #DEFAULT_HEADER_BOX_FILL_COLOUR} (no color)
     */
    Colour headerBoxFillColour;

    /**
     * Background color code used for header box filler characters.
     * <p>
     * Sets the background color for the filler characters used in header boxes or lines.
     * Useful for visually separating or highlighting header sections.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_HEADER_BOX_FILL_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour headerBoxFillBackgroundColour;


    //Tables

    /**
     * Character used as vertical separator in tables.
     * <p>
     * Used to divide columns in table output.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_VERTICAL_SEPERATOR} ('|')
     */
    char verticalSeparator;

    /**
     * ANSI color code used for vertical separator characters in tables.
     * <p>
     * Controls the color of vertical lines between columns.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_VERTICAL_SEPARATOR_COLOUR} (no color)
     */
    Colour verticalSeparatorColour;

    /**
     * Background color code used for vertical separator characters in tables.
     * <p>
     * Sets the background color behind vertical separator characters in tables.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_VERTICAL_SEPARATOR_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour verticalSeparatorBackgroundColour;

    /**
     * Character used as horizontal separator in tables.
     * <p>
     * Used to divide rows in table output.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_HORIZONTAL_SEPERATOR} ('-')
     */
    char horizontalSeparator;

    /**
     * ANSI color code used for horizontal separator characters in tables.
     * <p>
     * Controls the color of horizontal lines between rows.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_HORIZONTAL_SEPARATOR_COLOUR} (no color)
     */
    Colour horizontalSeparatorColour;

    /**
     * Background color code used for horizontal separator characters in tables.
     * <p>
     * Sets the background color behind horizontal separator characters in tables.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_HORIZONTAL_SEPARATOR_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour horizontalSeparatoBackgroundColour;

    /**
     * Character used as the corner piece in tables.
     * <p>
     * Used at the intersection points of horizontal and vertical separators in tables.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_TABLE_CORNER_PIECE} ('+')
     */
    char tableCornerPiece;

    /**
     * ANSI color code used for table corner piece characters.
     * <p>
     * Controls the color of the intersection points in table borders.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_TABLE_CORNER_PIECE_COLOUR} (no color)
     */
    Colour tableCornerPieceColour;

    /**
     * Background color code used for table corner piece characters.
     * <p>
     * Sets the background color for intersection points in table borders.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_TABLE_CORNER_PIECE_BACKGROUND_COLOUR} (no background color)
     */
    BackgroundColour tableCornerPieceBackgroundColour;

    // Video and Image Variables

    /**
     * Aspect ratio for ASCII art images (width/height).
     * <p>
     * Used to scale ASCII art to maintain proportions.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_ASPECT_RATIO} (0)
     */
    double aspectRatio;

    /**
     * Character height ratio for ASCII art scaling (height/width).
     * <p>
     * Used to properly scale ASCII art vertically.
     * </p>
     * <b>Default:</b> {@link #DEFAULT_CHAR_HEIGHT_RATIO} (2)
     */
    double charHeightRatio;

    /**
     * Flag indicating if audio playback is enabled for video output.
     * <b>Default:</b> {@link #DEFAULT_AUDIO_ENABLED} (true)
     */
    boolean audioEnabled;

    /**
     * Maximum frame rate for rendering dynamic output (e.g., animations).
     * <b>Default:</b> {@link #DEFAULT_MAX_FRAME_RATE} (5)
     */
    int maxFrameRate;


    //Audio Variables

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

    // 1. Content and Filler
    /** Default content colour code (no colour). Used for main content text. */
    static final Colour DEFAULT_CONTENT_COLOUR = Colour.NONE;
    /** Default content background colour code (no colour). Used for main content background. */
    static final BackgroundColour DEFAULT_CONTENT_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default filler character for padding and empty space. */
    static final char DEFAULT_FILLER = ' ';
    /** Default fill colour code (no colour). Used for filler regions. */
    static final Colour DEFAULT_FILL_COLOUR = Colour.NONE;
    /** Default fill background colour code (no colour). Used for filler background. */
    static final BackgroundColour DEFAULT_FILL_BACKGROUND_COLOUR = BackgroundColour.NONE;

    // 2. Border
    /** Default border string (surrounds formatted output). */
    static final String DEFAULT_BORDER = "||";
    /** Default border colour code (no colour). Used for border text. */
    static final Colour DEFAULT_BORDER_COLOUR = Colour.NONE;
    /** Default border background colour code (no colour). Used for border background. */
    static final BackgroundColour DEFAULT_BORDER_BACKGROUND_COLOUR = BackgroundColour.NONE;

    // 3. Basic Formatter Variables
    /** Default width of formatted output. */
    static final int DEFAULT_WIDTH = 100;
    /** Default text alignment. */
    static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;
    /** Default ANSI support flag. If true, ANSI codes are used. */
    static final boolean DEFAULT_ANSI_SUPPORT = true;
    /** Default warp enabled flag. If true, text wrapping is enabled. */
    static final boolean DEFAULT_WARP_ENABLED = false;
    /** Default wrap width for text wrapping. */
    static final int DEFAULT_WRAP_WIDTH = 100;

    // 4. Indentation and Padding
    /** Default left indentation amount (spaces). */
    static final int DEFAULT_LEFT_INDENT = 0;
    /** Default right indentation amount (spaces). */
    static final int DEFAULT_RIGHT_INDENT = 0;
    /** Default left indentation character (null char for space). */
    static final char DEFAULT_LEFT_INDENT_CHAR = 0;
    /** Default right indentation character (null char for space). */
    static final char DEFAULT_RIGHT_INDENT_CHAR = 0;
    /** Default left indentation colour code (no colour). */
    static final Colour DEFAULT_LEFT_INDENT_COLOUR = Colour.NONE;
    /** Default left indentation background colour code (no colour). */
    static final BackgroundColour DEFAULT_LEFT_INDENT_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default right indentation colour code (no colour). */
    static final Colour DEFAULT_RIGHT_INDENT_COLOUR = Colour.NONE;
    /** Default right indentation background colour code (no colour). */
    static final BackgroundColour DEFAULT_RIGHT_INDENT_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default left padding character (null char for space). */
    static final char DEFAULT_LEFT_PADDING_CHAR = 0;
    /** Default right padding character (null char for space). */
    static final char DEFAULT_RIGHT_PADDING_CHAR = 0;
    /** Default left padding colour code (no colour). */
    static final Colour DEFAULT_LEFT_PADDING_COLOUR = Colour.NONE;
    /** Default left padding background colour code (no colour). */
    static final BackgroundColour DEFAULT_LEFT_PADDING_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default right padding colour code (no colour). */
    static final Colour DEFAULT_RIGHT_PADDING_COLOUR = Colour.NONE;
    /** Default right padding background colour code (no colour). */
    static final BackgroundColour DEFAULT_RIGHT_PADDING_BACKGROUND_COLOUR = BackgroundColour.NONE;

    // 5. Headers
    /** Default header box filler character (used for header/box lines). */
    static final char DEFAULT_HEADER_BOX_FILLER = '-';
    /** Default header box filler colour code (no colour). */
    static final Colour DEFAULT_HEADER_BOX_FILL_COLOUR = Colour.NONE;
    /** Default header box filler background colour code (no colour). */
    static final BackgroundColour DEFAULT_HEADER_BOX_FILL_BACKGROUND_COLOUR = BackgroundColour.NONE;

    // 6. Tables
    /** Default vertical separator character for tables. */
    static final char DEFAULT_VERTICAL_SEPARATOR = '|';
    static final Colour DEFAULT_VERTICAL_SEPARATOR_COLOUR = Colour.NONE;
    static final BackgroundColour DEFAULT_VERTICAL_SEPARATOR_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default horizontal separator character for tables. */
    static final char DEFAULT_HORIZONTAL_SEPARATOR = '-';
    /**
     * Default colour for horizontal separator characters in tables (no colour).
     * Used to colourize horizontal lines in table output.
     */
    static final Colour DEFAULT_HORIZONTAL_SEPARATOR_COLOUR = Colour.NONE;
    /**
     * Default background colour for horizontal separator characters in tables (no colour).
     * Used to colourize the background of horizontal separator lines in tables.
     */
    static final BackgroundColour DEFAULT_HORIZONTAL_SEPARATOR_BACKGROUND_COLOUR = BackgroundColour.NONE;
    /** Default corner piece character for tables. */
    static final char DEFAULT_TABLE_CORNER_PIECE = '+';
    /**
     * Default colour for table corner piece characters (no colour).
     * Used to colourize the intersection points in table borders.
     */
    static final Colour DEFAULT_TABLE_CORNER_PIECE_COLOUR = Colour.NONE;
    /**
     * Default background colour for table corner piece characters (no colour).
     * Used for the background of intersection points in table borders.
     */
    static final BackgroundColour DEFAULT_TABLE_CORNER_PIECE_BACKGROUND_COLOUR = BackgroundColour.NONE;

    // 7. Video/ASCII art
    /** Default ASCII art aspect ratio (width/height). */
    static final double DEFAULT_ASPECT_RATIO = 0;
    /** Default character height ratio for ASCII art scaling (height/width). */
    static final double DEFAULT_CHAR_HEIGHT_RATIO = 2;
    /** Default maximum frame rate for rendering (frames per second). */
    static final int DEFAULT_MAX_FRAME_RATE = 5;
    /** Default audio enabled flag. If true, audio playback for videos is enabled. */
    static final boolean DEFAULT_AUDIO_ENABLED = true;

    // 8. Audio
    /** Default audio volume level (0.0 to 1.0). */
    static final float DEFAULT_AUDIO_VOLUME = 1;
    /** Default audio loop enabled flag. If true, audio will loop. */
    static final boolean DEFAULT_AUDIO_LOOP_ENABLED = false;
    
    /**
     * Resets all CoreState properties to their default values.
     * <p>
     * This method restores the CoreState to its initial configuration as if a new
     * instance
     * had just been created. All formatting, color, padding, border, separator,
     * ASCII art,
     * audio, and rendering properties are set to their respective {@code DEFAULT_*}
     * values.
     * </p>
     * <p>
     * <b>Typical use cases:</b>
     * <ul>
     * <li>Clear all customizations before reusing the CoreState for a new output
     * section.</li>
     * <li>Ensure consistent settings after printing a section, to avoid unintended
     * carryover.</li>
     * </ul>
     * <p>
     * <b>Example:</b>
     * 
     * <pre>{@code
     * coreState.resetValues(); // All settings revert to defaults
     * }</pre>
     */
void resetValues() {
    // 1. Content and Filler
    width = DEFAULT_WIDTH;
    alignment = DEFAULT_ALIGNMENT;
    ansiSupport = DEFAULT_ANSI_SUPPORT;

    contentColour = DEFAULT_CONTENT_COLOUR;
    contentBackgroundColour = DEFAULT_CONTENT_BACKGROUND_COLOUR;

    filler = DEFAULT_FILLER;
    fillColour = DEFAULT_FILL_COLOUR;
    fillBackgroundColour = DEFAULT_FILL_BACKGROUND_COLOUR;

    // 2. Border
    border = DEFAULT_BORDER;
    borderColour = DEFAULT_BORDER_COLOUR;
    borderBackgroundColour = DEFAULT_BORDER_BACKGROUND_COLOUR;

    // 3. Wrapping
    warpEnabled = DEFAULT_WARP_ENABLED;
    warpWidth = DEFAULT_WRAP_WIDTH;

    // 4. Indentation
    leftIndent = DEFAULT_LEFT_INDENT;
    leftIndentChar = DEFAULT_LEFT_INDENT_CHAR;
    leftIndentColour = DEFAULT_LEFT_INDENT_COLOUR;
    leftIndentBackgroundColour = DEFAULT_LEFT_INDENT_BACKGROUND_COLOUR;

    rightIndent = DEFAULT_RIGHT_INDENT;
    rightIndentChar = DEFAULT_RIGHT_INDENT_CHAR;
    rightIndentColour = DEFAULT_RIGHT_INDENT_COLOUR;
    rightIndentBackgroundColour = DEFAULT_RIGHT_INDENT_BACKGROUND_COLOUR;

    // 5. Padding
    leftPaddingChar = DEFAULT_LEFT_PADDING_CHAR;
    leftPaddingColour = DEFAULT_LEFT_PADDING_COLOUR;
    leftPaddingBackgroundColour = DEFAULT_LEFT_PADDING_BACKGROUND_COLOUR;

    rightPaddingChar = DEFAULT_RIGHT_PADDING_CHAR;
    rightPaddingColour = DEFAULT_RIGHT_PADDING_COLOUR;
    rightPaddingBackgroundColour = DEFAULT_RIGHT_PADDING_BACKGROUND_COLOUR;

    // 6. Header Box
    headerBoxFiller = DEFAULT_HEADER_BOX_FILLER;
    headerBoxFillColour = DEFAULT_HEADER_BOX_FILL_COLOUR;
    headerBoxFillBackgroundColour = DEFAULT_HEADER_BOX_FILL_BACKGROUND_COLOUR;

    // 7. Tables
    verticalSeparator = DEFAULT_VERTICAL_SEPARATOR;
    verticalSeparatorColour = DEFAULT_VERTICAL_SEPARATOR_COLOUR;
    verticalSeparatorBackgroundColour = DEFAULT_VERTICAL_SEPARATOR_BACKGROUND_COLOUR;

    horizontalSeparator = DEFAULT_HORIZONTAL_SEPARATOR;
    horizontalSeparatorColour = DEFAULT_HORIZONTAL_SEPARATOR_COLOUR;
    horizontalSeparatoBackgroundColour = DEFAULT_HORIZONTAL_SEPARATOR_BACKGROUND_COLOUR;

    tableCornerPiece = DEFAULT_TABLE_CORNER_PIECE;
    tableCornerPieceColour = DEFAULT_TABLE_CORNER_PIECE_COLOUR;
    tableCornerPieceBackgroundColour = DEFAULT_TABLE_CORNER_PIECE_BACKGROUND_COLOUR;

    // 8. Video / ASCII
    aspectRatio = DEFAULT_ASPECT_RATIO;
    charHeightRatio = DEFAULT_CHAR_HEIGHT_RATIO;
    maxFrameRate = DEFAULT_MAX_FRAME_RATE;
    audioEnabled = DEFAULT_AUDIO_ENABLED;

    // 9. Audio
    audioVolume = DEFAULT_AUDIO_VOLUME;
    audioLoopEnabled = DEFAULT_AUDIO_LOOP_ENABLED;
}

    /**
     * Prints the current contents of the internal buffer to the console.
     * <p>
     * Outputs all accumulated formatted text, tables, and ASCII art stored in
     * {@link #buffer}.
     * This method does <b>not</b> clear the buffer.
     * </p>
     * <b>Example:</b>
     * 
     * <pre>{@code
     * coreState.print(); // Output contents of buffer to terminal
     * }</pre>
     */
    void print() {
        System.out.print(buffer);
    }

    /**
     * Clears the terminal screen, prints the current buffer contents, then clears
     * the buffer.
     * <p>
     * Equivalent to calling {@link #clearScreen()}, {@link #print()}, and
     * {@link #clearBuffer()} in sequence.
     * Useful for presenting a fresh output view and discarding the buffer after
     * display.
     * </p>
     * <b>Example:</b>
     * 
     * <pre>{@code
     * coreState.clearingFlush();
     * }</pre>
     */
    public void clearingFlush() {
        clearScreen();
        print();
        clearBuffer();
    }

    /**
     * Rewrites the terminal screen (moves cursor to home), prints the buffer, and
     * clears the buffer.
     * <p>
     * This method calls {@link #rewriteScreen()} to refresh the terminal display
     * without scrolling,
     * then prints the current buffer contents, and finally clears the buffer.
     * Useful for updating dynamic content in-place.
     * </p>
     * <b>Example:</b>
     * 
     * <pre>{@code
     * coreState.rewritingFlush();
     * }</pre>
     */
    public void rewritingFlush() {
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
     * 
     * <pre>{@code
     * coreState.flush();
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
     * 
     * <pre>{@code
     * coreState.clearBuffer();
     * }</pre>
     */
     public CoreState clearBuffer() {
        buffer = new StringBuilder();
        return this;
    }

    /**
     * Pauses the current thread for the specified number of milliseconds.
     * <p>
     * Useful for creating timed pauses between output frames or sections.
     * </p>
     * 
     * @param milliseconds Number of milliseconds to delay.
     *                     <b>Example:</b>
     * 
     *                     <pre>{@code
     *   coreState.delay(500); // Pause for half a second
     * }</pre>
     */
     public CoreState delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    /**
     * Calculates the visible length of a string, ignoring ANSI escape codes.
     * <p>
     * Useful for alignment, centering, and wrapping when colored text is present.
     * </p>
     * 
     * @param text The string to measure.
     * @return The number of visible (printable) characters.
     *         <b>Example:</b>
     * 
     *         <pre>{@code
     *   int len = coreState.visible("\u001B[31mRed Text\u001B[0m"); // returns 8
     * }</pre>
     */
    int visible(String text) {
        if (text == null)
            return 0;
        return text.replaceAll("\\u001B\\[[0-9;]*[A-Za-z]", "").length();
    }

    /**
     * Applies the given ANSI color code to the provided text if ANSI support is
     * enabled.
     * <p>
     * If {@link #ansiSupport} is true and a valid color code is provided, the text
     * is wrapped with the color code and reset sequence.
     * Otherwise, the text is returned unmodified.
     * </p>
     * 
     * @param text   The text to colorize.
     * @param colour The ANSI color code to apply (see {@link Colour}).
     * @return The colorized text if applicable; otherwise, the original text.
     *         <b>Example:</b>
     * 
     *         <pre>{@code
     *   String red = coreState.applyColour("Error", Colour.RED);
     * }</pre>
     */
    String applyColour(String text, Colour colour, BackgroundColour backgroundColour) {
        if ((colour != null && ansiSupport) || text != null || !text.isEmpty())
            return colour.getCode() + backgroundColour.getCode() + text + Colour.RESET.getCode();
        return text;
    }

    /**
     * Repeats the specified string a given number of times and returns the
     * concatenated result.
     * <p>
     * Useful for generating repeated padding, separators, or box lines.
     * </p>
     * 
     * @param content The string to repeat.
     * @param count   The number of times to repeat the string.
     * @return A new string consisting of {@code content} repeated {@code count}
     *         times.
     *         <b>Example:</b>
     * 
     *         <pre>{@code
     *   String line = coreState.repeat("-", 10); // "----------"
     * }</pre>
     */
    String repeat(String content, int count) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
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
     * 
     * <pre>{@code
     * coreState.clearScreen();
     * }</pre>
     */
    void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }

    /**
     * Moves the terminal cursor to the home position (top-left) without fully
     * clearing the screen.
     * <p>
     * This is a lighter alternative to {@link #clearScreen()}, useful for
     * refreshing
     * dynamic output in place.
     * </p>
     * <b>Example:</b>
     * 
     * <pre>{@code
     * coreState.rewriteScreen();
     * }</pre>
     */
    void rewriteScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }
}
