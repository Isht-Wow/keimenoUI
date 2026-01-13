package wow.ishit.v2_stable_keimenoUI;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The {@code AsciiArt} class provides functionality to convert images into ASCII art representations.
 * It supports resizing images to fit a specified width, converting pixel brightness to ASCII characters,
 * and optionally colorizing the output using ANSI color codes. Additionally, it can play sequences of images
 * as frames, optionally synchronized with audio playback.
 * <p>
 * Usage example:
 * <pre>
 *     AsciiArt asciiArt = new AsciiArt();
 *     CoreState coreState = new CoreState();
 *     asciiArt.applySettings(coreState);
 *     asciiArt.appendImage("path/to/image.png", 80);
 *     coreState.flush();
 *     asciiArt.playFrames("path/to/frames/", 80);
 * </pre>
 */
public class AsciiArt extends Settings<AsciiArt>{
    /**
     * The current core state containing configuration settings such as aspect ratio,
     * ANSI color support, border characters, maximum frame rate, audio playback options,
     * and an internal buffer to accumulate ASCII art output.
     * <p>
     * This field is used internally by the {@code AsciiArt} methods to control rendering
     * behavior and to store generated ASCII art strings.
     * <p>
     * Note: This field should not be null during normal operation.
     */
    public CoreState coreState;
    
    /**
     * Constructs a new {@code AsciiArt} instance and initializes it with default settings.
     * The default settings are provided by a new {@link CoreState} object.
     */
    public AsciiArt() {
        this(new CoreState());
    }

    /**
     * Constructs a new {@code AsciiArt} instance with the specified {@link CoreState} settings.
     * These settings control aspects such as aspect ratio, ANSI color support, border characters,
     * maximum frame rate, and audio playback options.
     *
     * @param coreState the {@code CoreState} object containing configuration settings; must not be null
     */
    public AsciiArt(CoreState coreState) {
        this.coreState = coreState;
        super(coreState);
    }

    /**
     * Loads an image from the specified file path, resizes it to fit within the given maximum width,
     * converts it to an ASCII art representation, and appends the result to the internal buffer.
     * The height is automatically calculated based on the aspect ratio and character height ratio.
     * If the image cannot be loaded, a warning message is appended instead.
     * <p>
     * This method is useful for generating ASCII art from static images and accumulating
     * the result in the core state's buffer for later output.
     * <p>
     * Example usage:
     * <pre>
     *     CoreState state = new CoreState();
     *     AsciiArt ascii = new AsciiArt(state);
     *     ascii.appendImage("image.png", 100);
     *     state.flush();
     * </pre>
     *
     * @param path     the file path to the image to be converted; must not be null
     * @param maxWidth the maximum width in characters for the ASCII art output; must be positive
     * @return the current {@link CoreState} instance containing the updated buffer with ASCII art
     * @throws NullPointerException if {@code path} is null
     * @throws IllegalArgumentException if {@code maxWidth} is not positive
     */
    public CoreState appendImage(String path, int maxWidth) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // Use user-set image aspect ratio or natural
            double imgRatio = coreState.aspectRatio == 0 ? ((double) imgHeight / imgWidth) : coreState.aspectRatio;

            // Compute target height based on width and terminal char ratio
            int targetHeight = (int) (maxWidth * imgRatio / coreState.charHeightRatio);

