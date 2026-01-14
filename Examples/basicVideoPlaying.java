public class basicVideoPlaying {
    public void Demo() {
        CoreState core = new CoreState();
        AsciiArt asciiArt = new AsciiArt(core);
        Formatter formatter = new Formatter(core);
      
        core.clearScreen();
        formatter
          .setWidth(100)
          .setContentColour(Colour.RED)
          .header("Demo Video Playing")
          .coreState.flush();
      
        core.delay(1000);

        asciiArt.enableAudio(true)
                .setImageAspectRatio(1.0)
                .playFrames("Animations/Aura/", formatter.getWidth());
        
        core.delay(1400);
        core.clearScreen();
        formatter.header("THANKS FOR WATCHING").coreState.flush();

    }
}
