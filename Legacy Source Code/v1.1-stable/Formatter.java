package wow.ishit.v1_stable_keimenoUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Formatter
 *
 * <p>
 * A flexible, chainable text formatting utility for terminal output in Java.
 * Designed for Unix-like systems, it supports ANSI colors, text alignment,
 * indentation,
 * borders, filler characters, text warpping, and even ASCII-rendered images.
 * </p>
 *
 * <p>
 * This class maintains an internal {@link StringBuilder} buffer where formatted
 * content is stored. Users can append text or images, customize appearance, and
 * print the buffer to the console.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 * <li>Chainable setters for all configurable properties</li>
 * <li>Text alignment: LEFT, RIGHT, CENTER, or manual</li>
 * <li>Support for ANSI colors (optional)</li>
 * <li>Customizable borders, fillers, and padding characters</li>
 * <li>Indentation and left/right padding with optional color support</li>
 * <li>Text warpping / warp to specified width</li>
 * <li>ASCII image rendering with optional ANSI color support</li>
 * <li>Fail-safe: handles missing images and invalid input gracefully</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * 
 * <pre>
 * Formatter formatter = new Formatter()
 *         .setWidth(80)
 *         .setFiller('-')
 *         .setAlignment(Alignment.CENTER)
 *         .enableWarp(true)
 *         .setWarpWidth(60);
 *
 * formatter.header("Welcome to the App");
 * formatter.loadIntoBuffer("This is some sample text that will be formatted and warpped.");
 * formatter.print();
 * </pre>
 *
 * <p>
 * <b>Note for beginners:</b> This class is designed to be simple and safe. All
 * operations are fail-soft; invalid input or missing resources (e.g., images)
 * will not throw exceptions but print warnings internally and continue.
 * </p>
 *
 * <p>
 * Recommended workflow:
 * </p>
 * <ul>
 * <li>Create a {@code Formatter} instance</li>
 * <li>Configure properties using chainable setters</li>
 * <li>Load text or images into the buffer</li>
 * <li>Call {@link #print()} to output the formatted content</li>
 * <li>Use {@link #clearBuffer()} to reset for new content</li>
 * </ul>
 *
 * <p>
 * <b>Limitations:</b>
 * </p>
 * <ul>
 * <li>Terminal-dependent: designed for standard console output; behavior may
 * vary on non-Unix systems.</li>
 * <li>ANSI color support must be explicitly enabled with
 * {@link #setAnsiSupport(boolean)}.</li>
 * </ul>
 */

public class Formatter {

    private StringBuilder buffer = new StringBuilder();

    public Formatter() {
        resetValues();
    }

    // ------------------ ROOKIE SETTINGS ------------------
    private int width;
    private String border;
    private char filler;
    private Alignment alignment;
    private boolean ansiSupport;
    private String contentColour;
    private String fillColour;

    // ------------------ AMATEUR SETTINGS ------------------
    private char boxFiller;
    private String boxFillColour;
    private String borderColour;
    private int leftIndent;
    private int rightIndent;
    private char leftIndentChar;
    private char rightIndentChar;
    private double aspectRatio;

    // ------------------ VETERAN SETTINGS ------------------
    private String leftIndentColour;
    private String rightIndentColour;
    private char leftPaddingChar;
    private char rightPaddingChar;
    private String leftPaddingColour;
    private String rightPaddingColour;
    private boolean warpEnabled;
    private int warpWidth; // width to warp if warpEnabled
    private double CharHeightRatio;

    /**
     * Resets all formatter properties to their default values.
     *
     * <p>
     * This method restores the formatter to its initial state as if a new instance
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
     * <li>Clearing any customizations before reusing the formatter.</li>
     * <li>Resetting the formatter after printing a section to ensure consistency
     * for the next section.</li>
     * </ul>
     *
     *
     * @return this {@code Formatter} instance for chainable method calls
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

        leftIndentColour = DEFAULT_LEFT_INDENT_COLOUR;
        rightIndentColour = DEFAULT_RIGHT_INDENT_COLOUR;
        leftPaddingChar = DEFAULT_LEFT_PADDING_CHAR;
        rightPaddingChar = DEFAULT_RIGHT_PADDING_CHAR;
        leftPaddingColour = DEFAULT_LEFT_PADDING_COLOUR;
        rightPaddingColour = DEFAULT_RIGHT_PADDING_COLOUR;
        warpEnabled = DEFAULT_WARP_ENABLED;
        warpWidth = DEFAULT_WRAP_WIDTH;
        CharHeightRatio = DEFAULT_CHAR_HEIGHT_RATIO;
    }

    private static final int DEFAULT_WIDTH = 100;
    private static final String DEFAULT_BORDER = "||";
    private static final char DEFAULT_FILLER = ' ';
    private static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;
    private static final boolean DEFAULT_ANSI_SUPPORT = false;
    private static final String DEFAULT_CONTENT_COLOUR = Colour.NONE.getCode();
    private static final String DEFAULT_FILL_COLOUR = Colour.NONE.getCode();

    private static final char DEFAULT_BOX_FILLER = '-';
    private static final String DEFAULT_BOX_FILLER_COLOUR = Colour.NONE.getCode();
    private static final String DEFAULT_BORDER_COLOUR = Colour.NONE.getCode();
    private static final int DEFAULT_LEFT_INDENT = 0;
    private static final int DEFAULT_RIGHT_INDENT = 0;
    private static final char DEFAULT_LEFT_INDENT_CHAR = 0;
    private static final char DEFAULT_RIGHT_INDENT_CHAR = 0;
    private static final double DEFAULT_ASPECT_RATIO = 0;

    private static final String DEFAULT_LEFT_INDENT_COLOUR = Colour.NONE.getCode();
    private static final String DEFAULT_RIGHT_INDENT_COLOUR = Colour.NONE.getCode();
    private static final char DEFAULT_LEFT_PADDING_CHAR = 0;
    private static final char DEFAULT_RIGHT_PADDING_CHAR = 0;
    private static final String DEFAULT_LEFT_PADDING_COLOUR = Colour.NONE.getCode();
    private static final String DEFAULT_RIGHT_PADDING_COLOUR = Colour.NONE.getCode();
    private static final boolean DEFAULT_WARP_ENABLED = false;
    private static final int DEFAULT_WRAP_WIDTH = 100;
    private static final double DEFAULT_CHAR_HEIGHT_RATIO = 2;

    // ------------------ CHAINABLE SETTERS ------------------
    /**
     * Sets the internal {@link StringBuilder} buffer used by this formatter.
     *
     * <p>
     * Replacing the buffer allows advanced users to supply a custom buffer
     * or reuse an existing one. The buffer will be used for all subsequent
     * formatting operations.
     * </p>
     *
     * @param buffer the new {@link StringBuilder} buffer
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
        return this;
    }

    /**
     * Sets the width of the formatter.
     *
     * <p>
     * This defines the total width for alignment, padding, borders, and text
     * warpping.
     * </p>
     * 
     * @param width the desired width in characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the border string displayed at the edges of formatted lines.
     *
     * @param border the border string; can be empty or null for no border
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setBorder(String border) {
        this.border = border;
        return this;
    }

    /**
     * Sets the filler character used for alignment padding.
     *
     * @param filler the character to repeat for empty space
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setFiller(char filler) {
        this.filler = filler;
        return this;
    }

    /**
     * Sets the alignment for text in each line.
     *
     * @param alignment the desired {@link Alignment} (LEFT, RIGHT, CENTER, NONE)
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setAlignment(Alignment alignment) {
        this.alignment = alignment;
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
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setAnsiSupport(boolean enabled) {
        this.ansiSupport = enabled;
        return this;
    }

    /**
     * Sets the content color using a {@link Colour}.
     *
     * @param colour the colour to apply to text content
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setContentColour(Colour colour) {
        this.contentColour = colour.getCode();
        return this;
    }

    /**
     * Sets the filler color using a {@link Colour}.
     *
     * @param colour the colour to apply to filler characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setFillColour(Colour colour) {
        this.fillColour = colour.getCode();
        return this;
    }


    /**
     * Sets the character used for filling the box.
     *
     * @param boxFiller the character to use for filling the box
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setBoxFiller(char boxFiller) {
        this.boxFiller = boxFiller;
        return this;
    }

    /**
     * Sets the color for the box filler.
     * @param colour the colour to apply to the box filler
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setBoxFillColour(Colour colour) {
        if (colour != null)
            this.boxFillColour = colour.getCode();
        else 
            this.boxFillColour = DEFAULT_BOX_FILLER_COLOUR;
        return this;
    }

    /**
     * Sets the border color using a {@link Colour}.
     *
     * @param colour the colour to apply to borders
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setBorderColour(Colour colour) {
        if (colour != null)
            this.borderColour = colour.getCode();
        return this;
    }

    /**
     * Sets the number of spaces to indent from the left margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setLeftIndent(int spaces) {
        this.leftIndent = Math.max(0, spaces);
        return this;
    }

    /**
     * Sets the number of spaces to indent from the right margin.
     *
     * @param spaces the number of spaces (minimum 0)
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setRightIndent(int spaces) {
        this.rightIndent = Math.max(0, spaces);
        return this;
    }

    /**
     * Sets the character used for left indentation.
     *
     * @param c the character to repeat for left indentation
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setLeftIndentChar(char c) {
        this.leftIndentChar = c;
        return this;
    }

    /**
     * Sets the character used for right indentation.
     *
     * @param c the character to repeat for right indentation
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setRightIndentChar(char c) {
        this.rightIndentChar = c;
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
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setImageAspectRatio(double ratio) {
        this.aspectRatio = ratio;
        return this;
    }

    /**
     * Sets the color for left indentation.
     *
     * @param colour the colour to apply to the left indent characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setLeftIndentColour(Colour colour) {
        if (colour != null)
            this.leftIndentColour = colour.getCode();
        return this;
    }

    /**
     * Sets the color for right indentation.
     *
     * @param colour the colour to apply to the right indent characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setRightIndentColour(Colour colour) {
        if (colour != null)
            this.rightIndentColour = colour.getCode();
        return this;
    }

    public Formatter setLeftPaddingChar(char c) {
        /**
         * Sets the character used for left padding (inside the indented space).
         *
         * @param c the left padding character
         * @return this {@code Formatter} instance for chainable calls
         */
        this.leftPaddingChar = c;
        return this;
    }

    /**
     * Sets the character used for right padding (inside the indented space).
     *
     * @param c the right padding character
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setRightPaddingChar(char c) {
        this.rightPaddingChar = c;
        return this;
    }

    /**
     * Sets the color for left padding.
     *
     * @param colour the colour to apply to the left padding characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setLeftPaddingColour(Colour colour) {
        if (colour != null)
            this.leftPaddingColour = colour.getCode();
        return this;
    }

    /**
     * Sets the color for right padding.
     *
     * @param colour the colour to apply to the right padding characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setRightPaddingColour(Colour colour) {
        if (colour != null)
            this.rightPaddingColour = colour.getCode();
        return this;
    }

    /**
     * Enables or disables text warpping.
     *
     * @param enabled true to enable warp , false to disable
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter enableWarp(boolean enabled) {
        this.warpEnabled = enabled;
        return this;
    }

    /**
     * Sets the wrap width for text when warp mode is enabled.
     *
     * @param width the maximum line width for wrapping
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setWarpWidth(int width) {
        this.warpWidth = width;
        return this;
    }

    /**
     * Sets the character height ratio used for ASCII image rendering.
     *
     * @param ratio the ratio of height to width for terminal characters
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter setCharHeightRatio(double ratio) {
        this.CharHeightRatio = ratio;
        return this;
    }

    // ------------------ HEADER METHOD ------------------
    /**
     * Adds a formatted header section to the internal buffer.
     *
     * <p>
     * The header consists of three lines:
     * <ul>
     * <li>A top filler line using the current {@link #filler} character and
     * {@link #fillColour}.</li>
     * <li>A centered title line with the given {@code title} string, padded
     * according to the
     * current {@link #width} and {@link #alignment}, and colored with
     * {@link #contentColour}.</li>
     * <li>A bottom filler line identical to the top line.</li>
     * </ul>
     * </p>
     *
     * <p>
     * If {@code title} is {@code null}, it is treated as an empty string. The
     * method
     * is chainable and returns the same {@code Formatter} instance.
     * </p>
     *
     * <p>
     * This method does <b>not</b> print the header immediately; it only appends it
     * to the internal buffer. Use {@link #print()} to output the content.
     * </p>
     *
     * @param title the text to display in the header; if {@code null}, an empty
     *              string is used
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter header(String title) {
        if (title == null)
            title = "";

        String colouredBorder = applyColour(border, borderColour);
        String colouredBoxFiller = applyColour(String.valueOf(boxFiller).repeat(width), boxFillColour);
        // Top filler line
    
        String box = colouredBorder + colouredBoxFiller + colouredBorder;
        

        // Centered title line
        int visibleTitleLength = visible(title);
        int totalPadding = Math.max(0, width - visibleTitleLength);
        int leftPad = totalPadding / 2;
        int rightPad = totalPadding - leftPad;

        String colouredLeftFiller = applyColour(String.valueOf(filler).repeat(leftPad), fillColour);
        String colouredRightFiller = applyColour(String.valueOf(filler).repeat(rightPad), fillColour);
        String centeredTitle = colouredBorder + colouredLeftFiller
                + applyColour(title, contentColour)
                + colouredRightFiller + colouredBorder;

        buffer.append(box).append("\n");
        buffer.append(centeredTitle).append("\n");
        buffer.append(box).append("\n");

        return this; // chainable
    }

    /**
     * Prints the current contents of the internal buffer to the console.
     *
     * <p>This method outputs all accumulated formatted text, headers, and
     * ASCII images stored in the internal {@link StringBuilder} buffer. It
     * does not modify the buffer.</p>
     */
    public void print() {
        System.out.println(buffer);
    }

    /**
     * Clears the internal {@link StringBuilder} buffer, removing all accumulated content.
     *
     * <p>After calling this method, the buffer will be empty, and subsequent
     * formatting operations will start fresh.</p>
     */
    public void clearBuffer() {
        buffer = new StringBuilder();
    }

    // ------------------ TERMINAL METHOD ------------------
    /**
     * Formats a single line of content with alignment, indentation, padding, and borders.
     *
     * <p>
     * Applies the current formatter settings (alignment, indent, padding, filler, borders, and colors)
     * to the given content and returns the resulting string. This method is used internally
     * to generate each formatted line.
     * </p>
     *
     * @param content the text to format for this line
     * @return the formatted line as a string
     */
    private String formatLine(String content) {
        StringBuilder line = new StringBuilder();

        // Left indent & padding
        if (leftIndent > 0)
            line.append(applyColour(String.valueOf(leftIndentChar).repeat(leftIndent), leftIndentColour));
        if (leftPaddingChar != 0)
            line.append(applyColour(String.valueOf(leftPaddingChar).repeat(leftIndent), leftPaddingColour));

        // Alignment padding
        int contentLen = visible(content);
        int padding = Math.max(0, width - contentLen - leftIndent - rightIndent);
        String leftGap = "", rightGap = "";

        switch (alignment) {
            case LEFT:
                rightGap = String.valueOf(filler).repeat(padding);
                break;
            case RIGHT:
                leftGap = String.valueOf(filler).repeat(padding);
                break;
            case CENTER:
                int leftPad = padding / 2;
                int rightPad = padding - leftPad;
                leftGap = String.valueOf(filler).repeat(leftPad);
                rightGap = String.valueOf(filler).repeat(rightPad);
                break;
            case NONE:
                rightGap = String.valueOf(filler).repeat(padding);
                break; // fully manual
        }

        leftGap = applyColour(leftGap, fillColour);
        rightGap = applyColour(rightGap, fillColour);

        line.append(leftGap);
        line.append(applyColour(content, contentColour));
        line.append(rightGap);

        // Right indent & padding
        if (rightPaddingChar != 0)
            line.append(applyColour(String.valueOf(rightPaddingChar).repeat(rightIndent), rightPaddingColour));
        if (rightIndent > 0)
            line.append(applyColour(String.valueOf(rightIndentChar).repeat(rightIndent), rightIndentColour));

        // Borders
        if (border != null && !border.isEmpty())
            line.append(applyColour(border, borderColour));

        return line.toString();
    }

    /**
     * Loads the given content into the internal buffer with formatting applied.
     *
     * <p>
     * If warp mode is enabled and the content exceeds the warp width, the content is wrapped
     * into multiple lines automatically. Otherwise, the content is formatted as a single line.
     * </p>
     *
     * @param content the text to add to the buffer
     * @return the current buffer contents as a string
     */
    public String loadIntoBuffer(String content) {
        if (content == null || content.isEmpty())
            return "";

        String processedContent = content; // keep uncoloured for warpping

        // Warp first if needed
        if (warpEnabled && visible(processedContent) > warpWidth) {
            buffer.append(warpText(processedContent));
            return buffer.toString();
        }

        // Normal processing (alignment or manual)
        String line = formatLine(processedContent);
        buffer.append(line).append("\n");
        return buffer.toString();
    }

    // ------------------ HELPER METHODS ------------------
    /**
     * Applies the given ANSI color code to the provided text if ANSI support is enabled.
     *
     * <p>
     * If ANSI support is enabled and a valid color code is provided, the text will be wrapped
     * with the color code and reset sequence. Otherwise, the text is returned unmodified.
     * </p>
     *
     * @param text the text to colorize
     * @param colourCode the ANSI color code to apply
     * @return the colorized text if applicable, otherwise the original text
     */
    private String applyColour(String text, String colourCode) {
        if ((colourCode != null && ansiSupport) || text != null || !text.isEmpty())
            return colourCode + text + Colour.RESET.getCode();
        return text;
    }

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
    private int visible(String text) {
        if (text == null)
            return 0;
        return text.replaceAll("\\u001B\\[[0-9;]*[A-Za-z]", "").length();
    }

    /**
     * Wraps the given text into multiple lines if warp mode is enabled.
     *
     * <p>
     * Splits the text into substrings of at most {@code warpWidth} characters,
     * formatting each as a separate line.
     * </p>
     *
     * @param text the text to wrap
     * @return the wrapped and formatted text as a string
     */
    private String warpText(String text) {
        if (!warpEnabled || text == null || text.isEmpty())
            return formatLine(text) + "\n";

        StringBuilder warpped = new StringBuilder();
        int start = 0;

        while (start < text.length()) {
            int end = Math.min(start + warpWidth, text.length());
            String chunk = text.substring(start, end); // plain text chunk
            warpped.append(formatLine(chunk)).append("\n");
            start += warpWidth;
        }

        return warpped.toString();
    }

    /**
     * Appends an ASCII-art representation of an image to the internal buffer.
     *
     * <p>
     * The image at the specified path is loaded, resized to fit {@code maxWidth},
     * converted to ASCII characters, and optionally colorized using ANSI colors.
     * If the image cannot be loaded, a warning message is appended instead.
     * </p>
     *
     * @param path the file path to the image
     * @param maxWidth the maximum width in characters for the ASCII art
     * @return this {@code Formatter} instance for chainable calls
     */
    public Formatter appendImage(String path, int maxWidth) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // Use user-set image aspect ratio or natural
            double imgRatio = aspectRatio == 0 ? ((double) imgHeight / imgWidth) : aspectRatio;

            // Compute target height based on width and terminal char ratio
            int targetHeight = (int) (maxWidth * imgRatio / CharHeightRatio);

            BufferedImage resized = resizeImage(image, maxWidth, targetHeight);
            buffer.append(convertToAscii(resized, ansiSupport));
        } catch (IOException e) {
            buffer.append("[Image could not be loaded: ").append(path).append("]\n");
        }

        return this;
    }

    /**
     * Resizes a {@link BufferedImage} to the specified width and height.
     *
     * @param original the original image to resize
     * @param width the target width in pixels
     * @param height the target height in pixels
     * @return the resized {@link BufferedImage}
     */
    private BufferedImage resizeImage(BufferedImage original, int width, int height) {
        Image tmp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    /**
     * Converts a {@link BufferedImage} to an ASCII-art string representation.
     *
     * <p>
     * Each pixel is mapped to an ASCII character based on brightness. If color is enabled,
     * ANSI color codes are used to colorize each character.
     * </p>
     *
     * @param image the image to convert
     * @param useColor true to use ANSI color codes, false for plain ASCII
     * @return the ASCII-art string
     */
    private String convertToAscii(BufferedImage image, boolean useColor) {
        final char[] ASCII_CHARS = { '.', ',', ':', ';', '+', '*', '?', '%', 'S', '#', '@' };
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            result.append(border); // optional left border
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                Color c = new Color(rgb);

                double brightness = (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue()) / 255.0;
                char asciiChar = ASCII_CHARS[(int) (brightness * (ASCII_CHARS.length - 1))];

                if (useColor && ansiSupport) {
                    result.append(String.format("\u001B[38;2;%d;%d;%dm%c\u001B[0m",
                            c.getRed(), c.getGreen(), c.getBlue(), asciiChar));
                } else {
                    result.append(asciiChar);
                }
            }
            result.append(border).append("\n"); // optional right border
        }

        return result.toString();
    }

    // ------------------ GETTERS ------------------

    /**
     * Returns the internal StringBuilder buffer containing formatted content.
     *
     * @return the internal buffer
     */
    public StringBuilder getBuffer() {
        return buffer;
    }

    /**
     * Gets the current width setting for the formatter.
     *
     * @return the width in characters
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the character used for filling the box.
     *
     * @return the box filler character
     */
    public char getBoxFiller(){
        return boxFiller;
    }

    /**
     * Gets the current border string.
     *
     * @return the border string
     */
    public String getBorder() {
        return border;
    }

    /**
     * Gets the filler character used for alignment.
     *
     * @return the filler character
     */
    public char getFiller() {
        return filler;
    }

    /**
     * Gets the current text alignment setting.
     *
     * @return the current {@link Alignment}
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     * Returns whether ANSI color support is enabled.
     *
     * @return true if ANSI color is enabled; false otherwise
     */
    public boolean getAnsiSupport() {
        return ansiSupport;
    }

    /**
     * Gets the color code used for content text.
     *
     * @return the ANSI color code for content
     */
    public String getContentColour() {
        return contentColour;
    }

    /**
     * Gets the color code used for filler characters.
     *
     * @return the ANSI color code for fillers
     */
    public String getFillColour() {
        return fillColour;
    }

    /**
     * Gets the color code used for borders.
     *
     * @return the ANSI color code for borders
     */
    public String getBorderColour() {
        return borderColour;
    }

    /**
     * Gets the number of spaces used for left indentation.
     *
     * @return the left indent size
     */
    public int getLeftIndent() {
        return leftIndent;
    }

    /**
     * Gets the number of spaces used for right indentation.
     *
     * @return the right indent size
     */
    public int rightIndent() {
        return rightIndent;
    }

    /**
     * Gets the character used for left indentation.
     *
     * @return the left indent character
     */
    public char leftIndentChar() {
        return leftIndentChar;
    }

    /**
     * Gets the character used for right indentation.
     *
     * @return the right indent character
     */
    public char rightIndentChar() {
        return rightIndentChar;
    }

    /**
     * Gets the current image aspect ratio setting.
     *
     * @return the aspect ratio for images
     */
    public double getImageAspectRatio() {
        return aspectRatio;
    }

    /**
     * Gets the color code for left indentation.
     *
     * @return the ANSI color code for left indent
     */
    public String getLeftIndentColour() {
        return leftIndentColour;
    }

    /**
     * Gets the color code for right indentation.
     *
     * @return the ANSI color code for right indent
     */
    public String getRightIndentColour() {
        return rightIndentColour;
    }

    /**
     * Gets the character used for left padding.
     *
     * @return the left padding character
     */
    public char getLeftPaddingChar() {
        return leftPaddingChar;
    }

    /**
     * Gets the character used for right padding.
     *
     * @return the right padding character
     */
    public char getRightPaddingChar() {
        return rightPaddingChar;
    }

    /**
     * Gets the color code for left padding.
     *
     * @return the ANSI color code for left padding
     */
    public String getLeftPaddingColour() {
        return leftPaddingColour;
    }

    /**
     * Gets the color code for right padding.
     *
     * @return the ANSI color code for right padding
     */
    public String getRightPaddingColour() {
        return rightPaddingColour;
    }

    /**
     * Returns whether text wrapping (warp) is enabled.
     *
     * @return true if wrap/warp is enabled; false otherwise
     */
    public boolean getWarpStatus() {
        return warpEnabled;
    }

    /**
     * Gets the maximum width for text wrapping.
     *
     * @return the wrap width
     */
    public int getWarpWidth() {
        return warpWidth;
    }

    /**
     * Gets the character height ratio used for ASCII image rendering.
     *
     * @return the character height ratio
     */
    public double getCharHeightRatio() {
        return CharHeightRatio;
    }
}