            BufferedImage resized = resizeImage(image, maxWidth, targetHeight);
            coreState.buffer.append(convertToAscii(resized, coreState.ansiSupport));
        } catch (IOException e) {
            coreState.buffer.append("[Image could not be loaded: ").append(path).append("]\n");
        }

        return coreState;
    }

    /**
     * Plays a sequence of image frames located in the specified folder path as ASCII art animations.
     * Frames are loaded, converted, and displayed at a rate controlled by {@code maxFrameRate} in the current settings.
     * If audio playback is enabled in the settings, an audio file named "Audio.wav" in the same folder will be played.
     * If the folder does not exist or contains no valid image files, appropriate warnings are appended to the buffer.
     * <p>
     * This method blocks while playing frames and manages timing to maintain a consistent frame rate.
     * It supports PNG, JPG, and JPEG image formats for frames.
     * <p>
     * Example usage:
     * <pre>
     *     AsciiArt ascii = new AsciiArt();
     *     ascii.playFrames("path/to/animation/", 80);
     * </pre>
     *
     * @param folderPath the path to the folder containing image frames for the animation; must not be null
     * @param maxWidth   the maximum width in characters for each ASCII art frame; must be positive
     * @throws NullPointerException if {@code folderPath} is null
     * @throws IllegalArgumentException if {@code maxWidth} is not positive
     */
    public void playFrames(String folderPath, int maxWidth) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            coreState.buffer.append("[Folder does not exist: ").append(folderPath).append("]\n");
            return;
        }

        // Filter for image files
        File[] imageFiles = folder.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");
        });

        if (imageFiles == null || imageFiles.length == 0) {
            coreState.buffer.append("[No image files found in folder: ").append(folderPath).append("]\n");
            return;
        }

        // Sort by name ascending
        java.util.Arrays.sort(imageFiles, (a, b) -> a.getName().compareTo(b.getName()));
        final long frameDurationNanos = 1_000_000_000L / coreState.maxFrameRate;
        if (coreState.audioEnabled) {
            AudioManager audio = new AudioManager();
            audio.applySettings(coreState);
            audio.startPlaying(folderPath, folderPath + "Audio.wav", 0);
        }

        for (File imgFile : imageFiles) {
            long frameStartTime = System.nanoTime();

            appendImage(imgFile.getAbsolutePath(), maxWidth); // use current width
            coreState.rewritingFlush();

            long frameEndTime = System.nanoTime();
            long elapsedNanos = frameEndTime - frameStartTime;
            long sleepNanos = frameDurationNanos - elapsedNanos;

            if (sleepNanos > 0) {
                try {
                    long sleepMillis = sleepNanos / 1_000_000L;
                    int sleepNanosRemainder = (int) (sleepNanos % 1_000_000L);
                    Thread.sleep(sleepMillis, sleepNanosRemainder);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        return;
    }

    /**
     * Resizes a {@link BufferedImage} to the specified width and height.
     * <p>
     * This method uses smooth scaling to preserve image quality.
     * It returns a new {@code BufferedImage} instance of the requested size.
     * <p>
     * Example usage:
     * <pre>
     *     BufferedImage original = ImageIO.read(new File("image.png"));
     *     BufferedImage resized = resizeImage(original, 80, 40);
     * </pre>
     *
     * @param original the original image to resize; must not be null
     * @param width    the target width in pixels; must be positive
     * @param height   the target height in pixels; must be positive
     * @return the resized {@link BufferedImage}
     * @throws NullPointerException if {@code original} is null
     * @throws IllegalArgumentException if {@code width} or {@code height} is not positive
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
     * <p>
     * Each pixel is mapped to an ASCII character based on brightness. If color is
     * enabled, ANSI color codes are used to colorize each character.
     * <p>
     * The ASCII characters used range from light to dark to simulate shading.
     * If {@code useColor} is true and ANSI support is enabled in the core state,
     * the output will include ANSI escape codes for 24-bit RGB coloring.
     * Otherwise, plain ASCII characters are used.
     * <p>
     * Example usage:
     * <pre>
     *     String asciiArt = convertToAscii(image, true);
     * </pre>
     *
     * @param image    the image to convert; must not be null
     * @param useColor true to use ANSI color codes, false for plain ASCII
     * @return the ASCII-art string representing the image
     * @throws NullPointerException if {@code image} is null
     */
    private String convertToAscii(BufferedImage image, boolean useColor) {
        final char[] ASCII_CHARS = { '.', ',', ':', ';', '+', '*', '?', '%', 'S', '#', '@' };
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            result.append(coreState.border); // optional left border
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                Color c = new Color(rgb);

                double brightness = (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue()) / 255.0;
                char asciiChar = ASCII_CHARS[(int) (brightness * (ASCII_CHARS.length - 1))];

                if (useColor && coreState.ansiSupport) {
                    result.append(String.format("\u001B[38;2;%d;%d;%dm%c\u001B[0m",
                            c.getRed(), c.getGreen(), c.getBlue(), asciiChar));
                } else {
                    result.append(asciiChar);
                }
            }
            result.append(coreState.border).append("\n"); // optional right border
        }

        return result.toString();
    }
}
