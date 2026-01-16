package wow.ishit.v1_stable_keimenoUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputManager
 *
 * Beginner-friendly raw terminal input library for Unix-like systems
 * (macOS, Linux).
 *
 * This class puts the terminal into RAW MODE, meaning:
 * - Keys are read instantly (no Enter required)
 * - Arrow keys, Ctrl keys, and function keys are captured
 * - Input is read as raw bytes
 * - All normal printing must occur after the InputManager is closed.
 * - Printing while raw mode is active may cause undesirable terminal behavior
 *
 * ⚠ IMPORTANT:
 * - You MUST close this class to restore the terminal.
 * - NOT Windows compatible.
 *
 * BASIC USAGE:
 *
 * <pre>
 * InputManager input = InputManager.open();
 * Key key;
 * while (true) {
 *     key = input.readKey();
 *     if (key == Key.ESC)
 *         break; // ESC key
 * }
 * input.close();
 * System.out.println(key);
 * </pre>
 */

public final class InputManager implements AutoCloseable {

    private static String originalConfig;
    private static boolean rawEnabled = false;

    private final InputStream in;

    /**
     * Creates a new InputManager bound directly to the controlling terminal.
     *
     * <p>
     * This constructor opens {@code /dev/tty} for raw byte-level input.
     * It does not enable raw mode by itself; callers should normally use
     * {@link #open()} instead, which handles initialization safely.
     * </p>
     *
     */
    public InputManager() throws IOException {
        InputStream stream;
        try {
            stream = new FileInputStream("/dev/tty");
        } catch (IOException e) {
            System.err.println("Warning: Failed to open /dev/tty: " + e.getMessage());
            stream = InputStream.nullInputStream();
        }
        this.in = stream;
    }

    /**
     * Opens an {@code InputManager} and enables raw terminal mode.
     *
     * <p>
     * This is the recommended entry point for most users. It constructs
     * the manager, switches the terminal into raw mode, and installs a
     * shutdown hook to ensure terminal restoration.
     * </p>
     *
     * <p>
     * If initialization fails, the error is reported to {@code stderr}
     * and {@code null} is returned.
     * </p>
     *
     * @return an initialized {@code InputManager}, or {@code null} on failure
     */
    public static InputManager open() {
        InputManager manager;
        try {
            manager = new InputManager();
            manager.start();
        } catch (IOException e) {
            System.err.println("Failed to open InputManager: " + e.getMessage());
            e.printStackTrace(System.err);
            return null;
        }
        return manager;
    }

    /* ===================== LIFECYCLE ===================== */

