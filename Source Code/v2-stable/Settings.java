package wow.ishit.v2_beta_keimenoUI;

/**
 * The {@code Settings} class provides read-only access to the current configuration and state
 * of the formatter, layout, and audio settings. It acts as a wrapper around the internal
 * {@link CoreState} object, exposing various getter methods to retrieve formatting options,
 * color codes, indentation settings, audio parameters, and other related properties.
 * <p>
 * This class extends {@link Setters}, inheriting the ability to modify settings, but primarily
 * serves as the interface for retrieving the current configuration values.
 * <p>
 * Usage example:
 * <pre>
 *     Settings settings = new Settings(coreState);
 *     int width = settings.getWidth();
 *     char boxFiller = settings.getBoxFiller();
 *     boolean audioEnabled = settings.getAudioStatus();
 * </pre>
 *
 * @param <T> the type of Settings subclass for fluent setters (inherited from {@link Setters})
 */
public class Settings<T extends Settings<T>> extends Setters<T> {
    
    /**
     * The {@code CoreState} instance that holds all internal state and configuration values.
     * This field is the source of all settings retrieved by this class.
     */
    public CoreState coreState;
    
    /**
     * Constructs a new {@code Settings} instance with the specified core state.
     *
     * @param coreState the core state to use for settings retrieval; must not be {@code null}
     */
    public Settings(CoreState coreState){
        this.coreState = coreState;
        super(coreState);
    }

    // ------------------ GETTERS ------------------

    /**
     * Returns the internal {@link StringBuilder} buffer containing the formatted content.
     * This buffer holds the current accumulated output of formatting operations.
     *
     * @return the internal {@code StringBuilder} buffer with formatted content
     */
    public StringBuilder getBuffer() {
        return coreState.buffer;
    }

    /**
     * Gets the current width setting for the formatter.
     * This width is typically measured in characters and defines the maximum line width.
     *
     * @return the width in characters used for formatting
     */
    public int getWidth() {
        return coreState.width;
    }

    /**
     * Gets the character used for filling the box layout.
     * This character is used to fill space within boxes or framed areas.
     *
     * @return the box filler character
     */
    public char getBoxFiller() {
        return coreState.boxFiller;
    }

    /**
     * Gets the current border string used for framing content.
     * This string represents the visual border applied around formatted content.
     *
     * @return the border string used for framing
     */
    public String getBorder() {
        return coreState.border;
    }

    /**
     * Gets the filler character used for alignment padding.
     * This character fills space to align text or layout elements.
     *
     * @return the filler character used for alignment
     */
    public char getFiller() {
        return coreState.filler;
    }

    /**
     * Gets the current text alignment setting.
     * Defines how text is aligned within the available space (e.g., left, center, right).
     *
     * @return the current {@link Alignment} setting
     */
    public Alignment getAlignment() {
        return coreState.alignment;
    }

    /**
     * Returns whether ANSI color support is enabled.
     * When enabled, output may include ANSI escape codes for colored text.
     *
     * @return {@code true} if ANSI color support is enabled; {@code false} otherwise
     */
    public boolean getAnsiSupport() {
        return coreState.ansiSupport;
    }

