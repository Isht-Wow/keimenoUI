package wow.ishit.v2_stable_keimenoUI;

/**
 * The {@code Setters} class provides a fluent, chainable interface for configuring
 * an internal {@link CoreState} instance with various formatting, color, layout,
 * and audio settings.
 * <p>
 * This class is designed to simplify the process of customizing the {@code CoreState}
 * configuration by exposing a collection of setter methods for each supported property,
 * such as buffer content, alignment, border style, colors, indentation, padding,
 * image and audio options, and more. Each setter returns the same {@code Setters}
 * instance, enabling builder-style chaining for concise and readable code.
 * </p>
 * <p>
 * The chainable design allows users to set multiple properties in a single statement,
 * making it easy to create or modify a {@code CoreState} before passing it to
 * other components (such as {@code Formatter}, {@code AsciiArt}, or {@code AudioManager}).
 * </p>
 * <p>
 * <b>Usage Example:</b>
 * <pre>
 * Setters setters = new Setters(existingCoreState)
 *     .setWidth(100)
 *     .setBorder("=")
 *     .setAlignment(Alignment.CENTER)
 *     .setContentColour(Colour.GREEN)
 *     .enableAudio(true)
 *     .setAudioVolume(0.8f);
 * CoreState configuredState = setters.getUpdatedCoreState();
 * // configuredState can now be used with Formatter, AsciiArt, AudioManager, etc.
 * </pre>
 * <p>
 * This class is intended for use wherever flexible, programmatic configuration of
 * {@link CoreState} is needed, such as in UI setup, rendering, or audio control logic.
 * </p>
 *  @param <T> the concrete subclass type of Setters used for fluent, chainable setter calls
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
    private CoreState coreState;

    /**
     * Returns the internal CoreState object.
     * @return the CoreState object
     */
    public CoreState getCoreState(){
        return coreState;
    }

    /**
     * Constructs a new Setters instance with the given CoreState.
     * Allows modification of an existing CoreState instance.
     * 
     * @param coreState the CoreState instance to load and modify
     */
    public Setters(CoreState coreState) {
        if(coreState == null) {
            throw new IllegalArgumentException("CoreState cannot be null.");
        }
        this.coreState = coreState;
    }

    /**
     * Sets the internal {@link StringBuilder} buffer used by this BufferState.
     * <p>
     * Replacing the buffer allows advanced users to supply a custom buffer
     * or reuse an existing one. The buffer will be used for all subsequent
     * formatting operations.
     * </p>
     *
     * @param buffer the new {@link StringBuilder} buffer; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code buffer} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setBuffer(StringBuilder buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null.");
        }
        coreState.buffer = buffer;
        return (T) this;
    }

    /**
     * Sets the width of the BufferState.
     * <p>
     * This defines the total width for alignment, padding, borders, and text wrapping.
     * </p>
     *
     * @param width the desired width in characters (must be positive)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code width} is less than 1
     */
    @SuppressWarnings("unchecked")
    public T setWidth(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Width must be positive.");
        }
        coreState.width = width;
        return (T) this;
    }

    /**
     * Sets the border string displayed at the edges of formatted lines.
     *
     * @param border the border string; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code border} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setBorder(String border) {
        if (border == null) {
            throw new IllegalArgumentException("Border cannot be null.");
        }
        coreState.border = border;
        return (T) this;
    }

    /**
     * Sets the filler character used for alignment padding.
     *
     * @param filler the character to repeat for empty space
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code filler} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setFiller(char filler) {
        if (filler == '\0') {
            throw new IllegalArgumentException("Filler cannot be null character.");
        }
        coreState.filler = filler;
        return (T) this;
    }

    /**
     * Sets the alignment for text in each line.
     *
     * @param alignment the desired {@link Alignment} (LEFT, RIGHT, CENTER, NONE); must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code alignment} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setAlignment(Alignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Alignment cannot be null.");
        }
        coreState.alignment = alignment;
        return (T) this;
    }

    /**
     * Enables or disables ANSI color support.
     * <p>
     * When enabled, all colour codes will be applied to output where supported.
     * </p>
     *
     * @param enabled true to enable ANSI colors, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T setAnsiSupport(boolean enabled) {
        coreState.ansiSupport = enabled;
        return (T) this;
    }

    /**
     * Sets the content color using a {@link Colour}.
     *
     * @param colour the colour to apply to text content; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setContentColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Content colour cannot be null.");
        }
        coreState.contentColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the filler color using a {@link Colour}.
     *
     * @param colour the colour to apply to filler characters; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setFillColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Fill colour cannot be null.");
        }
        coreState.fillColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the character used for filling the box.
     *
     * @param boxFiller the character to use for filling the box
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code boxFiller} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setBoxFiller(char boxFiller) {
        if (boxFiller == '\0') {
            throw new IllegalArgumentException("Box filler cannot be null character.");
        }
        coreState.boxFiller = boxFiller;
        return (T) this;
    }

    /**
     * Sets the color for the box filler.
     *
     * @param colour the colour to apply to the box filler; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setBoxFillColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Box fill colour cannot be null.");
        }
        coreState.boxFillColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the border color using a {@link Colour}.
     *
     * @param colour the colour to apply to borders; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setBorderColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Border colour cannot be null.");
        }
        coreState.borderColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the number of spaces to indent from the left margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code spaces} is negative
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndent(int spaces) {
        if (spaces < 0) {
            throw new IllegalArgumentException("Left indent cannot be negative.");
        }
        coreState.leftIndent = spaces;
        return (T) this;
    }

    /**
     * Sets the number of spaces to indent from the right margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code spaces} is negative
     */
    @SuppressWarnings("unchecked")
    public T setRightIndent(int spaces) {
        if (spaces < 0) {
            throw new IllegalArgumentException("Right indent cannot be negative.");
        }
        coreState.rightIndent = spaces;
        return (T) this;
    }

    /**
     * Sets the character used for left indentation.
     *
     * @param c the character to repeat for left indentation
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code c} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndentChar(char c) {
        if (c == '\0') {
            throw new IllegalArgumentException("Left indent character cannot be null character.");
        }
        coreState.leftIndentChar = c;
        return (T) this;
    }

    /**
     * Sets the character used for right indentation.
     *
     * @param c the character to repeat for right indentation
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code c} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setRightIndentChar(char c) {
        if (c == '\0') {
            throw new IllegalArgumentException("Right indent character cannot be null character.");
        }
        coreState.rightIndentChar = c;
        return (T) this;
    }

    /**
     * Sets the aspect ratio for ASCII images.
     * <p>
     * If set to 0, the natural image ratio will be used.
     * </p>
     *
     * @param ratio the width-to-height ratio for images (must be non-negative)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code ratio} is negative
     */
    @SuppressWarnings("unchecked")
    public T setImageAspectRatio(double ratio) {
        if (ratio < 0.0) {
            throw new IllegalArgumentException("Aspect ratio cannot be negative.");
        }
        coreState.aspectRatio = ratio;
        return (T) this;
    }

    /**
     * Enables or disables audio.
     *
     * @param enabled true to enable audio, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T enableAudio(boolean enabled) {
        coreState.audioEnabled = enabled;
        return (T) this;
    }

    /**
     * Sets the color for left indentation.
     *
     * @param colour the colour to apply to the left indent characters; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setLeftIndentColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Left indent colour cannot be null.");
        }
        coreState.leftIndentColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the color for right indentation.
     *
     * @param colour the colour to apply to the right indent characters; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setRightIndentColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Right indent colour cannot be null.");
        }
        coreState.rightIndentColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the character used for left padding (inside the indented space).
     *
     * @param c the left padding character
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code c} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setLeftPaddingChar(char c) {
        if (c == '\0') {
            throw new IllegalArgumentException("Left padding character cannot be null character.");
        }
        coreState.leftPaddingChar = c;
        return (T) this;
    }

    /**
     * Sets the character used for right padding (inside the indented space).
     *
     * @param c the right padding character
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code c} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setRightPaddingChar(char c) {
        if (c == '\0') {
            throw new IllegalArgumentException("Right padding character cannot be null character.");
        }
        coreState.rightPaddingChar = c;
        return (T) this;
    }

    /**
     * Sets the color for left padding.
     *
     * @param colour the colour to apply to the left padding characters; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setLeftPaddingColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Left padding colour cannot be null.");
        }
        coreState.leftPaddingColour = colour.getCode();
        return (T) this;
    }

    /**
     * Sets the color for right padding.
     *
     * @param colour the colour to apply to the right padding characters; must not be {@code null}
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code colour} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public T setRightPaddingColour(Colour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Right padding colour cannot be null.");
        }
        coreState.rightPaddingColour = colour.getCode();
        return (T) this;
    }

    /**
     * Enables or disables text wrapping (warp).
     *
     * @param enabled true to enable wrapping, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T enableWarp(boolean enabled) {
        coreState.warpEnabled = enabled;
        return (T) this;
    }

    /**
     * Sets the wrap width for text when warp mode is enabled.
     *
     * @param width the maximum line width for wrapping
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the wrap width for text when wrap mode is enabled.
     *
     * @param width the maximum line width for wrapping (must be positive)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code width} is less than 1
     */
    /**
     * Sets the wrap width for text when wrap mode is enabled.
     *
     * @param width the maximum line width for wrapping (must be positive)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code width} is less than 1
     */
    @SuppressWarnings("unchecked")
    public T setWarpWidth(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Warp width must be positive.");
        }
        coreState.warpWidth = width;
        return (T) this;
    }

    /**
     * Sets the character height ratio used for ASCII image rendering.
     *
     * @param ratio the ratio of height to width for terminal characters
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character height ratio used for ASCII image rendering.
     *
     * @param ratio the ratio of height to width for terminal characters (must be positive)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code ratio} is not positive
     */
    /**
     * Sets the character height ratio used for ASCII image rendering.
     *
     * @param ratio the ratio of height to width for terminal characters (must be positive)
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code ratio} is not positive
     */
    @SuppressWarnings("unchecked")
    public T setCharHeightRatio(double ratio) {
        if (ratio <= 0.0) {
            throw new IllegalArgumentException("Character height ratio must be positive.");
        }
        coreState.charHeightRatio = ratio;
        return (T) this;
    }

    /**
     * Sets the audio volume. 0 for silent, 1 for full volume.
     *
     * @param volume the audio volume
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the audio volume. 0 for silent, 1 for full volume.
     *
     * @param volume the audio volume (must be in [0.0, 1.0])
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code volume} is not in [0.0, 1.0]
     */
    /**
     * Sets the audio volume. 0 for silent, 1 for full volume.
     *
     * @param volume the audio volume (must be in [0.0, 1.0])
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code volume} is not in [0.0, 1.0]
     */
    @SuppressWarnings("unchecked")
    public T setAudioVolume(float volume) {
        if (volume < 0.0f || volume > 1.0f) {
            throw new IllegalArgumentException("Audio volume must be between 0.0 and 1.0.");
        }
        coreState.audioVolume = volume;
        return (T) this;
    }

    /**
     * Enables or disables audio looping.
     *
     * @param enabled true to enable audio looping, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Enables or disables audio looping.
     *
     * @param enabled true to enable audio looping, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Enables or disables audio looping.
     *
     * @param enabled true to enable audio looping, false to disable
     * @return this {@code Setters} instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T setAudioLoopEnabled(boolean enabled) {
        coreState.audioLoopEnabled = enabled;
        return (T) this;
    }

    /**
     * Sets the maximum frame rate for rendering or updates.
     *
     * @param maxFrameRate the maximum frames per second (FPS)
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the maximum frame rate for rendering or updates.
     *
     * @param maxFrameRate the maximum frames per second (FPS); must be positive
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code maxFrameRate} is less than 1
     */
    /**
     * Sets the maximum frame rate for rendering or updates.
     *
     * @param maxFrameRate the maximum frames per second (FPS); must be positive
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code maxFrameRate} is less than 1
     */
    @SuppressWarnings("unchecked")
    public T setMaxFrameRate(int maxFrameRate) {
        if (maxFrameRate < 1) {
            throw new IllegalArgumentException("Max frame rate must be positive.");
        }
        coreState.maxFrameRate = maxFrameRate;
        return (T) this;
    }

    /**
     * Sets the character used as the vertical separator in tables or layouts.
     *
     * @param separator the character to use as vertical separator
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used as the vertical separator in tables or layouts.
     *
     * @param separator the character to use as vertical separator
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used as the vertical separator in tables or layouts.
     *
     * @param separator the character to use as vertical separator; must not be the null character
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code separator} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setVerticalSeparator(char separator) {
        if (separator == '\0') {
            throw new IllegalArgumentException("Vertical separator cannot be null character.");
        }
        coreState.verticalSeparator = separator;
        return (T) this;
    }

    /**
     * Sets the character used as the horizontal separator in tables or layouts.
     *
     * @param separator the character to use as horizontal separator
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used as the horizontal separator in tables or layouts.
     *
     * @param separator the character to use as horizontal separator
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used as the horizontal separator in tables or layouts.
     *
     * @param separator the character to use as horizontal separator; must not be the null character
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code separator} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setHorizontalSeparator(char separator) {
        if (separator == '\0') {
            throw new IllegalArgumentException("Horizontal separator cannot be null character.");
        }
        coreState.horizontalSeparator = separator;
        return (T) this;
    }

    /**
     * Sets the character used for the corner piece in tables or box layouts.
     *
     * @param cornerPiece the character to use as the table corner piece
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used for the corner piece in tables or box layouts.
     *
     * @param cornerPiece the character to use as the table corner piece
     * @return this {@code Setters} instance for chainable calls
     */
    /**
     * Sets the character used for the corner piece in tables or box layouts.
     *
     * @param cornerPiece the character to use as the table corner piece; must not be the null character
     * @return this {@code Setters} instance for chainable calls
     * @throws IllegalArgumentException if {@code cornerPiece} is the null character
     */
    @SuppressWarnings("unchecked")
    public T setTableCornerPiece(char cornerPiece) {
        if (cornerPiece == '\0') {
            throw new IllegalArgumentException("Table corner piece cannot be null character.");
        }
        coreState.tableCornerPiece = cornerPiece;
        return (T) this;
    }
}
