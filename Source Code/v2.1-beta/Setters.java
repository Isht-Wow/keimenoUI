package wow.ishit.v2_beta_keimenoUI;

/**
 * The {@code Setters} class provides a fluent, chainable interface for
 * configuring
 * an internal {@link CoreState} instance with various formatting, color,
 * layout,
 * and audio settings.
 * <p>
 * This class is designed to simplify the process of customizing the
 * {@code CoreState}
 * configuration by exposing a collection of setter methods for each supported
 * property,
 * such as buffer content, alignment, border style, colors, indentation,
 * padding,
 * image and audio options, and more. Each setter returns the same
 * {@code Setters}
 * instance, enabling builder-style chaining for concise and readable code.
 * </p>
 * <p>
 * The chainable design allows users to set multiple properties in a single
 * statement,
 * making it easy to create or modify a {@code CoreState} before passing it to
 * other components (such as {@code Formatter}, {@code AsciiArt}, or
 * {@code AudioManager}).
 * </p>
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * Setters setters = new Setters(existingCoreState)
 *         .setWidth(100)
 *         .setBorder("=")
 *         .setAlignment(Alignment.CENTER)
 *         .setContentColour(Colour.GREEN)
 *         .enableAudio(true)
 *         .setAudioVolume(0.8f);
 * CoreState configuredState = setters.getUpdatedCoreState();
 * // configuredState can now be used with Formatter, AsciiArt, AudioManager,
 * // etc.
 * </pre>
 * <p>
 * This class is intended for use wherever flexible, programmatic configuration
 * of
 * {@link CoreState} is needed, such as in UI setup, rendering, or audio control
 * logic.
 * </p>
 * 
 * @param <T> the concrete subclass type of Setters used for fluent, chainable
 *            setter calls
 */
public class Setters<T extends Setters<T>> {

    /**
     * Stores the internal {@link CoreState} instance that holds all configuration
     * and formatting settings.
     * <p>
     * All setter methods operate on this field, updating its properties to reflect
     * the current configuration. This includes all formatting, color, alignment,
     * padding, border, audio, and rendering options for downstream use.
     * </p>
     */
    public CoreState coreState;

    /**
     * Constructs a new Setters instance with the given CoreState.
     * Allows modification of an existing CoreState instance.
     * 
     * @param coreState the CoreState instance to load and modify
     */
    Setters(CoreState coreState) {
        if (coreState == null) {
            throw new IllegalArgumentException("CoreState cannot be null.");
        }
        this.coreState = coreState;
    }

