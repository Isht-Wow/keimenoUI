package wow.ishit.v1_stable_keimenoUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Formatter {
    private StringBuilder buffer = new StringBuilder();

    // ------------------ HEADER METHOD ------------------
    // ------------------ HEADER METHOD ------------------
    public Formatter header(String title) {
        if (title == null)
            title = "";

        // Top filler line
        String top = String.valueOf(filler).repeat(width);
        buffer.append(applyColour(top, fillColour)).append("\n");

        // Centered title line
        int visibleTitleLength = visible(title);
        int totalPadding = Math.max(0, width - visibleTitleLength);
        int leftPad = totalPadding / 2;
        int rightPad = totalPadding - leftPad;

        String centeredTitle = String.valueOf(filler).repeat(leftPad)
                + title
                + String.valueOf(filler).repeat(rightPad);
        buffer.append(applyColour(centeredTitle, contentColour)).append("\n");

        // Bottom filler line
        buffer.append(applyColour(top, fillColour)).append("\n");

        return this; // chainable
    }

    public void print() {
        System.out.println(buffer);
    }

    public void clearBuffer() {
        buffer = new StringBuilder();
    }

    // ------------------ ROOKIE SETTINGS ------------------
    private int width = 100;
    private String border = "||";
    private char filler = ' ';
    private Alignment alignment = Alignment.LEFT;
    private boolean ansiSupport = false;
    private String contentColour = "";
    private String fillColour = "";

    // ------------------ AMATEUR SETTINGS ------------------
    private String borderColour = "";
    private int leftIndent = 0;
    private int rightIndent = 0;
    private char leftIndentChar = ' ';
    private char rightIndentChar = ' ';
    private double aspectRatio = 0;

    // ------------------ VETERAN SETTINGS ------------------
    private String leftIndentColour = "";
    private String rightIndentColour = "";
    private char leftPaddingChar = 0;
    private char rightPaddingChar = 0;
    private String leftPaddingColour = "";
    private String rightPaddingColour = "";
    private boolean warpEnabled = false;
    private int wrapWidth = 100; // width to wrap if warpEnabled
    private double CharHeightRatio = 2;

    // ------------------ CHAINABLE SETTERS ------------------
    public Formatter setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
        return this;
    }

    public Formatter setWidth(int width) {
        this.width = width;
        return this;
    }

    public Formatter setBorder(String border) {
        this.border = border;
        return this;
    }

    public Formatter setFiller(char filler) {
        this.filler = filler;
        return this;
    }

    public Formatter setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public Formatter setAnsiSupport(boolean enabled) {
        this.ansiSupport = enabled;
        return this;
    }

    public Formatter setContentColour(Colour colour) {
        this.contentColour = colour.getCode();
        return this;
    }

    public Formatter setFillColour(Colour colour) {
        this.fillColour = colour.getCode();
        return this;
    }

    public Formatter setBorderColour(Colour colour) {
        if (colour != null)
            this.borderColour = colour.getCode();
        return this;
    }

    public Formatter leftIndent(int spaces) {
        this.leftIndent = Math.max(0, spaces);
        return this;
    }

    public Formatter rightIndent(int spaces) {
        this.rightIndent = Math.max(0, spaces);
        return this;
    }

    public Formatter leftIndentChar(char c) {
        this.leftIndentChar = c;
        return this;
    }

    public Formatter rightIndentChar(char c) {
        this.rightIndentChar = c;
        return this;
    }

    public Formatter setImageAspectRatio(double ratio) {
        this.aspectRatio = ratio;
        return this;
    }

    public Formatter leftIndentColour(Colour colour) {
        if (colour != null)
            this.leftIndentColour = colour.getCode();
        return this;
    }

    public Formatter rightIndentColour(Colour colour) {
        if (colour != null)
            this.rightIndentColour = colour.getCode();
        return this;
    }

    public Formatter leftPaddingChar(char c) {
        this.leftPaddingChar = c;
        return this;
    }

    public Formatter rightPaddingChar(char c) {
        this.rightPaddingChar = c;
        return this;
    }

    public Formatter leftPaddingColour(Colour colour) {
        if (colour != null)
            this.leftPaddingColour = colour.getCode();
        return this;
    }

    public Formatter rightPaddingColour(Colour colour) {
        if (colour != null)
            this.rightPaddingColour = colour.getCode();
        return this;
    }

    public Formatter enableWarp(boolean enabled) {
        this.warpEnabled = enabled;
        return this;
    }

    public Formatter setWrapWidth(int width) {
        this.wrapWidth = width;
        return this;
    }

    public Formatter setCharHeightRatio(double ratio) {
        this.CharHeightRatio = ratio;
        return this;
    }

    // ------------------ TERMINAL METHOD ------------------
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

    public String apply(String content) {
        if (content == null || content.isEmpty())
            return "";

        String processedContent = content; // keep uncoloured for wrapping

        // Warp first if needed
        if (warpEnabled && visible(processedContent) > wrapWidth) {
            buffer.append(warpText(processedContent));
            return buffer.toString();
        }

        // Normal processing (alignment or manual)
        String line = formatLine(processedContent);
        buffer.append(line).append("\n");
        return buffer.toString();
    }

    // ------------------ HELPER METHODS ------------------
    private String applyColour(String text, String colourCode) {
        if ((colourCode != null && ansiSupport) || text != null || !text.isEmpty())
            return colourCode + text + Colour.RESET.getCode();
        return text;
    }

    private int visible(String text) {
        if (text == null)
            return 0;
        return text.replaceAll("\\u001B\\[[0-9;]*[A-Za-z]", "").length();
    }

    private String warpText(String text) {
        if (!warpEnabled || text == null || text.isEmpty())
            return formatLine(text) + "\n";

        StringBuilder wrapped = new StringBuilder();
        int start = 0;

        while (start < text.length()) {
            int end = Math.min(start + wrapWidth, text.length());
            String chunk = text.substring(start, end); // plain text chunk
            wrapped.append(formatLine(chunk)).append("\n");
            start += wrapWidth;
        }

        return wrapped.toString();
    }
    // ------------------ GETTERS ------------------

    public StringBuilder getBuffer() {
        return buffer;
    }

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

    // Resize image to given width & height
    private BufferedImage resizeImage(BufferedImage original, int width, int height) {
        Image tmp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // Convert BufferedImage to ASCII string
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
}
