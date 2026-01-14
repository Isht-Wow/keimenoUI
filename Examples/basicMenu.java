public class basicMenu {
  
    public void basicSelectionMenu() {
        CoreState core = new CoreState();
        Formatter f = new Formatter(core);
        core.clearScreen();

        // Interactive menu controlled by keys
        String[] options = { "1. Explore", "2. Learn", "3. Exit" };
        core.clearScreen();
        f.listMenu(options);
        core.flush();

        Key key = null;
        int selectedOption = 0;

        while (key != Key.ENTER) { // simple demo
            key = singleKeyPress();
            selectedOption = logic(key, selectedOption, options.length);
            Colour[] colours = getColours(selectedOption, options);
            f.listMenu(options, colours);
            core.rewritingFlush();
        }

        switch(selectedOption){
            case 0:
                exploreMenu();
                break;
            case 1:
                learnMenu();
            case 2:
                System.exit(0);
            default:
                System.exit(0);
        }
    }

    private int logic(Key key, int selectedOption, int totalOptions) {
        if (key == Key.ARROW_UP) {
            selectedOption = (selectedOption - 1 + totalOptions) % totalOptions;
        } else if (key == Key.ARROW_DOWN) {
            selectedOption = (selectedOption + 1) % totalOptions;
        }
        return selectedOption;
    }

    private static Colour[] getColours(int selectedOption, String[] options) {
        Colour[] colours = new Colour[options.length];
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                colours[i] = Colour.RED;
            } else {
                colours[i] = Colour.WHITE;
            }
        }
        return colours;
    }

    private Key singleKeyPress() {
        InputManager input = InputManager.open();
        Key key = input.readKey();
        input.close();
        return key;
    }
    private void exploreMenu() {
        // TODO:
    }
    private void learnMenu(){
        // TODO:
    }

}
