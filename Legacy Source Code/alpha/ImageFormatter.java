package wow.ishit.keimenoUI;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFormatter {

    // ASCII characters from light to dark
    private static final char[] ASCII_CHARS = { '.', ',', ':', ';', '+', '*', '?', '%', 'S', '#', '@' };

    /**
     * Converts an image file to ASCII and prints it to the terminal.
     *
     * @param path     Path to the image file
     * @param width    Output width in characters
     * @param height   Output height in characters
     * @param useColor Whether to use ANSI color codes
     * @throws IOException if the file cannot be read
     */
    public static void AsciiArt(String path, int width, int height, boolean useColor) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));

        // Adjust height to account for terminal font aspect ratio
        int adjustedHeight = (int) (height * FormatterSettings.CharHeightRatio); // or / CHAR_HEIGHT_RATIO

        BufferedImage resized = resizeImage(image, width, adjustedHeight);
        printAscii(resized, useColor);
    }

    // Resize the image to given width and height
    private static BufferedImage resizeImage(BufferedImage original, int width, int height) {
        Image tmp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // Print ASCII representation of a BufferedImage
    private static void printAscii(BufferedImage image, boolean useColor) {
        for (int y = 0; y < image.getHeight(); y++) {
            StringBuilder line = new StringBuilder();
            line.append(FormatterSettings.defaultBorder);
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                Color c = new Color(rgb);

                double brightness = (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue()) / 255.0;
                char asciiChar = ASCII_CHARS[(int) (brightness * (ASCII_CHARS.length - 1))];

                if (useColor) {
                    line.append(String.format("\u001B[38;2;%d;%d;%dm%c\u001B[0m",
                            c.getRed(), c.getGreen(), c.getBlue(), asciiChar));
                } else {
                    line.append(asciiChar);
                }
            }
            line.append(FormatterSettings.defaultBorder);
            System.out.println(line);
        }
    }

}