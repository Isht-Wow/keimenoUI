package wow.ishit.keimenoUI;
import java.util.Arrays;

public class TextFormatter {

    // ----- HEADER METHODS -----
    public static void header(String title) {
        header(title, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour);
    }

    public static void header(String title, char filler) {
        header(title, filler, FormatterSettings.defaultContentColour, FormatterSettings.defaultFillerColour);
    }

    public static void header(String title, String contentColour) {
        header(title, FormatterSettings.defaultFiller, contentColour, FormatterSettings.defaultFillerColour);
    }

    public static void header(String title, char filler, String contentColour) {
        header(title, filler, contentColour, FormatterSettings.defaultFillerColour);
    }

    public static void header(String title, char filler, int width) {
        header(title, filler, FormatterSettings.defaultContentColour, FormatterSettings.defaultFillerColour, width,
                FormatterSettings.defaultBorder);
    }

    public static void header(String title, char filler, String contentColour, int width) {
        header(title, filler, contentColour, FormatterSettings.defaultFillerColour, width,
                FormatterSettings.defaultBorder);
    }

    public static void header(String title, char filler, String contentColour, String fillColour, int width,
            String border) {
        TextFormatter.centerFormatting("", filler, contentColour, fillColour, width, border);
        TextFormatter.centerFormatting(title, FormatterSettings.defaultFiller, contentColour, fillColour, width, border);
        TextFormatter.centerFormatting("", filler, contentColour, fillColour, width, border);
    }

    public static void header(String title, char filler, String contentColour, String fillColour) {
        header(title, filler, contentColour, fillColour, FormatterSettings.defaultWidth,
                FormatterSettings.defaultBorder);
    }

    // ----- PRINT OPTIONS METHODS -----
    public static void printOptions(String[] options) {
        printOptions(options, new String[options.length], new String[options.length]);
    }

    public static void printOptions(String[] options, String contentColour) {
        String[] colours = new String[options.length];
        Arrays.fill(colours, contentColour);
        printOptions(options, colours, new String[options.length]);
    }

    public static void printOptions(String[] options, String[] contentColour) {
        printOptions(options, contentColour, new String[options.length]);
    }

    public static void printOptions(String[] options, String[] contentColour, String[] fillColour) {
        for (int i = 0; i < options.length; i++) {
            TextFormatter.leftFormatting((i + 1) + ". " + options[i], FormatterSettings.defaultFiller, contentColour[i],
                    fillColour[i],
                    FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
        }
    }

    // ----- PUBLIC METHODS -----
    public static String centerFormatting(String content, char filler, String contentColour, String fillColour,
            int width, String border) {
        return alignFormatting(content, filler, contentColour, fillColour, "CENTER", width, border);
    }

    public static String leftFormatting(String content, char filler, String contentColour, String fillColour, int width,
            String border) {
        return alignFormatting(content, filler, contentColour, fillColour, "LEFT", width, border);
    }

    public static String rightFormatting(String content, char filler, String contentColour, String fillColour,
            int width, String border) {
        return alignFormatting(content, filler, contentColour, fillColour, "RIGHT", width, border);
    }

    // ----- CONVENIENCE OVERLOADS -----
    // Default filler, colours, width, and border
    public static String centerFormatting(String content) {
        return centerFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String leftFormatting(String content) {
        return leftFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String rightFormatting(String content) {
        return rightFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    // With filler only
    public static String centerFormatting(String content, char filler) {
        return centerFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String leftFormatting(String content, char filler) {
        return leftFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String rightFormatting(String content, char filler) {
        return rightFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    // With content colour only
    public static String centerFormatting(String content, String contentColour) {
        return centerFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String leftFormatting(String content, String contentColour) {
        return leftFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    public static String rightFormatting(String content, String contentColour) {
        return rightFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth, FormatterSettings.defaultBorder);
    }

    // With filler and content colour
    public static String centerFormatting(String content, char filler, String contentColour) {
        return centerFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                FormatterSettings.defaultBorder);
    }

    public static String leftFormatting(String content, char filler, String contentColour) {
        return leftFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                FormatterSettings.defaultBorder);
    }

    public static String rightFormatting(String content, char filler, String contentColour) {
        return rightFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                FormatterSettings.defaultBorder);
    }

    // ----- NEW CONVENIENCE OVERLOADS FOR CUSTOM WIDTH AND BORDER -----
    public static String centerFormatting(String content, int width, String border) {
        return centerFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width, border);
    }

    public static String leftFormatting(String content, int width, String border) {
        return leftFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width, border);
    }

    public static String rightFormatting(String content, int width, String border) {
        return rightFormatting(content, FormatterSettings.defaultFiller, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width, border);
    }

    public static String centerFormatting(String content, char filler, int width, String border) {
        return centerFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }

    public static String leftFormatting(String content, char filler, int width, String border) {
        return leftFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }

    public static String rightFormatting(String content, char filler, int width, String border) {
        return rightFormatting(content, filler, FormatterSettings.defaultContentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }

    public static String centerFormatting(String content, String contentColour, int width, String border) {
        return centerFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }

    public static String leftFormatting(String content, String contentColour, int width, String border) {
        return leftFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }

    public static String rightFormatting(String content, String contentColour, int width, String border) {
        return rightFormatting(content, FormatterSettings.defaultFiller, contentColour,
                FormatterSettings.defaultFillerColour, width,
                border);
    }
    // ----- ADDITIONAL CONVENIENCE OVERLOADS FOR REMAINING COMBINATIONS -----

    // Filler + Content Colour + Width
    public static String centerFormatting(String content, char filler, String contentColour, int width) {
        return centerFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width,
                FormatterSettings.defaultBorder);
    }

    public static String leftFormatting(String content, char filler, String contentColour, int width) {
        return leftFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width,
                FormatterSettings.defaultBorder);
    }

    public static String rightFormatting(String content, char filler, String contentColour, int width) {
        return rightFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width,
                FormatterSettings.defaultBorder);
    }

    // Filler + Content Colour + Border
    public static String centerFormatting(String content, char filler, String contentColour, String border) {
        return centerFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                border);
    }

