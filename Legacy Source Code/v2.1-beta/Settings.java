package wow.ishit.v2_beta_keimenoUI;

/**
 * The {@code Settings} class provides read-only access to the current
 * configuration and state
 * of the formatter, layout, and audio settings. It acts as a wrapper around the
 * internal
 * {@link CoreState} object, exposing various getter methods to retrieve
 * formatting options,
 * color codes, indentation settings, audio parameters, and other related
 * properties.
 * <p>
 * This class extends {@link Setters}, inheriting the ability to modify
 * settings, but primarily
 * serves as the interface for retrieving the current configuration values.
 * <p>
 * Usage example:
 * 
 * <pre>
 * Settings settings = new Settings(coreState);
 * int width = settings.getWidth();
 * char boxFiller = settings.getBoxFiller();
 * boolean audioEnabled = settings.getAudioStatus();
 * </pre>
 *
 * @param <T> the type of Settings subclass for fluent setters (inherited from
 *            {@link Setters})
 */
public class Settings<T extends Settings<T>> extends Setters<T> {

    /**
     * Constructs a new {@code Settings} instance with the specified core state.
     *
     * @param coreState the core state to use for settings retrieval; must not be
     *                  {@code null}
     */
    Settings(CoreState coreState) {
        super(coreState);
    }

    // ------------------ GETTERS ------------------

    // -----------------------------------------------------------------
    // Content and Filler
    // -----------------------------------------------------------------

    /**
     * Gets the ANSI color code used for the main content text.
     * Controls the foreground color of text output.
     * Used throughout layout and formatting for regular content.
     * Default: No color applied to content text.
     *
     * @return the content {@link Colour} for main text
     */
    public Colour getContentColour() {
        return coreState.contentColour;
    }

    /**
     * Gets the ANSI color code used for filler characters (padding).
     * Controls the color of filler regions around content in layouts.
     * Default: No color applied to filler regions.
     *
     * @return the {@link Colour} for filler characters
     */
    public Colour getFillColour() {
        return coreState.fillColour;
    }

    /**
     * Gets the character used as filler for padding and empty space in formatted output.
     * Used to fill areas between borders and content or for padding lines.
     * Default: Space character used for filler.
     *
     * @return the filler character
     */
    public char getFiller() {
        return coreState.filler;
    }

    /**
     * Gets the background color code used for the main content text.
     * Controls the background color of text output.
     * Used in layout and content formatting.
     * Default: No background color for content.
     *
     * @return the content {@link BackgroundColour}
     */
    public BackgroundColour getContentBackgroundColour() {
        return coreState.contentBackgroundColour;
    }

    /**
     * Gets the background color code used for the filler characters.
     * Sets the background color behind filler (padding) in formatted output layouts.
     * Default: No background color for filler.
     *
     * @return the fill {@link BackgroundColour}
     */
    public BackgroundColour getFillBackgroundColour() {
        return coreState.fillBackgroundColour;
    }

    // -----------------------------------------------------------------
    // Border
    // -----------------------------------------------------------------

    /**
     * Gets the string used as the border around formatted output.
     * Displayed at the left and right edges of formatted lines or boxes.
     * Used in layout and box/table rendering.
     * Default: "||" as border.
     *
     * @return the border string
     */
    public String getBorder() {
        return coreState.border;
    }

    /**
     * Gets the ANSI color code used for borders.
     * Controls the color of border strings in layouts and tables.
     * Default: No color applied to borders.
     *
     * @return the {@link Colour} for borders
     */
    public Colour getBorderColour() {
        return coreState.borderColour;
    }

    /**
     * Gets the background color code used for the border region.
     * Sets the background color behind the border string in formatted output.
     * Used for highlighting or visually separating borders in layouts and tables.
     * Default: No background color for borders.
     *
     * @return the border {@link BackgroundColour}
     */
    public BackgroundColour getBorderBackgroundColour() {
        return coreState.borderBackgroundColour;
    }

    // -----------------------------------------------------------------
    // Basic Formatter Variables
    // -----------------------------------------------------------------

