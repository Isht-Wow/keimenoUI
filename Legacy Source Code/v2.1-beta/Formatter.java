package wow.ishit.v2_beta_keimenoUI;

/**
 * Formatter
 *
 * <p>
 * A flexible, chainable text formatting utility for terminal output in Java.
 * Designed for Unix-like systems, it supports ANSI colors, text alignment,
 * indentation, borders, filler characters, text warpping, and even
 * ASCII-rendered images.
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
 * <li>Text warpping / warp to specified coreState.width</li>
 * <li>ASCII image rendering with optional ANSI color support</li>
 * <li>Fail-safe: handles missing images and invalid input gracefully</li>
 * </ul>
 *
 * <h2>Usage</h2>
 * <p>
 * <b>Basic usage example:</b>
 * </p>
 * 
 * <pre>
 * // Create and configure a Formatter
 * Formatter formatter = new Formatter();
 * CoreState coreState = new CoreState();
 *
 * // Add a table to the buffer
 * String[] headers = { "Name", "Score" };
 * String[][] data = { { "Alice", "95" }, { "Bob", "89" } };
 * formatter.table(headers, data);
 *
 * // Add a list menu to the buffer
 * String[] menu = { "1. Start", "2. Settings", "3. Exit" };
 * formatter.listMenu(menu);
 *
 * // Add custom formatted text
 * formatter.formatIntoBuffer("Custom message goes here.");
 * </pre>
 *
 * <p>
 * <b>Note for beginners:</b> This class is designed to be simple and safe. All
 * operations are fail-soft; invalid input or missing resources (e.g., images)
 * will not throw exceptions but print warnings internally and continue.
 * </p>
 * 
 * Formatter is a flexible, chainable text formatting utility for terminal
 * output in Java.
 * <p>
 * It supports features such as ANSI colors, text alignment, indentation,
 * borders, filler characters,
 * text wrapping, and ASCII-rendered images. Formatter maintains an internal
 * buffer for formatted content
 * and exposes methods to append headers, tables, menus, and arbitrary text.
 * </p>
 * <p>
 * Usage is chainable; most methods return the same Formatter instance for
 * fluent calls.
 * </p>
 */
public class Formatter<T extends Formatter<T>> extends Settings<Formatter<T>> {

    /**
     * Constructs a new Formatter using the specified {@code CoreState} settings.
     *
     * @param coreState the CoreState object containing initial formatting settings
     */
    Formatter(CoreState coreState) {
        super(coreState);
    }

