package wow.ishit.v2_beta_keimenoUI;

/**
 * Provides a set of chainable setter methods to configure the internal {@link CoreState} object.
 * <p>
 * This class acts as a builder-style interface for modifying various formatting and display properties,
 * such as buffer content, alignment, colors, indentation, padding, audio settings, and more.
 * It allows for fluent and readable configuration by returning the same {@code Setters} instance
 * after each setter call.
 * </p>
 * <p>
 * Usage example:
 * <pre>
 *     Setters setters = new Setters()
 *         .setWidth(80)
 *         .setBorder("*")
 *         .setAlignment(Alignment.CENTER)
 *         .setContentColour(Colour.RED)
 *         .enableAudio(true)
 *         .setAudioVolume(0.75f);
 *     CoreState configuredState = setters.getUpdatedCoreState();
 *      // This allows to customise CoreState which can be used as:
 *      Formatter formatter = new Formatter(configuredState);
 *      AsciiArt asciiArt = new AsciiArt(configuredState);
 *      AudioManager audio = new AudioManager(configuredState);
 * </pre>
 * </p>
 * <p>
 * This class is intended to simplify the modification of the {@link CoreState} configuration,
 * making it easier to manage and apply multiple settings in a concise manner.
 * </p>
 */
public class Setters{

    protected CoreState coreState;
    protected Setters(){
        this.coreState = new CoreState();
    }
    // ------------------ CHAINABLE SETTERS ------------------
    /**
     * Sets the internal {@link StringBuilder} buffer used by this BufferState.
     *
     * <p>
     * Replacing the buffer allows advanced users to supply a custom buffer
     * or reuse an existing one. The buffer will be used for all subsequent
     * formatting operations.
     * </p>
     *
     * @param buffer the new {@link StringBuilder} buffer
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setBuffer(StringBuilder buffer) {
        coreState.buffer = buffer;
        return this;
    }

    /**
     * Sets the width of the BufferState.
     *
     * <p>
     * This defines the total width for alignment, padding, borders, and text
     * warpping.
     * </p>
     * 
     * @param width the desired width in characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setWidth(int width) {
        coreState.width = width;
        return this;
    }

    /**
     * Sets the border string displayed at the edges of formatted lines.
     *
     * @param border the border string; can be empty or null for no border
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setBorder(String border) {
        coreState.border = border;
        return this;
    }

    /**
     * Sets the filler character used for alignment padding.
     *
     * @param filler the character to repeat for empty space
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setFiller(char filler) {
        coreState.filler = filler;
        return this;
    }

    /**
     * Sets the alignment for text in each line.
     *
     * @param alignment the desired {@link Alignment} (LEFT, RIGHT, CENTER, NONE)
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setAlignment(Alignment alignment) {
        coreState.alignment = alignment;
        return this;
    }

    /**
     * Enables or disables ANSI color support.
     *
     * <p>
     * When enabled, all colour codes will be applied to output where supported.
     * </p>
     *
     * @param enabled true to enable ANSI colors, false to disable
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setAnsiSupport(boolean enabled) {
        coreState.ansiSupport = enabled;
        return this;
    }

    /**
     * Sets the content color using a {@link Colour}.
     *
     * @param colour the colour to apply to text content
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setContentColour(Colour colour) {
        coreState.contentColour = colour.getCode();
        return this;
    }

    /**
     * Sets the filler color using a {@link Colour}.
     *
     * @param colour the colour to apply to filler characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setFillColour(Colour colour) {
        coreState.fillColour = colour.getCode();
        return this;
    }

    /**
     * Sets the character used for filling the box.
     *
     * @param boxFiller the character to use for filling the box
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setBoxFiller(char boxFiller) {
        coreState.boxFiller = boxFiller;
        return this;
    }

    /**
     * Sets the color for the box filler.
     * 
     * @param colour the colour to apply to the box filler
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setBoxFillColour(Colour colour) {
        if (colour != null)
            coreState.boxFillColour = colour.getCode();
        else
            coreState.boxFillColour = CoreState.DEFAULT_BOX_FILLER_COLOUR;
        return this;
    }

    /**
     * Sets the border color using a {@link Colour}.
     *
     * @param colour the colour to apply to borders
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setBorderColour(Colour colour) {
        if (colour != null)
            coreState.borderColour = colour.getCode();
        return this;
    }

    /**
     * Sets the number of spaces to indent from the left margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setLeftIndent(int spaces) {
        coreState.leftIndent = Math.max(0, spaces);
        return this;
    }

    /**
     * Sets the number of spaces to indent from the right margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setRightIndent(int spaces) {
        coreState.rightIndent = Math.max(0, spaces);
        return this;
    }

    /**
     * Sets the character used for left indentation.
     *
     * @param c the character to repeat for left indentation
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setLeftIndentChar(char c) {
        coreState.leftIndentChar = c;
        return this;
    }

    /**
     * Sets the character used for right indentation.
     *
     * @param c the character to repeat for right indentation
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setRightIndentChar(char c) {
        coreState.rightIndentChar = c;
        return this;
    }

    /**
     * Sets the aspect ratio for ASCII images.
     *
     * <p>
     * If set to 0, the natural image ratio will be used.
     * </p>
     *
     * @param ratio the width-to-height ratio for images
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setImageAspectRatio(double ratio) {
        coreState.aspectRatio = ratio;
        return this;
    }

    /**
     * Enables or disables audio.
     *
     * @param enabled true to enable audio, false to disable
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters enableAudio(boolean enabled){
        coreState.audioEnabled = enabled;
        return this;
    }

    /**
     * Sets the color for left indentation.
     *
     * @param colour the colour to apply to the left indent characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setLeftIndentColour(Colour colour) {
        if (colour != null)
            coreState.leftIndentColour = colour.getCode();
        return this;
    }

    /**
     * Sets the color for right indentation.
     *
     * @param colour the colour to apply to the right indent characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setRightIndentColour(Colour colour) {
        if (colour != null)
            coreState.rightIndentColour = colour.getCode();
        return this;
    }

    public Setters setLeftPaddingChar(char c) {
        /**
         * Sets the character used for left padding (inside the indented space).
         *
         * @param c the left padding character
         * @return this {@code BufferState} instance for chainable calls
         */
        coreState.leftPaddingChar = c;
        return this;
    }

    /**
     * Sets the character used for right padding (inside the indented space).
     *
     * @param c the right padding character
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setRightPaddingChar(char c) {
        coreState.rightPaddingChar = c;
        return this;
    }

    /**
     * Sets the color for left padding.
     *
     * @param colour the colour to apply to the left padding characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setLeftPaddingColour(Colour colour) {
        if (colour != null)
            coreState.leftPaddingColour = colour.getCode();
        return this;
    }

    /**
     * Sets the color for right padding.
     *
     * @param colour the colour to apply to the right padding characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setRightPaddingColour(Colour colour) {
        if (colour != null)
            coreState.rightPaddingColour = colour.getCode();
        return this;
    }

    /**
     * Enables or disables text warpping.
     *
     * @param enabled true to enable warp , false to disable
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters enableWarp(boolean enabled) {
        coreState.warpEnabled = enabled;
        return this;
    }

    /**
     * Sets the wrap width for text when warp mode is enabled.
     *
     * @param width the maximum line width for wrapping
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setWarpWidth(int width) {
        coreState.warpWidth = width;
        return this;
    }

    /**
     * Sets the character height ratio used for ASCII image rendering.
     *
     * @param ratio the ratio of height to width for terminal characters
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setCharHeightRatio(double ratio) {
        coreState.charHeightRatio = ratio;
        return this;
    }

    /**
     * Sets the audio volume. 0 for silent, 1 for
     *
     * @param volume the audio volume
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setAudioVolume(float volume){
        coreState.audioVolume = Math.min(1.0f, Math.max(0.0f, volume));
        return this;
    }

    /**
     * 
     * @param enabled true to enable audio looping, false to disable
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setAudioLoopEnabled(boolean enabled){
        coreState.audioLoopEnabled = enabled;
        return this;
    }

    /**
     * Sets the maximum frame rate for rendering or updates.
     *
     * @param maxFrameRate the maximum frames per second (FPS)
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setMaxFrameRate(int maxFrameRate) {
        coreState.maxFrameRate = maxFrameRate;
        return this;
    }

    /**
     * Sets the character used as the vertical separator in tables or layouts.
     *
     * @param separator the character to use as vertical separator
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setVerticalSeparator(char separator) {
        coreState.verticalSeparator = separator;
        return this;
    }

    /**
     * Sets the character used as the horizontal separator in tables or layouts.
     *
     * @param separator the character to use as horizontal separator
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setHorizontalSeparator(char separator) {
        coreState.horizontalSeparator = separator;
        return this;
    }

    /**
     * Sets the character used for the corner piece in tables or box layouts.
     *
     * @param cornerPiece the character to use as the table corner piece
     * @return this {@code BufferState} instance for chainable calls
     */
    public Setters setTableCornerPiece(char cornerPiece) {
        coreState.tableCornerPiece = cornerPiece;
        return this;
    }

    /**
     * Returns the current CoreState instance with all updated settings.
     *
     * @return the updated {@link CoreState} instance
     */
    public CoreState getUpdatedCoreState(){
        return coreState;
    }
}