    // 1. Content and Filler
    /**
     * Sets the ANSI color code used for the main content text.
     * @param contentColour the content foreground colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if contentColour is null
     */
    @SuppressWarnings("unchecked")
    public T setContentColour(Colour contentColour) {
        if (contentColour == null)
            throw new IllegalArgumentException("Content colour cannot be null.");
        coreState.contentColour = contentColour;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for filler characters (padding).
     * @param fillColour the filler foreground colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if fillColour is null
     */
    @SuppressWarnings("unchecked")
    public T setFillColour(Colour fillColour) {
        if (fillColour == null)
            throw new IllegalArgumentException("Fill colour cannot be null.");
        coreState.fillColour = fillColour;
        return (T) this;
    }

    /**
     * Sets the character used as filler for padding and empty space.
     * @param filler the filler character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if filler is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setFiller(char filler) {
        if (filler == '\0')
            throw new IllegalArgumentException("Filler character cannot be null ('\\0').");
        coreState.filler = filler;
        return (T) this;
    }

    /**
     * Sets the background color code used for the main content text.
     * @param colour the background colour for content (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setContentBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Content background colour cannot be null.");
        coreState.contentBackgroundColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for the filler characters.
     * @param colour the background colour for filler (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setFillBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Fill background colour cannot be null.");
        coreState.fillBackgroundColour = colour;
        return (T) this;
    }

    // 2. Border
    /**
     * Sets the string used as the border around formatted output.
     * @param border the border string (must not be null or empty)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if border is null or empty
     */
    @SuppressWarnings("unchecked")
    public T setBorder(String border) {
        if (border == null || border.isEmpty())
            throw new IllegalArgumentException("Border string cannot be null or empty.");
        coreState.border = border;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for borders.
     * @param borderColour the border colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if borderColour is null
     */
    @SuppressWarnings("unchecked")
    public T setBorderColour(Colour borderColour) {
        if (borderColour == null)
            throw new IllegalArgumentException("Border colour cannot be null.");
        coreState.borderColour = borderColour;
        return (T) this;
    }

    /**
     * Sets the background color code used for border region.
     * @param colour the border background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setBorderBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Border background colour cannot be null.");
        coreState.borderBackgroundColour = colour;
        return (T) this;
    }

    // 3. Basic Formatter Variables
    /**
     * Sets the width of the formatted output area, in characters.
     * @param width the output width (must be > 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if width <= 0
     */
    @SuppressWarnings("unchecked")
    public T setWidth(int width) {
        if (width <= 0)
            throw new IllegalArgumentException("Width must be greater than 0.");
        coreState.width = width;
        return (T) this;
    }

    /**
     * Sets the alignment setting for text formatting.
     * @param alignment the alignment (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if alignment is null
     */
    @SuppressWarnings("unchecked")
    public T setAlignment(Alignment alignment) {
        if (alignment == null)
            throw new IllegalArgumentException("Alignment cannot be null.");
        coreState.alignment = alignment;
        return (T) this;
    }

    /**
     * Enables or disables ANSI color support.
     * @param ansiSupport true to enable, false to disable
     * @returns the caller's instance for fluent chaining
     */
    @SuppressWarnings("unchecked")
    public T setAnsiSupport(boolean ansiSupport) {
        coreState.ansiSupport = ansiSupport;
        return (T) this;
    }

    // 4. Wrapping
    /**
     * Enables or disables text wrapping.
     * @param warpEnabled true to enable wrapping, false to disable
     * @returns the caller's instance for fluent chaining
     */
    @SuppressWarnings("unchecked")
    public T setWarpEnabled(boolean warpEnabled) {
        coreState.warpEnabled = warpEnabled;
        return (T) this;
    }

    /**
     * Sets the width (in characters) to wrap text at if wrapping is enabled.
     * @param warpWidth the wrap width (must be > 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if warpWidth <= 0
     */
    @SuppressWarnings("unchecked")
    public T setWarpWidth(int warpWidth) {
        if (warpWidth <= 0)
            throw new IllegalArgumentException("Wrap width must be greater than 0.");
        coreState.warpWidth = warpWidth;
        return (T) this;
    }

    // 5. Indentation
    /**
     * Sets the number of spaces to indent on the left side of each line.
     * @param leftIndent the left indent (must be >= 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if leftIndent < 0
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndent(int leftIndent) {
        if (leftIndent < 0)
            throw new IllegalArgumentException("Left indent cannot be negative.");
        coreState.leftIndent = leftIndent;
        return (T) this;
    }

    /**
     * Sets the character used for left indentation.
     * @param leftIndentChar the left indent character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if leftIndentChar is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndentChar(char leftIndentChar) {
        if (leftIndentChar == '\0')
            throw new IllegalArgumentException("Left indent character cannot be null ('\\0').");
        coreState.leftIndentChar = leftIndentChar;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for left indentation region.
     * @param colour the left indent colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndentColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Left indent colour cannot be null.");
        coreState.leftIndentColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for left indentation region.
     * @param colour the left indent background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndentBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Left indent background colour cannot be null.");
        coreState.leftIndentBackgroundColour = colour;
        return (T) this;
    }

    /**
     * Sets the number of spaces to indent on the right side of each line.
     * @param rightIndent the right indent (must be >= 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if rightIndent < 0
     */
    @SuppressWarnings("unchecked")
    public T setRightIndent(int rightIndent) {
        if (rightIndent < 0)
            throw new IllegalArgumentException("Right indent cannot be negative.");
        coreState.rightIndent = rightIndent;
        return (T) this;
    }

    /**
     * Sets the character used for right indentation.
     * @param rightIndentChar the right indent character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if rightIndentChar is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setRightIndentChar(char rightIndentChar) {
        if (rightIndentChar == '\0')
            throw new IllegalArgumentException("Right indent character cannot be null ('\\0').");
        coreState.rightIndentChar = rightIndentChar;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for right indentation region.
     * @param colour the right indent colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setRightIndentColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Right indent colour cannot be null.");
        coreState.rightIndentColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for right indentation region.
     * @param colour the right indent background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setRightIndentBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Right indent background colour cannot be null.");
        coreState.rightIndentBackgroundColour = colour;
        return (T) this;
    }

    // 6. Padding
    /**
     * Sets the character used for left padding (between border and content).
     * @param leftPaddingChar the left padding character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if leftPaddingChar is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setLeftPaddingChar(char leftPaddingChar) {
        if (leftPaddingChar == '\0')
            throw new IllegalArgumentException("Left padding character cannot be null ('\\0').");
        coreState.leftPaddingChar = leftPaddingChar;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for left padding region.
     * @param colour the left padding colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setLeftPaddingColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Left padding colour cannot be null.");
        coreState.leftPaddingColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for left padding region.
     * @param colour the left padding background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setLeftPaddingBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Left padding background colour cannot be null.");
        coreState.leftPaddingBackgroundColour = colour;
        return (T) this;
    }

    /**
     * Sets the character used for right padding (between content and border).
     * @param rightPaddingChar the right padding character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if rightPaddingChar is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setRightPaddingChar(char rightPaddingChar) {
        if (rightPaddingChar == '\0')
            throw new IllegalArgumentException("Right padding character cannot be null ('\\0').");
        coreState.rightPaddingChar = rightPaddingChar;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for right padding region.
     * @param colour the right padding colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setRightPaddingColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Right padding colour cannot be null.");
        coreState.rightPaddingColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for right padding region.
     * @param colour the right padding background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setRightPaddingBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Right padding background colour cannot be null.");
        coreState.rightPaddingBackgroundColour = colour;
        return (T) this;
    }

    // 7. Header Box
    /**
     * Sets the character used to fill boxes in formatted output.
     * @param headerBoxFiller the header box filler character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if headerBoxFiller is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setHeaderBoxFiller(char headerBoxFiller) {
        if (headerBoxFiller == '\0')
            throw new IllegalArgumentException("Header box filler character cannot be null ('\\0').");
        coreState.headerBoxFiller = headerBoxFiller;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for header box filler characters.
     * @param colour the header box fill colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setHeaderBoxFillColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Header box fill colour cannot be null.");
        coreState.headerBoxFillColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for header box filler characters.
     * @param colour the header box fill background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setHeaderBoxFillBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Header box fill background colour cannot be null.");
        coreState.headerBoxFillBackgroundColour = colour;
        return (T) this;
    }

    // 8. Tables
    /**
     * Sets the character used as vertical separator in tables.
     * @param verticalSeparator the vertical separator character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if verticalSeparator is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setVerticalSeparator(char verticalSeparator) {
        if (verticalSeparator == '\0')
            throw new IllegalArgumentException("Vertical separator character cannot be null ('\\0').");
        coreState.verticalSeparator = verticalSeparator;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for vertical separator characters in tables.
     * @param colour the vertical separator colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setVerticalSeparatorColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Vertical separator colour cannot be null.");
        coreState.verticalSeparatorColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for vertical separator characters in tables.
     * @param colour the vertical separator background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setVerticalSeparatorBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Vertical separator background colour cannot be null.");
        coreState.verticalSeparatorBackgroundColour = colour;
        return (T) this;
    }

    /**
     * Sets the character used as horizontal separator in tables.
     * @param horizontalSeparator the horizontal separator character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if horizontalSeparator is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setHorizontalSeparator(char horizontalSeparator) {
        if (horizontalSeparator == '\0')
            throw new IllegalArgumentException("Horizontal separator character cannot be null ('\\0').");
        coreState.horizontalSeparator = horizontalSeparator;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for horizontal separator characters in tables.
     * @param colour the horizontal separator colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setHorizontalSeparatorColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Horizontal separator colour cannot be null.");
        coreState.horizontalSeparatorColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for horizontal separator characters in tables.
     * @param colour the horizontal separator background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setHorizontalSeparatorBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Horizontal separator background colour cannot be null.");
        coreState.horizontalSeparatoBackgroundColour = colour;
        return (T) this;
    }

    /**
     * Sets the character used as the corner piece in tables.
     * @param tableCornerPiece the table corner piece character (must not be '\0')
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if tableCornerPiece is '\0'
     */
    @SuppressWarnings("unchecked")
    public T setTableCornerPiece(char tableCornerPiece) {
        if (tableCornerPiece == '\0')
            throw new IllegalArgumentException("Table corner piece character cannot be null ('\\0').");
        coreState.tableCornerPiece = tableCornerPiece;
        return (T) this;
    }

    /**
     * Sets the ANSI color code used for table corner piece characters.
     * @param colour the table corner piece colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setTableCornerPieceColour(Colour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Table corner piece colour cannot be null.");
        coreState.tableCornerPieceColour = colour;
        return (T) this;
    }

    /**
     * Sets the background color code used for table corner piece characters.
     * @param colour the table corner piece background colour (must not be null)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if colour is null
     */
    @SuppressWarnings("unchecked")
    public T setTableCornerPieceBackgroundColour(BackgroundColour colour) {
        if (colour == null)
            throw new IllegalArgumentException("Table corner piece background colour cannot be null.");
        coreState.tableCornerPieceBackgroundColour = colour;
        return (T) this;
    }

    // 9. Video / ASCII
    /**
     * Sets the aspect ratio for ASCII art images (width/height).
     * @param aspectRatio the aspect ratio (must be >= 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if aspectRatio < 0
     */
    @SuppressWarnings("unchecked")
    public T setAspectRatio(double aspectRatio) {
        if (aspectRatio < 0)
            throw new IllegalArgumentException("Aspect ratio cannot be negative.");
        coreState.aspectRatio = aspectRatio;
        return (T) this;
    }

    /**
     * Sets the character height ratio for ASCII art scaling (height/width).
     * @param charHeightRatio the character height ratio (must be > 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if charHeightRatio <= 0
     */
    @SuppressWarnings("unchecked")
    public T setCharHeightRatio(double charHeightRatio) {
        if (charHeightRatio <= 0)
            throw new IllegalArgumentException("Character height ratio must be positive.");
        coreState.charHeightRatio = charHeightRatio;
        return (T) this;
    }

    /**
     * Enables or disables audio playback for video output.
     * @param audioEnabled true to enable audio, false to disable
     * @returns the caller's instance for fluent chaining
     */
    @SuppressWarnings("unchecked")
    public T setAudioEnabled(boolean audioEnabled) {
        coreState.audioEnabled = audioEnabled;
        return (T) this;
    }

    /**
     * Sets the maximum frame rate for rendering dynamic output.
     * @param maxFrameRate the max frame rate (must be > 0)
     * @returns the caller's instance for fluent chaining
     * @throws IllegalArgumentException if maxFrameRate <= 0
     */
    @SuppressWarnings("unchecked")
    public T setMaxFrameRate(int maxFrameRate) {
        if (maxFrameRate <= 0)
            throw new IllegalArgumentException("Max frame rate must be greater than 0.");
        coreState.maxFrameRate = maxFrameRate;
        return (T) this;
    }

    // 10. Audio
    /**
     * Sets the volume level for audio playback (range: 0.0 to 1.0).
     * @param audioVolume the audio volume (clamped to [0.0, 1.0])
     * @return the caller's instance for fluent chaining
     * @throws IllegalArgumentException if audioVolume is NaN or infinite
     */
    @SuppressWarnings("unchecked")
    public T setAudioVolume(float audioVolume) {
        if (Float.isNaN(audioVolume) || Float.isInfinite(audioVolume))
            throw new IllegalArgumentException("Audio volume must be a finite number.");
        // Clamp to [0.0, 1.0]
        float clamped = Math.max(0.0f, Math.min(1.0f, audioVolume));
        coreState.audioVolume = clamped;
        return (T) this;
    }

    /**
     * Enables or disables audio playback looping.
     * @param audioLoopEnabled true to enable looping, false to disable
     * @return the caller's instance for fluent chaining
     */
    @SuppressWarnings("unchecked")
    public T setAudioLoopEnabled(boolean audioLoopEnabled) {
        coreState.audioLoopEnabled = audioLoopEnabled;
        return (T) this;
    }
}