    /**
     * Gets the width of the formatted output area, in characters.
     * Controls line wrapping and the width of tables, boxes, and ASCII art.
     * Used throughout layout and content formatting.
     * Default: 100 characters wide.
     *
     * @return the output width in characters
     */
    public int getWidth() {
        return coreState.width;
    }

    /**
     * Gets the alignment setting for text formatting.
     * Controls text alignment (LEFT, RIGHT, CENTER) in layout and tables.
     * Default: Left alignment.
     *
     * @return the {@link Alignment} setting
     */
    public Alignment getAlignment() {
        return coreState.alignment;
    }

    /**
     * Checks if ANSI color support is enabled.
     * When true, color codes are applied to output; otherwise, output is colorless.
     * Used globally in layout and rendering.
     * Default: ANSI support enabled.
     *
     * @return true if ANSI color is supported, false otherwise
     */
    public boolean isAnsiSupport() {
        return coreState.ansiSupport;
    }

    /**
     * Checks if text wrapping (word wrap) is enabled.
     * When true, lines longer than wrap width are wrapped in layout and content output.
     * Default: Wrapping disabled.
     *
     * @return true if wrapping is enabled, false otherwise
     */
    public boolean isWarpEnabled() {
        return coreState.warpEnabled;
    }

    /**
     * Gets the width (in characters) to wrap text at if wrapping is enabled.
     * Used in layout and content formatting for word-wrapping.
     * Default: 100 characters.
     *
     * @return the wrap width in characters
     */
    public int getWarpWidth() {
        return coreState.warpWidth;
    }

    // -----------------------------------------------------------------
    // Indentation and Padding
    // -----------------------------------------------------------------

    /**
     * Gets the number of spaces to indent on the left side of each line.
     * Used in layout and formatting to provide left indentation.
     * Default: No left indentation.
     *
     * @return the left indentation amount
     */
    int getLeftIndent() {
        return coreState.leftIndent;
    }

    /**
     * Gets the character used for left indentation (if set, overrides space).
     * Used in layout to customize left indentation appearance.
     * Default: Uses space character if unset.
     *
     * @return the left indentation character
     */
    char getLeftIndentChar() {
        return coreState.leftIndentChar;
    }

    /**
     * Gets the ANSI color code used for left indentation region.
     * Controls the color of left indentation in layout.
     * Default: No color for left indentation.
     *
     * @return the {@link Colour} for left indentation
     */
    Colour getLeftIndentColour() {
        return coreState.leftIndentColour;
    }

    /**
     * Gets the background color code used for left indentation region.
     * Sets the background color for the left indentation area of each line.
     * Used in layout for visually separating indented regions.
     * Default: No background color for left indentation.
     *
     * @return the left indentation {@link BackgroundColour}
     */
    BackgroundColour getLeftIndentBackgroundColour() {
        return coreState.leftIndentBackgroundColour;
    }

    /**
     * Gets the number of spaces to indent on the right side of each line.
     * Used in layout and formatting to provide right indentation.
     * Default: No right indentation.
     *
     * @return the right indentation amount
     */
    int getRightIndent() {
        return coreState.rightIndent;
    }

    /**
     * Gets the character used for right indentation (if set, overrides space).
     * Used in layout to customize right indentation appearance.
     * Default: Uses space character if unset.
     *
     * @return the right indentation character
     */
    char getRightIndentChar() {
        return coreState.rightIndentChar;
    }

    /**
     * Gets the ANSI color code used for right indentation region.
     * Controls the color of right indentation in layout.
     * Default: No color for right indentation.
     *
     * @return the {@link Colour} for right indentation
     */
    Colour getRightIndentColour() {
        return coreState.rightIndentColour;
    }

    /**
     * Gets the background color code used for right indentation region.
     * Sets the background color for the right indentation area of each line.
     * Used in layout for visually separating indented regions.
     * Default: No background color for right indentation.
     *
     * @return the right indentation {@link BackgroundColour}
     */
    BackgroundColour getRightIndentBackgroundColour() {
        return coreState.rightIndentBackgroundColour;
    }

    /**
     * Gets the character used for left padding (between border and content).
     * Used in layout and tables for customizing left padding appearance.
     * Default: Uses space character if unset.
     *
     * @return the left padding character
     */
    char getLeftPaddingChar() {
        return coreState.leftPaddingChar;
    }