    // ------------------ HEADER METHOD ------------------
    /**
     * Appends a formatted header section to the internal buffer.
     * <p>
     * The header consists of a top and bottom filler line and a centered title
     * line.
     * Filler and border characters, as well as colors, are taken from the current
     * CoreState.
     * <br>
     * If {@code title} is {@code null}, it is treated as an empty string.
     * This method does not print to the console; it only appends to the internal
     * buffer.
     * </p>
     * 
     * @param title the text to display in the header; if {@code null}, an empty
     *              string is used
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T header(String title) {
        if (title == null)
            title = "";

        String colouredBorder = coreState.applyColour(coreState.border, coreState.borderColour,
                coreState.borderBackgroundColour);
        String colouredBoxFiller = coreState.applyColour(
                coreState.repeat(String.valueOf(coreState.headerBoxFiller), coreState.width),
                coreState.headerBoxFillColour, coreState.headerBoxFillBackgroundColour);
        // Top filler line

        String box = colouredBorder + colouredBoxFiller + colouredBorder;

        // Centered title line
        int visibleTitleLength = coreState.visible(title);
        int totalPadding = Math.max(0, coreState.width - visibleTitleLength);
        int leftPad = totalPadding / 2;
        int rightPad = totalPadding - leftPad;

        String colouredLeftFiller = coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), leftPad),
                coreState.fillColour, coreState.fillBackgroundColour);
        String colouredRightFiller = coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), rightPad),
                coreState.fillColour, coreState.fillBackgroundColour);
        String centeredTitle = colouredBorder + colouredLeftFiller
                + coreState.applyColour(title, coreState.contentColour, coreState.contentBackgroundColour)
                + colouredRightFiller + colouredBorder;

        coreState.buffer.append(box).append("\n");
        coreState.buffer.append(centeredTitle).append("\n");
        coreState.buffer.append(box).append("\n");

        return (T) this; // chainable
    }

    /**
     * Appends a formatted progress bar to the internal buffer.
     * <p>
     * The progress bar visually represents the completion percentage using filled
     * and empty blocks, and displays the numeric percentage at the end.
     * <br>
     * The bar width is determined by the Formatter's width or warpWidth if wrapping
     * is enabled.
     * </p>
     * 
     * @param progress the progress value in the range [0.0, 1.0], where 0.0 is 0%
     *                 and 1.0 is 100%
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T progressBar(double progress) {
        int barWidth = coreState.warpEnabled ? coreState.warpWidth : coreState.width;
        String footer = coreState.applyColour(" " + (int) (progress * 100) + "/100", coreState.contentColour,
                coreState.contentBackgroundColour);
        int availableWidth = barWidth - coreState.visible(footer) - 4;

        if (availableWidth < 1)
            availableWidth = 1; // minimum

        int filledCount = (int) Math.round(progress * availableWidth);
        int emptyCount = availableWidth - filledCount;

        StringBuilder bar = new StringBuilder();
        bar.append(coreState.applyColour(" [", Colour.RESET, BackgroundColour.NONE));
        bar.append(coreState.applyColour(coreState.repeat("█", filledCount), coreState.fillColour,
                coreState.fillBackgroundColour));
        bar.append(coreState.applyColour(coreState.repeat("░", emptyCount), coreState.fillColour,
                coreState.fillBackgroundColour));
        bar.append(coreState.applyColour("] ", Colour.RESET, BackgroundColour.NONE));
        bar.append(footer);

        formatIntoBuffer(bar.toString());
        return (T) this;
    }

    // ------------------ TERMINAL METHOD ------------------
    /**
     * Formats a single line of content with alignment, indentation, padding, and
     * borders.
     * <p>
     * Applies the current formatter settings (alignment, indent, padding, filler,
     * borders, and colors)
     * to the given content and returns the resulting string.
     * <br>
     * This method is used internally to generate each formatted line.
     * </p>
     * 
     * @param content the text to format for this line
     * @return the formatted line as a string
     */
    private String formatLine(String content) {
        StringBuilder line = new StringBuilder();

        // Left indent & padding
        if (coreState.leftIndent > 0)
            line.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.leftIndentChar), coreState.leftIndent),
                    coreState.leftIndentColour, coreState.leftIndentBackgroundColour));
        if (coreState.leftPaddingChar != 0)
            line.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.leftPaddingChar), coreState.leftIndent),
                    coreState.leftPaddingColour, coreState.leftPaddingBackgroundColour));
        // Alignment padding
        int contentLen = coreState.visible(content);
        int padding = Math.max(0, coreState.width - contentLen - coreState.leftIndent - coreState.rightIndent);
        String leftGap = "", rightGap = "";

        switch (coreState.alignment) {
            case LEFT:
                rightGap = coreState.repeat(String.valueOf(coreState.filler), padding);
                break;
            case RIGHT:
                leftGap = coreState.repeat(String.valueOf(coreState.filler), padding);
                break;
            case CENTER:
                int leftPad = padding / 2;
                int rightPad = padding - leftPad;
                leftGap = coreState.repeat(String.valueOf(coreState.filler), leftPad);
                rightGap = coreState.repeat(String.valueOf(coreState.filler), rightPad);
                break;
            case NONE:
                rightGap = coreState.repeat(String.valueOf(coreState.filler), padding);
                break; // fully manual
        }
        if (coreState.border != null && !coreState.border.isEmpty())
            line.append(
                    coreState.applyColour(coreState.border, coreState.borderColour, coreState.borderBackgroundColour));

        leftGap = coreState.applyColour(leftGap, coreState.fillColour, coreState.fillBackgroundColour);
        rightGap = coreState.applyColour(rightGap, coreState.fillColour, coreState.fillBackgroundColour);
        line.append(leftGap);
        line.append(coreState.applyColour(content, coreState.contentColour, coreState.contentBackgroundColour));
        line.append(rightGap);

        // Right indent & padding
        if (coreState.rightPaddingChar != 0)
            line.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.rightPaddingChar), coreState.rightIndent),
                    coreState.rightPaddingColour, coreState.rightPaddingBackgroundColour));
        if (coreState.rightIndent > 0)
            line.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.rightIndentChar), coreState.rightIndent),
                    coreState.rightIndentColour, coreState.rightIndentBackgroundColour));

        // Borders
        if (coreState.border != null && !coreState.border.isEmpty())
            line.append(
                    coreState.applyColour(coreState.border, coreState.borderColour, coreState.borderBackgroundColour));

        return line.toString();
    }

    /**
     * Loads the given content into the internal buffer with formatting applied.
     * <p>
     * If warp mode is enabled and the content exceeds the warp width, the content
     * is wrapped
     * into multiple lines automatically. Otherwise, the content is formatted as a
     * single line.
     * <br>
     * If {@code content} is {@code null}, it is treated as an empty string.
     * </p>
     * 
     * @param content the text to add to the buffer; if {@code null}, treated as
     *                empty
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T formatIntoBuffer(String content) {
        if (content == null)
            content = "";

        String processedContent = warpText(content); // keep uncoloured for warpping

        // Normal processing (alignment or manual)
        String line = formatLine(processedContent);
        coreState.buffer
                .append(line)
                .append("\n")
                .toString();
        return (T) this;
    }

    // ------------------ HELPER METHODS ------------------

    /**
     * Wraps the given text into multiple lines if warp mode is enabled.
     * <p>
     * Splits the text into substrings of at most {@code warpWidth} characters,
     * formatting each as a separate line. If warp is disabled and the text exceeds
     * warpWidth, it is clipped to warpWidth.
     * <br>
     * If {@code text} is {@code null} or empty, returns a single formatted empty
     * line.
     * </p>
     * 
     * @param text the text to wrap; if {@code null}, treated as empty string
     * @return the wrapped and formatted text as a string
     */
    private String warpText(String text) {
        if (text == null || text.isEmpty())
            text = "";

        // Clip if warp is disabled
        if (!coreState.warpEnabled && coreState.visible(text) > coreState.width) {
            text = text.substring(0, coreState.width);
            return formatLine(text) + "\n";
        }

        // Warp enabled: split into multiple lines
        StringBuilder wrapped = new StringBuilder();
        int start = 0;
        int len = text.length();
        while (start < len) {
            int end = Math.min(start + coreState.warpWidth, len);
            String chunk = text.substring(start, end);
            wrapped.append(formatLine(chunk)).append("\n");
            start += coreState.warpWidth;
        }

        return wrapped.toString();
    }

    /**
     * Appends a formatted table to the internal buffer using the specified headers
     * and data.
     * <p>
     * The table automatically adjusts column widths to fit the formatter's width
     * (or warp width if enabled),
     * wraps cell content as needed, and applies current border, padding, and color
     * settings.
     * <br>
     * If {@code headers} or {@code data} is {@code null}, or if the data rows do
     * not match the header length,
     * an error message is appended to the buffer and the method returns.
     * </p>
     * 
     * @param headers the array of column headers; must not be {@code null}
     * @param data    the 2D array of table data; each row should have the same
     *                number of columns as {@code headers}
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T table(String[] headers, String[][] data) {
        if (headers == null || data == null || data[0].length != headers.length) {
            coreState.buffer.append("ERROR in TABLE!!! ");
            return (T) this;
        }

        int cols = headers.length;
        int totalWidth = coreState.warpEnabled ? coreState.warpWidth : coreState.width;
        int[] colWidths = new int[cols];

        // Start with minimum width = header length
        for (int i = 0; i < cols; i++) {
            colWidths[i] = Math.max(1, coreState.visible(headers[i]));
        }

        // Adjust column widths proportionally to fit totalWidth
        int paddingPerCol = 2; // left/right padding in each cell
        int totalPadding = paddingPerCol * cols + (cols + 1); // include separators
        int availableWidth = totalWidth - totalPadding;
        int sumColWidths = 0;
        for (int w : colWidths)
            sumColWidths += w;

        for (int i = 0; i < cols; i++) {
            colWidths[i] = Math.max(1, colWidths[i] * availableWidth / sumColWidths);
        }

        // Build horizontal border with corner pieces and separators
        StringBuilder borderLine = new StringBuilder();
        borderLine.append(coreState.tableCornerPiece);
        for (int w : colWidths) {
            borderLine.append(coreState.repeat(String.valueOf(coreState.horizontalSeparator), w + paddingPerCol));
            borderLine.append(coreState.tableCornerPiece);
        }
        String border = borderLine.toString();
        formatIntoBuffer(border);

        // Print header row
        StringBuilder headerLine = new StringBuilder();
        // Left indent and padding
        if (coreState.leftIndent > 0)
            headerLine.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.leftIndentChar), coreState.leftIndent),
                    coreState.leftIndentColour, coreState.leftIndentBackgroundColour));
        if (coreState.leftPaddingChar != 0)
            headerLine.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.leftPaddingChar), coreState.leftIndent),
                    coreState.leftPaddingColour, coreState.leftPaddingBackgroundColour));
        headerLine.append(coreState.verticalSeparator);
        for (int i = 0; i < cols; i++) {
            String h = headers[i];
            int pad = colWidths[i] - coreState.visible(h);
            int leftPad = pad / 2;
            int rightPad = pad - leftPad;
            // Left filler inside cell
            headerLine.append(
                    coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), 1), coreState.fillColour,
                            coreState.fillBackgroundColour));
            headerLine.append(coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), leftPad),
                    coreState.fillColour, coreState.fillBackgroundColour));
            headerLine.append(coreState.applyColour(h, coreState.contentColour, coreState.contentBackgroundColour));
            headerLine.append(coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), rightPad),
                    coreState.fillColour, coreState.fillBackgroundColour));
            headerLine.append(
                    coreState.applyColour(coreState.repeat(String.valueOf(coreState.filler), 1), coreState.fillColour,
                            coreState.fillBackgroundColour));
            headerLine.append(coreState.verticalSeparator);
        }
        // Right padding and indent
        if (coreState.rightPaddingChar != 0)
            headerLine.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.rightPaddingChar), coreState.rightIndent),
                    coreState.rightPaddingColour, coreState.rightPaddingBackgroundColour));
        if (coreState.rightIndent > 0)
            headerLine.append(coreState.applyColour(
                    coreState.repeat(String.valueOf(coreState.rightIndentChar), coreState.rightIndent),
                    coreState.rightIndentColour, coreState.rightIndentBackgroundColour));
        formatIntoBuffer(headerLine.toString());
        formatIntoBuffer(border);

        // Print rows with wrapping
        for (String[] row : data) {
            int maxLines = 1;
            String[][] wrappedCells = new String[cols][];
            for (int i = 0; i < cols; i++) {
                String cell = i < row.length ? row[i] : "";
                int colWidth = colWidths[i];
                wrappedCells[i] = warpText(cell, colWidth);
                maxLines = Math.max(maxLines, wrappedCells[i].length);
            }

            for (int line = 0; line < maxLines; line++) {
                StringBuilder rowLine = new StringBuilder();
                // Left indent and padding
                if (coreState.leftIndent > 0)
                    rowLine.append(
                            coreState.applyColour(coreState.repeat(coreState.leftIndentChar + "", coreState.leftIndent),
                                    coreState.leftIndentColour, coreState.leftIndentBackgroundColour));
                if (coreState.leftPaddingChar != 0)
                    rowLine.append(coreState.applyColour(
                            coreState.repeat(coreState.leftPaddingChar + "", coreState.leftIndent),
                            coreState.leftPaddingColour, coreState.leftPaddingBackgroundColour));
                rowLine.append(coreState.verticalSeparator);
                for (int i = 0; i < cols; i++) {
                    String cellPart = line < wrappedCells[i].length ? wrappedCells[i][line] : "";
                    int pad = colWidths[i] - coreState.visible(cellPart);
                    // Left filler inside cell
                    rowLine.append(coreState.applyColour(coreState.filler + "", coreState.fillColour,
                            coreState.fillBackgroundColour));
                    rowLine.append(coreState.applyColour(cellPart, coreState.contentColour,
                            coreState.contentBackgroundColour));
                    rowLine.append(
                            coreState.applyColour(coreState.repeat(coreState.filler + "", pad), coreState.fillColour,
                                    coreState.fillBackgroundColour));
                    rowLine.append(coreState.applyColour(coreState.filler + "", coreState.fillColour,
                            coreState.fillBackgroundColour));
                    rowLine.append(coreState.verticalSeparator);
                }
                // Right padding and indent
                if (coreState.rightPaddingChar != 0)
                    rowLine.append(coreState.applyColour(
                            coreState.repeat(String.valueOf(coreState.rightPaddingChar), coreState.rightIndent),
                            coreState.rightPaddingColour, coreState.rightPaddingBackgroundColour));
                if (coreState.rightIndent > 0)
                    rowLine.append(coreState.applyColour(
                            coreState.repeat(String.valueOf(coreState.rightIndentChar), coreState.rightIndent),
                            coreState.rightIndentColour, coreState.rightIndentBackgroundColour));
                formatIntoBuffer(rowLine.toString());
            }
        }

        formatIntoBuffer(border);
        return (T) this;
    }

    /**
     * Helper method to warp text within a specified column width.
     * <p>
     * Splits the text into substrings of at most {@code width} characters.
     * <br>
     * If {@code text} is {@code null}, it is treated as an empty string.
     * </p>
     * 
     * @param text  the text to warp; if {@code null}, treated as an empty string
     * @param width the maximum width of each line
     * @return an array of warped lines
     */
    private String[] warpText(String text, int width) {
        if (text == null)
            text = "";
        int len = text.length();
        if (len <= width)
            return new String[] { text };

        java.util.List<String> lines = new java.util.ArrayList<>();
        int start = 0;
        while (start < len) {
            int end = Math.min(start + width, len);
            lines.add(text.substring(start, end));
            start += width;
        }
        return lines.toArray(new String[0]);
    }

    /**
     * Appends a formatted list-style menu to the internal buffer.
     * <p>
     * Each menu option is displayed on a separate line. If {@code options} is
     * {@code null},
     * an error message is appended to the buffer.
     * </p>
     * 
     * @param options the array of menu option strings; must not be {@code null}
     * @param colours the array of colour options, must not be {@code null}
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T listMenu(String[] options, Colour colours[]) {
        if (options == null) {
            formatIntoBuffer("ERROR!!! Options are not initiliazed.\n");
            return (T) this;
        }
        if (colours == null || colours.length < options.length) {
            formatIntoBuffer("ERROR!!! Colours are not initialized or do not match options.\n");
            return (T) this;
        }
        for (int i = 0; i < options.length; i++) {
            setContentColour(colours[i]);
            formatIntoBuffer(options[i]);
        }
        return (T) this;
    }

    /**
     * Appends a formatted list-style menu to the internal buffer.
     * <p>
     * Each menu option is displayed on a separate line. If {@code options} is
     * {@code null},
     * an error message is appended to the buffer.
     * </p>
     * 
     * @param options the array of menu option strings; must not be {@code null}
     * @return the caller's instance for chainable calls
     */
    @SuppressWarnings("unchecked")
    public T listMenu(String[] options) {
        if (options == null) {
            formatIntoBuffer("ERROR!!! Options are not initiliazed.\n");
            return (T) this;
        }

        for (int i = 0; i < options.length; i++) {
            formatIntoBuffer(options[i]);
        }
        return (T) this;
    }
}
