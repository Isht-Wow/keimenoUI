public class basicVideoPlaying {
    public void Demo() {
        Renderer<?> renderer = new Renderer();
        renderer.coreState.clearScreen();
        renderer
          .setWidth(100)
          .setContentColour(Colour.RED)
          .header("Demo Video Playing")
          .getCoreState().flush();

        renderer.coreState.delay(1000);

        renderer.enableAudio(true)
                .setImageAspectRatio(1.0)
                .playFrames("Animations/Aura/", renderer.getWidth());

        renderer.coreState
            .delay(1400)
            .clearScreen();

        renderer.header("THANKS FOR WATCHING").flush();

    }
}
