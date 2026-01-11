package wow.ishit.v2_beta_keimenoUI;
import javax.sound.sampled.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages audio playback including loading, playing, stopping sounds,
 * adjusting volume, and cleaning up audio resources.
 * This class supports looping and volume control based on a CoreState configuration.
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>
 * {@code
 * CoreState coreState = new CoreState();
 * coreState.audioVolume = 0.8f;
 * coreState.audioLoopEnabled = true;
 *
 * AudioManager audioManager = new AudioManager();
 * audioManager.applySettings(coreState);
 * audioManager.startPlaying("backgroundMusic", "path/to/sound.wav");
 * }
 * </pre>
 */
public class AudioManager {
    
    /**
     * Constructs an AudioManager instance and applies default settings.
     * The default settings are obtained from a new CoreState instance.
     * Use {@link #applySettings(CoreState)} to update the configuration.
     */
    protected AudioManager() {
        applySettings(new CoreState());
    }

    private CoreState coreState;

    /**
     * Applies the provided CoreState configuration to the AudioManager.
     * This updates audio-related settings such as volume and looping behavior.
     *
     * @param coreState The CoreState instance containing audio configuration.
     */
    public void applySettings(CoreState coreState) {
        this.coreState = coreState;
    }


    private final Map<String, Clip> sounds = new HashMap<>();
    private float masterVolume = 1.0f; // 0.0 – 1.0
    // ===================== LOAD =====================

    /**
     * Loads an audio clip from the specified file path and associates it with the given identifier.
     * If a clip with the same identifier is already loaded, this method does nothing.
     * The loaded clip's volume is set according to the current master volume.
     *
     * @param id   The unique identifier for the sound clip.
     * @param path The file system path to the audio file (e.g., WAV format).
     * @throws RuntimeException if the audio file cannot be loaded or opened.
     */
    public void load(String id, String path) {
        if (sounds.containsKey(id)) return;

        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            sounds.put(id, clip);
            applyVolume(clip);
        } catch (Exception e) {
            coreState.buffer.append("Failed to load sound: " + path);
        }
    }

    // ===================== PLAY =====================

    /**
     * Starts playback of the sound associated with the given identifier.
     * If looping is enabled in the current CoreState configuration, the sound will loop continuously.
     * Otherwise, the sound plays once from the beginning.
     * If the sound is not found, this method has no effect.
     *
     * @param id The identifier of the sound to play.
     */
    public void play(String id) {
        Clip clip = sounds.get(id);
        if (clip == null) return;

        clip.stop();
        clip.setFramePosition(0);

        if (coreState.audioLoopEnabled) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    /**
     * Stops playback of the specified sound and resets its position to the beginning.
     * If the sound is not currently playing or not found, this method has no effect.
     *
     * @param id The identifier of the sound to stop.
     */
    public void stop(String id) {
        Clip clip = sounds.get(id);
        if (clip == null) return;

        clip.stop();
        clip.setFramePosition(0);
    }

    // ===================== VOLUME =====================

    /**
     * Updates the master volume for all loaded sounds based on the current CoreState audio volume.
     * The volume value is clamped between 0.0 (mute) and 1.0 (maximum).
     * This method should be called after changing the CoreState's audioVolume to apply the new volume.
     */
    public void setMasterVolume() {
        masterVolume = clamp(coreState.audioVolume);
        for (Clip clip : sounds.values()) {
            applyVolume(clip);
        }
    }

    private void applyVolume(Clip clip) {
        if (!clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) return;

        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = gain.getMinimum();
        float max = gain.getMaximum();
        gain.setValue(min + (max - min) * masterVolume);
    }

    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

    // ===================== CLEANUP =====================

    /**
     * Stops all currently playing sounds, closes their resources,
     * and clears the loaded sounds cache.
     * Should be called when the AudioManager is no longer needed to free resources.
     */
    public void shutdown() {
        for (Clip clip : sounds.values()) {
            clip.stop();
            clip.close();
        }
        sounds.clear();
    }

    /**
     * Loads a sound from the specified location if not already loaded,
     * sets the master volume, and starts playing the sound.
     * This method combines loading, volume adjustment, and playback into a single call.
     *
     * @param id       The identifier to associate with the sound.
     * @param location The file path of the sound to load and play.
     */
    public void startPlaying(String id, String location){
        load(id, location);
        setMasterVolume();
        play(id);
    }
}