package wow.ishit.v2_beta_keimenoUI;

public enum BackgroundColour {
    BLACK("\u001B[40m"),
    RED("\u001B[41m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    BLUE("\u001B[44m"),
    MAGENTA("\u001B[45m"),
    CYAN("\u001B[46m"),
    WHITE("\u001B[47m"),
    BRIGHT_BLACK("\u001B[100m"),
    BRIGHT_RED("\u001B[101m"),
    BRIGHT_GREEN("\u001B[102m"),
    BRIGHT_YELLOW("\u001B[103m"),
    BRIGHT_BLUE("\u001B[104m"),
    BRIGHT_MAGENTA("\u001B[105m"),
    BRIGHT_CYAN("\u001B[106m"),
    BRIGHT_WHITE("\u001B[107m"),
    RESET("\u001B[0m"),
    NONE("");
    private final String code;

    BackgroundColour(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