    public static String leftFormatting(String content, char filler, String contentColour, String border) {
        return leftFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                border);
    }

    public static String rightFormatting(String content, char filler, String contentColour, String border) {
        return rightFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour,
                FormatterSettings.defaultWidth,
                border);
    }

    // Filler + Content Colour + Width + Border
    public static String centerFormatting(String content, char filler, String contentColour, int width, String border) {
        return centerFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width, border);
    }

    public static String leftFormatting(String content, char filler, String contentColour, int width, String border) {
        return leftFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width, border);
    }

    public static String rightFormatting(String content, char filler, String contentColour, int width, String border) {
        return rightFormatting(content, filler, contentColour, FormatterSettings.defaultFillerColour, width, border);
    }

    // ----- PRIVATE HELPER METHODS -----
    private static String alignFormatting(String content, char filler, String contentColour, String fillColour,
            String alignment, int width, String border) {
        int length = visibleLength(content);
        if (length > width)
            return warpText(content, filler, contentColour, fillColour, alignment, width, border);

        String leftGap = "";
        String rightGap = "";
        int padding = width - length;

        switch (alignment) {
            case "CENTER":
                int leftPadding = padding / 2;
                int rightPadding = padding - leftPadding;
                leftGap = repeat(filler, leftPadding, fillColour);
                rightGap = repeat(filler, rightPadding, fillColour);
                break;
            case "LEFT":
                leftGap = " ";
                rightGap = repeat(filler, padding - 1, fillColour);
                break;
            case "RIGHT":
                leftGap = repeat(filler, padding - 1, fillColour);
                rightGap = " ";
                break;
        }

        content = addColour(content, contentColour);
        String output = border + leftGap + content + rightGap + border;
        if(content != "WARPFLAG!!!"){
            TUIHandler.buffer.append(output + "\n");
        }
        return output;
    }

    private static int visibleLength(String content) {
        return content.replaceAll("\u001B\\[[;\\d]*m", "").length();
    }

    private static String repeat(char filler, int times, String colour) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++)
            sb.append(filler);
        return addColour(sb.toString(), colour);
    }

    public static String addColour(String text, String colour) {
        if (!FormatterSettings.ansiSupport || !validColour(colour))
            return text;
        return colour + text + FormatterSettings.RESET;
    }

    private static boolean validColour(String colour) {
        return FormatterSettings.RED.equals(colour) || FormatterSettings.GREEN.equals(colour)
                || FormatterSettings.YELLOW.equals(colour) ||
                FormatterSettings.BLUE.equals(colour) || FormatterSettings.MAGENTA.equals(colour)
                || FormatterSettings.CYAN.equals(colour) ||
                FormatterSettings.WHITE.equals(colour) || FormatterSettings.RESET.equals(colour) || "".equals(colour);
    }
    private static String warpText(String content, char filler, String contentColour, String fillColour,
            String alignment, int width, String border){
        int length = visibleLength(content);
        for(int i = 0; i < (length/width)+1; i++){
            int end = Math.min((i+1)*width, content.length());
            TextFormatter.alignFormatting(content.substring(i*width, end), filler, contentColour, fillColour, alignment, width, border);
        }
        return "WARPFLAG!!!";
    }
}