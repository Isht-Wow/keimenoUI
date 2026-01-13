package wow.ishit.v2_beta_keimenoUI;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AudioManager handles loading, playing, stopping, and volume control of audio clips.
 * <p>
 * This class manages audio playback using Java Sound API's Clip objects. It supports
 * loading audio files from disk, playing them with optional looping, adjusting volume
 * based on a master volume setting, and cleaning up resources when no longer needed.
 * <p>
 * AudioManager works closely with a {@link CoreState} instance to retrieve configuration
 * parameters such as audio volume and looping preferences. It extends {@link Settings}
 * to integrate with the application's settings framework.
 * <p>
 * Usage example:
 * <pre>{@code
 * CoreState coreState = new CoreState();
 * coreState.audioVolume = 0.8f;
 * coreState.audioLoopEnabled = true;
 *
 * AudioManager audioManager = new AudioManager(coreState);
 * audioManager.startPlaying("backgroundMusic", "path/to/music.wav", 0);
 * }</pre>
 * <p>
 * This class is designed to be thread-safe for typical use cases but does not
 * synchronize externally. Users should ensure thread safety if accessed concurrently.
 */
public class AudioManager extends Settings<AudioManager> {

    /**
     * The CoreState instance providing configuration parameters such as audio volume
     * and looping settings.
     */
    CoreState coreState;

    /**
     * A map storing loaded audio clips, keyed by their unique string identifiers.
     * Clips are loaded once and reused for playback.
     */
    private final Map<String, Clip> sounds = new HashMap<String, Clip>();

    /**
     * The master volume level applied to all loaded audio clips.
     * Value ranges from 0.0 (mute) to 1.0 (maximum volume).
     */
    private float masterVolume = 1.0f; // 0.0 – 1.0

    /**
     * Constructs an AudioManager instance and applies default settings from a new CoreState.
     * Use {@link #applySettings(CoreState)} to update the configuration.
     */
    public AudioManager() {
        this(new CoreState());
    }

    /**
     * Constructs an AudioManager instance with the specified CoreState settings.
     *
     * @param coreState The CoreState instance containing audio configuration.
     */
    public AudioManager(CoreState coreState) {
        this.coreState = coreState;
        super(coreState);
    }

    /**
     * Applies the provided CoreState configuration to the AudioManager.
     * This updates audio-related settings such as volume and looping behavior.
     *
     * @param coreState The CoreState instance containing audio configuration.
     */
    public void applySettings(CoreState coreState) {
        this.coreState = coreState;
    }

    /**
     * Loads an audio clip from the specified file path and associates it with the given identifier.
     * If a clip with the same identifier is already loaded, this method does nothing.
     * The loaded clip's volume is set according to the current master volume.
     * <p>
     * Supported audio formats depend on the Java Sound API and typically include WAV.
     *
     * @param id   The unique identifier for the sound clip.
     * @param path The file system path to the audio file.
     * @throws RuntimeException if the audio file cannot be loaded or opened.
     */
    public void load(String id, String path) {
        if (sounds.containsKey(id))
            return;

        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            sounds.put(id, clip);
            applyVolume(clip);
        } catch (Exception e) {
            coreState.buffer.append("Failed to load sound: " + path);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

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
        if (clip == null)
            return;

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
        if (clip == null)
            return;

        clip.stop();
        clip.setFramePosition(0);
    }

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

    /**
     * Applies the current master volume to the specified Clip.
     * If the clip does not support volume control, this method does nothing.
     *
     * @param clip The audio Clip to adjust volume for.
     */
    private void applyVolume(Clip clip) {
        if (!clip.isControlSupported(FloatControl.Type.MASTER_GAIN))
            return;

        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = gain.getMinimum();
        float max = gain.getMaximum();
        gain.setValue(min + (max - min) * masterVolume);
    }

    /**
     * Clamps a float value to the range 0.0 to 1.0 inclusive.
     *
     * @param v The value to clamp.
     * @return The clamped value within [0.0, 1.0].
     */
    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

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
     * If playTimeMillis is greater than zero, the method delays for that duration after starting playback.
     *
     * @param id             The identifier to associate with the sound.
     * @param location       The file path of the sound to load and play.
     * @param playTimeMillis The time in milliseconds to delay after starting playback; zero or negative means no delay.
     */
    public void startPlaying(String id, String location, int playTimeMillis) {
        load(id, location);
        setMasterVolume();
        play(id);
        if (playTimeMillis > 0)
        coreState.delay(playTimeMillis);
    }
}