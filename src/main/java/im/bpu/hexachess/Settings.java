package im.bpu.hexachess;

import java.util.prefs.Preferences;

public class Settings {
	private static final Preferences prefs = Preferences.userNodeForPackage(Settings.class);
	public static int maxDepth = prefs.getInt("maxDepth", 3);
	public static void save() {
		prefs.putInt("maxDepth", maxDepth);
	}
}