    /**
     * Gets the color code used for content text.
     * This color applies to the main textual content.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for content text, or {@code null} if none matches
     */
    public Colour getContentColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.contentColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Gets the color code used for filler characters.
     * This color applies to filler or padding characters in the layout.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for filler characters, or {@code null} if none matches
     */
    public Colour getFillColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.fillColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Gets the color code used for borders.
     * This color applies to border elements surrounding content.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for borders, or {@code null} if none matches
     */
    public Colour getBorderColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.borderColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }   

    /**
     * Gets the number of spaces used for left indentation.
     * This value defines how many spaces to indent content from the left margin.
     *
     * @return the left indentation size in spaces
     */
    public int getLeftIndent() {
        return coreState.leftIndent;
    }

    /**
     * Gets the number of spaces used for right indentation.
     * This value defines how many spaces to indent content from the right margin.
     *
     * @return the right indentation size in spaces
     */
    public int getRightIndent() {
        return coreState.rightIndent;
    }

    /**
     * Gets the character used for left indentation.
     * This character is repeated to create the left indent padding.
     *
     * @return the character used for left indentation
     */
    public char getLeftIndentChar() {
        return coreState.leftIndentChar;
    }

    /**
     * Gets the character used for right indentation.
     * This character is repeated to create the right indent padding.
     *
     * @return the character used for right indentation
     */
    public char getRightIndentChar() {
        return coreState.rightIndentChar;
    }

    /**
     * Gets the current image aspect ratio setting.
     * This ratio is used when rendering images or ASCII art to maintain proportions.
     *
     * @return the aspect ratio used for images (width divided by height)
     */
    public double getImageAspectRatio() {
        return coreState.aspectRatio;
    }
    
    /**
     * Returns whether audio is enabled.
     * Indicates if audio playback features are currently active.
     *
     * @return {@code true} if audio is enabled; {@code false} otherwise
     */
    public boolean getAudioStatus(){
        return coreState.audioEnabled;
    }

    /**
     * Gets the color code for left indentation.
     * This color is applied to the left indentation padding.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for left indentation, or {@code null} if none matches
     */
    public Colour getLeftIndentColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.leftIndentColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Gets the color code for right indentation.
     * This color is applied to the right indentation padding.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for right indentation, or {@code null} if none matches
     */
    public Colour getRightIndentColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.rightIndentColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Gets the character used for left padding.
     * This character fills space for padding on the left side of content.
     *
     * @return the character used for left padding
     */
    public char getLeftPaddingChar() {
        return coreState.leftPaddingChar;
    }

    /**
     * Gets the character used for right padding.
     * This character fills space for padding on the right side of content.
     *
     * @return the character used for right padding
     */
    public char getRightPaddingChar() {
        return coreState.rightPaddingChar;
    }

    /**
     * Gets the color code for left padding.
     * This color is applied to the left padding characters.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for left padding, or {@code null} if none matches
     */
    public Colour getLeftPaddingColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.leftPaddingColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Gets the color code for right padding.
     * This color is applied to the right padding characters.
     * <p>
     * Note: Returns {@code null} if the color code does not match any known {@link Colour}.
     *
     * @return the {@link Colour} used for right padding, or {@code null} if none matches
     */
    public Colour getRightPaddingColour() {
        for(Colour colour: Colour.values()) {
            if(coreState.rightPaddingColour == colour.getCode()) {
                return colour;
            }
        }
        return null;
    }

    /**
     * Returns whether text wrapping (warp) is enabled.
     * When enabled, content will wrap to new lines at the specified wrap width.
     *
     * @return {@code true} if wrapping is enabled; {@code false} otherwise
     */
    public boolean getWarpStatus() {
        return coreState.warpEnabled;
    }

    /**
     * Gets the maximum width for text wrapping.
     * This value defines the character limit at which text wraps to the next line.
     *
     * @return the wrap width in characters
     */
    public int getWarpWidth() {
        return coreState.warpWidth;
    }

    /**
     * Gets the character height ratio used for ASCII image rendering.
     * This ratio adjusts the vertical scaling of characters relative to their width.
     *
     * @return the character height ratio (height divided by width)
     */
    public double getCharHeightRatio() {
        return coreState.charHeightRatio;
    }

    /**
     * Gets the audio volume level.
     * This value represents the audio playback volume as a float between 0.0 (mute) and 1.0 (max).
     *
     * @return the audio volume level
     */
    public float getAudioVolume(){
        return coreState.audioVolume;
    }

    /**
     * Gets the audio loop status.
     * Indicates whether audio playback is set to loop continuously.
     *
     * @return {@code true} if audio looping is enabled; {@code false} otherwise
     */
    public boolean getAudioLoopStatus() {
        return coreState.audioLoopEnabled;
    }

    /**
     * Gets the maximum frame rate setting.
     * This value limits the number of frames per second allowed in animations or rendering.
     *
     * @return the maximum frames per second allowed
     */
    public int getMaxFrameRate(){
        return coreState.maxFrameRate;
    }

    /**
     * Gets the character used as the vertical separator in tables or layouts.
     * This character visually separates columns or sections vertically.
     *
     * @return the vertical separator character
     */
    public char getVerticalSeparator(){
        return coreState.verticalSeparator;
    }

    /**
     * Gets the character used as the horizontal separator in tables or layouts.
     * This character visually separates rows or sections horizontally.
     *
     * @return the horizontal separator character
     */
    public char getHorizontalSeparator(){
        return coreState.horizontalSeparator;
    }

    /**
     * Gets the character used for the corner piece in tables or box layouts.
     * This character is used at the intersections of vertical and horizontal separators.
     *
     * @return the table corner piece character
     */
    public char getTableCornerPiece(){
        return coreState.tableCornerPiece;
    }
}