    /**
     * Gets the ANSI color code used for left padding region.
     * Controls the color of left padding in layout and tables.
     * Default: No color for left padding.
     *
     * @return the {@link Colour} for left padding
     */
    Colour getLeftPaddingColour() {
        return coreState.leftPaddingColour;
    }

    /**
     * Gets the background color code used for left padding region.
     * Sets the background color for the left padding area (between the border and content).
     * Used for visually distinguishing the padding area in output.
     * Default: No background color for left padding.
     *
     * @return the left padding {@link BackgroundColour}
     */
    BackgroundColour getLeftPaddingBackgroundColour() {
        return coreState.leftPaddingBackgroundColour;
    }

    /**
     * Gets the character used for right padding (between content and border).
     * Used in layout and tables for customizing right padding appearance.
     * Default: Uses space character if unset.
     *
     * @return the right padding character
     */
    char getRightPaddingChar() {
        return coreState.rightPaddingChar;
    }

    /**
     * Gets the ANSI color code used for right padding region.
     * Controls the color of right padding in layout and tables.
     * Default: No color for right padding.
     *
     * @return the {@link Colour} for right padding
     */
    Colour getRightPaddingColour() {
        return coreState.rightPaddingColour;
    }

    /**
     * Gets the background color code used for right padding region.
     * Sets the background color for the right padding area (between the content and border).
     * Used for visually distinguishing the padding area in output.
     * Default: No background color for right padding.
     *
     * @return the right padding {@link BackgroundColour}
     */
    BackgroundColour getRightPaddingBackgroundColour() {
        return coreState.rightPaddingBackgroundColour;
    }

    // -----------------------------------------------------------------
    // Headers
    // -----------------------------------------------------------------

    /**
     * Gets the character used to fill boxes in formatted output (e.g., table or box lines).
     * Used for drawing header and box lines in layout and tables.
     * Default: '-' character.
     *
     * @return the header box filler character
     */
    char getHeaderBoxFiller() {
        return coreState.headerBoxFiller;
    }

    /**
     * Gets the ANSI color code used for header box filler characters.
     * Controls the color of header and box lines in layout and tables.
     * Default: No color for header box filler.
     *
     * @return the {@link Colour} for header box filler
     */
    Colour getHeaderBoxFillColour() {
        return coreState.headerBoxFillColour;
    }

    /**
     * Gets the background color code used for header box filler characters.
     * Sets the background color for the filler characters used in header boxes or lines.
     * Used for visually separating or highlighting header sections.
     * Default: No background color for header box filler.
     *
     * @return the header box filler {@link BackgroundColour}
     */
    BackgroundColour getHeaderBoxFillBackgroundColour() {
        return coreState.headerBoxFillBackgroundColour;
    }

    // -----------------------------------------------------------------
    // Tables
    // -----------------------------------------------------------------

    /**
     * Gets the character used as vertical separator in tables.
     * Used to divide columns in table output.
     * Default: '|' character.
     *
     * @return the vertical separator character
     */
    char getVerticalSeparator() {
        return coreState.verticalSeparator;
    }

    /**
     * Gets the ANSI color code used for vertical separator characters in tables.
     * Controls the color of vertical lines between columns.
     * Default: No color for vertical separators.
     *
     * @return the {@link Colour} for vertical separator
     */
    Colour getVerticalSeparatorColour() {
        return coreState.verticalSeparatorColour;
    }

    /**
     * Gets the background color code used for vertical separator characters in tables.
     * Sets the background color behind vertical separator characters in tables.
     * Default: No background color for vertical separators.
     *
     * @return the vertical separator {@link BackgroundColour}
     */
    BackgroundColour getVerticalSeparatorBackgroundColour() {
        return coreState.verticalSeparatorBackgroundColour;
    }

    /**
     * Gets the character used as horizontal separator in tables.
     * Used to divide rows in table output.
     * Default: '-' character.
     *
     * @return the horizontal separator character
     */
    char getHorizontalSeparator() {
        return coreState.horizontalSeparator;
    }

