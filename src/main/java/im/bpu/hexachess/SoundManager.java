package im.bpu.hexachess;

import java.net.URL;
import javafx.scene.media.AudioClip;

public class SoundManager {
	private static final AudioClip CLIP;
	static {
		AudioClip clip = null;
		try {
			URL resource = SoundManager.class.getResource(
				"/im/bpu/sounds/mixkit-quick-win-video-game-notification-269.wav");
			if (resource != null) {
				clip = new AudioClip(resource.toString());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		CLIP = clip;
	}
	public static void playClick() {
		if (CLIP != null) {
			CLIP.play();
		} else {
			System.err.println("Audio File Not Found Error");
		}
	}
}