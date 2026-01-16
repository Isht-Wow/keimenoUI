package wow.ishit.keimenoUI;

public class TUIHandler {
    public static StringBuilder buffer = new StringBuilder();
    public static void newUI() {
        buffer.setLength(0);
    }

    public static void applyUI() {
        System.out.println(buffer);
        newUI();
    }
}