    /**
     * Gets the ANSI color code used for horizontal separator characters in tables.
     * Controls the color of horizontal lines between rows.
     * Default: No color for horizontal separators.
     *
     * @return the {@link Colour} for horizontal separator
     */
    Colour getHorizontalSeparatorColour() {
        return coreState.horizontalSeparatorColour;
    }

    /**
     * Gets the background color code used for horizontal separator characters in tables.
     * Sets the background color behind horizontal separator characters in tables.
     * Default: No background color for horizontal separators.
     *
     * @return the horizontal separator {@link BackgroundColour}
     */
    BackgroundColour getHorizontalSeparatorBackgroundColour() {
        return coreState.horizontalSeparatoBackgroundColour;
    }

    /**
     * Gets the character used as the corner piece in tables.
     * Used at the intersection points of horizontal and vertical separators in tables.
     * Default: '+' character.
     *
     * @return the table corner piece character
     */
    char getTableCornerPiece() {
        return coreState.tableCornerPiece;
    }

    /**
     * Gets the ANSI color code used for table corner piece characters.
     * Controls the color of the intersection points in table borders.
     * Default: No color for table corner pieces.
     *
     * @return the {@link Colour} for table corner piece
     */
    Colour getTableCornerPieceColour() {
        return coreState.tableCornerPieceColour;
    }

    /**
     * Gets the background color code used for table corner piece characters.
     * Sets the background color for intersection points in table borders.
     * Default: No background color for table corner pieces.
     *
     * @return the table corner piece {@link BackgroundColour}
     */
    BackgroundColour getTableCornerPieceBackgroundColour() {
        return coreState.tableCornerPieceBackgroundColour;
    }

    // -----------------------------------------------------------------
    // Video and Image
    // -----------------------------------------------------------------

    /**
     * Gets the aspect ratio for ASCII art images (width/height).
     * Used to scale ASCII art to maintain proportions during rendering.
     * Default: No aspect ratio scaling (0).
     *
     * @return the aspect ratio for ASCII art
     */
    double getAspectRatio() {
        return coreState.aspectRatio;
    }

    /**
     * Gets the character height ratio for ASCII art scaling (height/width).
     * Used to properly scale ASCII art vertically during rendering.
     * Default: 2 (twice as tall as wide).
     *
     * @return the character height ratio
     */
    double getCharHeightRatio() {
        return coreState.charHeightRatio;
    }

    /**
     * Checks if audio playback is enabled for video output.
     * Used in video and animation rendering to control audio.
     * Default: Audio enabled for video output.
     *
     * @return true if audio is enabled, false otherwise
     */
    boolean isAudioEnabled() {
        return coreState.audioEnabled;
    }

    /**
     * Gets the maximum frame rate for rendering dynamic output (e.g., animations).
     * Used in video and animation rendering.
     * Default: 5 frames per second.
     *
     * @return the maximum frame rate
     */
    int getMaxFrameRate() {
        return coreState.maxFrameRate;
    }

    // -----------------------------------------------------------------
    // Audio
    // -----------------------------------------------------------------

    /**
     * Gets the volume level for audio playback (range: 0.0 to 1.0).
     * Used in audio and video playback components.
     * Default: Maximum volume (1.0).
     *
     * @return the audio volume level
     */
    float getAudioVolume() {
        return coreState.audioVolume;
    }

    /**
     * Checks if audio playback should loop.
     * Used in audio and video playback to control looping behavior.
     * Default: Audio looping disabled.
     *
     * @return true if audio looping is enabled, false otherwise
     */
    boolean isAudioLoopEnabled() {
        return coreState.audioLoopEnabled;
    }

    // -----------------------------------------------------------------
    // Buffer (internal, always last)
    // -----------------------------------------------------------------

    /**
     * Returns the internal {@link StringBuilder} buffer containing the formatted
     * content.
     * This buffer holds the current accumulated output of formatting operations.
     * Used internally by the formatting and rendering system.
     * Default: Empty upon initialization or after clearing.
     *
     * @return the internal {@code StringBuilder} buffer with formatted content
     */
    StringBuilder getBuffer() {
        return coreState.buffer;
    }
}