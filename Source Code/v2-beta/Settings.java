package wow.ishit.v2_beta_keimenoUI;

/**
 * The {@code Settings} class provides read-only access to the current configuration and state
 * of the formatter, layout, and audio settings. It acts as a wrapper around the internal
 * core state object, exposing various getter methods to retrieve formatting options,
 * color codes, indentation settings, audio parameters, and other related properties.
 * <p>
 * This class extends {@code Setters}, inheriting the ability to modify settings, but primarily
 * serves as the interface for retrieving the current configuration values.
 * <p>
 * Usage example:
 * <pre>
 *     Settings settings = new Settings();
 *     int width = settings.getWidth();
 *     char boxFiller = settings.getBoxFiller();
 *     boolean audioEnabled = settings.getAudioStatus();
 * </pre>
 */
public class Settings extends Setters{

    /**
     * Constructs a new {@code Settings} instance with default core state.
     */
    public Settings(){
        super();
    }
    /**
     * Constructs a new {@code Settings} instance with the specified core state.
     *
     * @param coreState the core state to use for settings retrieval
     */
    public Settings(CoreState coreState){
        super(coreState);
    }
    // ------------------ GETTERS ------------------

    /**
     * Returns the internal {@code StringBuilder} buffer containing formatted content.
     *
     * @return the internal buffer
     */
    public StringBuilder getBuffer() {
        return coreState.buffer;
    }

    /**
     * Gets the current width setting for the formatter.
     *
     * @return the width in characters
     */
    public int getWidth() {
        return coreState.width;
    }

    /**
     * Gets the character used for filling the box.
     *
     * @return the box filler character
     */
    public char getBoxFiller() {
        return coreState.boxFiller;
    }

    /**
     * Gets the current border string.
     *
     * @return the border string
     */
    public String getBorder() {
        return coreState.border;
    }

    /**
     * Gets the filler character used for alignment.
     *
     * @return the filler character
     */
    public char getFiller() {
        return coreState.filler;
    }

    /**
     * Gets the current text alignment setting.
     *
     * @return the current alignment setting
     */
    public Alignment getAlignment() {
        return coreState.alignment;
    }

    /**
     * Returns whether ANSI color support is enabled.
     *
     * @return {@code true} if ANSI color is enabled; {@code false} otherwise
     */
    public boolean getAnsiSupport() {
        return coreState.ansiSupport;
    }

    /**
     * Gets the color code used for content text.
     *
     * @return the colour used for content text
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
     *
     * @return the colour used for filler characters
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
     *
     * @return the colour used for borders
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
     *
     * @return the left indentation size in spaces
     */
    public int getLeftIndent() {
        return coreState.leftIndent;
    }

    /**
     * Gets the number of spaces used for right indentation.
     *
     * @return the right indentation size in spaces
     */
    public int rightIndent() {
        return coreState.rightIndent;
    }

    /**
     * Gets the character used for left indentation.
     *
     * @return the character used for left indentation
     */
    public char leftIndentChar() {
        return coreState.leftIndentChar;
    }

    /**
     * Gets the character used for right indentation.
     *
     * @return the character used for right indentation
     */
    public char rightIndentChar() {
        return coreState.rightIndentChar;
    }

    /**
     * Gets the current image aspect ratio setting.
     *
     * @return the aspect ratio used for images
     */
    public double getImageAspectRatio() {
        return coreState.aspectRatio;
    }
    /**
     * Returns whether audio is enabled.
     *
     * @return {@code true} if audio is enabled; {@code false} otherwise
     */
    public boolean getAudioStatus(){
        return coreState.audioEnabled;
    }

    /**
     * Gets the color code for left indentation.
     *
     * @return the colour used for left indentation
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
     *
     * @return the colour used for right indentation
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
     *
     * @return the character used for left padding
     */
    public char getLeftPaddingChar() {
        return coreState.leftPaddingChar;
    }

    /**
     * Gets the character used for right padding.
     *
     * @return the character used for right padding
     */
    public char getRightPaddingChar() {
        return coreState.rightPaddingChar;
    }

    /**
     * Gets the color code for left padding.
     *
     * @return the colour used for left padding
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
     *
     * @return the colour used for right padding
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
     *
     * @return {@code true} if wrapping is enabled; {@code false} otherwise
     */
    public boolean getWarpStatus() {
        return coreState.warpEnabled;
    }

    /**
     * Gets the maximum width for text wrapping.
     *
     * @return the wrap width in characters
     */
    public int getWarpWidth() {
        return coreState.warpWidth;
    }

    /**
     * Gets the character height ratio used for ASCII image rendering.
     *
     * @return the character height ratio
     */
    public double getCharHeightRatio() {
        return coreState.charHeightRatio;
    }

    /**
     * Gets the audio volume.
     *
     * @return the audio volume as a float
     */
    public float getAudioVolume(){
        return coreState.audioVolume;
    }

    /**
     * Gets the audio loop status.
     *
     * @return {@code true} if audio looping is enabled; {@code false} otherwise
     */
    public boolean getAudioLoopStatus() {
        return coreState.audioLoopEnabled;
    }

    /**
     * Gets the maximum frame rate setting.
     *
     * @return the maximum frames per second allowed
     */
    public int getMaxFrameRate(){
        return coreState.maxFrameRate;
    }

    /**
     * Gets the character used as the vertical separator in tables or layouts.
     *
     * @return the vertical separator character
     */
    public char getVerticalSeparator(){
        return coreState.verticalSeparator;
    }

    /**
     * Gets the character used as the horizontal separator in tables or layouts.
     *
     * @return the horizontal separator character
     */
    public char getHorizontalSeparator(){
        return coreState.horizontalSeparator;
    }

    /**
     * Gets the character used for the corner piece in tables or box layouts.
     *
     * @return the table corner piece character
     */
    public char getTableCornerPiece(){
        return coreState.tableCornerPiece;
    }
}