    /**
     * Enables raw terminal mode on the controlling terminal.
     *
     * <p>
     * Raw mode disables canonical input processing and echoing, allowing
     * key presses to be read immediately as bytes. Calling this method
     * multiple times has no effect after raw mode is enabled.
     * </p>
     *
     * <p>
     * The original terminal configuration is saved and automatically
     * restored on JVM shutdown.
     * </p>
     */
    public void start() {
        if (rawEnabled)
            return;
        try {
            originalConfig = exec("stty -f /dev/tty -g").trim();
            exec("stty -f /dev/tty -icanon -echo min 1 time 0");
            rawEnabled = true;
        } catch (IOException e) {
            System.err.println("Failed to enable raw mode: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                restore();
            } catch (Exception ignored) {
            }
        }));
    }

    /**
     * Restores the terminal to its original configuration.
     *
     * <p>
     * This method should always be called when input handling is finished.
     * It is safe to call multiple times. When used in a
     * try-with-resources block, this method is invoked automatically.
     * </p>
     */
    @Override
    public void close() {
        try {
            restore();
        } catch (Exception e) {
            System.err.println("Failed to restore terminal state on close: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /**
     * Restores the saved terminal configuration.
     *
     * <p>
     * If raw mode is not currently enabled, this method does nothing.
     * Any I/O errors during restoration are reported to {@code stderr}.
     * </p>
     */
    private void restore() {
        if (!rawEnabled)
            return;
        try {
            exec("stty -f /dev/tty " + originalConfig);
            rawEnabled = false;
        } catch (IOException e) {
            System.err.println("Failed to restore terminal state: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /* ===================== INPUT ===================== */

    /**
     * Reads a single key press in blocking mode.
     *
     * <p>
     * This method interprets raw terminal input and translates it into
     * a {@link Key} enum value. It handles printable characters, control
     * keys (such as Ctrl+C), escape sequences, arrow keys, and function keys.
     * </p>
     *
     * <p>
     * Any {@link IOException} encountered during reading is caught
     * internally. In such cases, {@link Key#OTHER} is returned and a warning
     * is printed to {@code stderr}.
     * </p>
     *
     * @return the interpreted {@link Key}, or {@link Key#OTHER} on error
     */
    public Key readKey() {
        try {
            int b = in.read();
            if (b == -1)
                return Key.OTHER;
            if (b == 27) { // ESC or escape sequence
                in.mark(4);
                int b2 = in.available() > 0 ? in.read() : -1;
                if (b2 == -1) {
                    return Key.ESC;
                }
                if (b2 == 91) { // CSI: ESC [
                    int b3 = in.available() > 0 ? in.read() : -1;
                    if (b3 == -1) {
                        return Key.ESC;
                    }
                    // Arrow keys
                    if (b3 == 65)
                        return Key.ARROW_UP;
                    if (b3 == 66)
                        return Key.ARROW_DOWN;
                    if (b3 == 67)
                        return Key.ARROW_RIGHT;
                    if (b3 == 68)
                        return Key.ARROW_LEFT;
                    // Function keys (ESC [ [A..Z] or ESC [ <num> ~)
                    if (b3 >= 49 && b3 <= 54) { // F1-F6, etc.
                        int b4 = in.available() > 0 ? in.read() : -1;
                        if (b4 == -1) {
                            return Key.ESC;
                        }
                        // ESC [ 1 ~ = Home, ESC [ 4 ~ = End, etc.
                        // Function keys: ESC [ [1-9][0-9]? ~
                        if (b4 == 126) {
                            // e.g. ESC [ 1 ~, ESC [ 4 ~
                            switch (b3) {
                                case 49:
                                    return Key.F1;
                                case 50:
                                    return Key.F2;
                                case 51:
                                    return Key.F3;
                                case 52:
                                    return Key.F4;
                                case 53:
                                    return Key.F5;
                                case 54:
                                    return Key.F6;
                            }
                        } else if (b4 >= 48 && b4 <= 57) {
                            // Possibly two-digit function key: ESC [ 1 1 ~, etc.
                            int b5 = in.available() > 0 ? in.read() : -1;
                            if (b5 == 126) {
                                int fn = (b3 - 48) * 10 + (b4 - 48);
                                switch (fn) {
                                    case 11:
                                        return Key.F1;
                                    case 12:
                                        return Key.F2;
                                    case 13:
                                        return Key.F3;
                                    case 14:
                                        return Key.F4;
                                    case 15:
                                        return Key.F5;
                                    case 17:
                                        return Key.F6;
                                    case 18:
                                        return Key.F7;
                                    case 19:
                                        return Key.F8;
                                    case 20:
                                        return Key.F9;
                                    case 21:
                                        return Key.F10;
                                    case 23:
                                        return Key.F11;
                                    case 24:
                                        return Key.F12;
                                }
                            }
                        }
                    }
                    // F1–F4 (xterm: ESC [ A/B/C/D)
                    if (b3 == 80)
                        return Key.F1; // ESC [P
                    if (b3 == 81)
                        return Key.F2; // ESC [Q
                    if (b3 == 82)
                        return Key.F3; // ESC [R
                    if (b3 == 83)
                        return Key.F4; // ESC [S
                    // Otherwise, unknown ESC sequence
                    return Key.ESC;
                } else if (b2 == 79) { // ESC O
                    int b3 = in.available() > 0 ? in.read() : -1;
                    if (b3 == -1)
                        return Key.ESC;
                    // ESC O P/Q/R/S for F1–F4 (vt100)
                    if (b3 == 80)
                        return Key.F1;
                    if (b3 == 81)
                        return Key.F2;
                    if (b3 == 82)
                        return Key.F3;
                    if (b3 == 83)
                        return Key.F4;
                    return Key.ESC;
                } else {
                    // Just ESC
                    in.reset();
                    return Key.ESC;
                }
            }
            if (b == 3)
                return Key.ETX; // Ctrl+C
            if (b == 13 || b == 10)
                return Key.ENTER; // CR or LF
            if (b == 9)
                return Key.TAB;
            if (b == 127 || b == 8)
                return Key.BACKSPACE;
            // Printable ASCII
            if (b >= 32 && b <= 126) {
                Key key = Key.fromChar((char) b);
                if (key != null)
                    return key;
                return Key.OTHER;
            }
            // Extended Unicode (UTF-8) - just return OTHER for bytes >= 128
            if (b >= 128)
                return Key.OTHER;
            return Key.OTHER;
        } catch (IOException e) {
            System.err.println("Warning: IOException in readKey(): " + e.getMessage());
            return Key.OTHER;
        }
    }

    /**
     * Reads a single raw byte from the terminal in blocking mode.
     *
     * <p>
     * This method performs no interpretation or key mapping and exposes
     * the underlying terminal input directly.
     * </p>
     *
     * @return the byte value (0–255), or {@code -1} on end of stream or error
     */
    public int read() {
        try {
            return in.read();
        } catch (IOException e) {
            System.err.println("Warning: IOException in read(): " + e.getMessage());
            return -1;
        }
    }

    /**
     * Attempts to read a single raw byte without blocking.
     *
     * <p>
     * If no input is currently available, this method returns {@code -1}
     * immediately. Otherwise, it reads and returns the next byte.
     * </p>
     *
     * @return the byte value (0–255), or {@code -1} if no input is available or on
     *         error
     */
    public int poll() {
        try {
            if (in.available() > 0) {
                return in.read();
            }
            return -1;
        } catch (IOException e) {
            System.err.println("Warning: IOException in poll(): " + e.getMessage());
            return -1;
        }
    }

    /**
     * Reads a pending escape sequence from the terminal, if available.
     *
     * <p>
     * This method is useful for processing multi-byte sequences such as
     * arrow keys and function keys when manual parsing is required.
     * </p>
     *
     * @return a byte array containing the escape sequence, or {@code null}
     *         if no input is available or on error
     */
    public byte[] readEscapeSequence() {
        try {
            if (in.available() == 0)
                return null;

            byte[] buffer = new byte[Math.min(in.available(), 16)];
            int len = in.read(buffer);
            if (len <= 0)
                return null;

            byte[] result = new byte[len];
            System.arraycopy(buffer, 0, result, 0, len);
            return result;
        } catch (IOException e) {
            System.err.println("Warning: IOException in readEscapeSequence(): " + e.getMessage());
            return null;
        }
    }

    /* ===================== UTIL ===================== */

    /**
     * Executes a shell command and returns its standard output.
     *
     * <p>
     * This utility method is primarily used to configure and restore
     * terminal settings via {@code stty}. The command is executed using
     * {@code sh -c}, and both standard output and error output are captured.
     * </p>
     * 
     * @param cmd the shell command to execute
     * @return the command output as a string
     */
    private static String exec(String cmd) throws IOException {
        try {
            Process p = new ProcessBuilder("sh", "-c", cmd)
                    .redirectErrorStream(true)
                    .start();

            p.waitFor();
            return new String(p.getInputStream().readAllBytes());
        } catch (IOException e) {
            System.err.println("Warning: Failed to execute command: " + cmd);
            System.err.println(e.getMessage());
            return "";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Process execution was interrupted: " + e.getMessage());
            return "";
        }
    }

}