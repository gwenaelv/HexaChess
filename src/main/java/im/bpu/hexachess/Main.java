package im.bpu.hexachess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	private static final double ASPECT_RATIO_THRESHOLD = 1.5;
	private static final double DESKTOP_WIDTH = 1200;
	private static final double DESKTOP_HEIGHT = 800;
	private static final double MOBILE_WIDTH = 540;
	private static final double MOBILE_HEIGHT = 1200;
	private static final String WINDOW_TITLE = "HexaChess";
	@Override
	public void start(Stage stage) throws Exception {
		Parent root;
		if (SettingsManager.userHandle != null) {
			FXMLLoader mainWindowLoader =
				new FXMLLoader(getClass().getResource("ui/mainWindow.fxml"));
			mainWindowLoader.setController(new MainWindow());
			root = mainWindowLoader.load();
		} else {
			FXMLLoader startWindowLoader =
				new FXMLLoader(getClass().getResource("ui/startWindow.fxml"));
			startWindowLoader.setController(new StartWindow());
			root = startWindowLoader.load();
		}
		double width;
		double height;
		if (getAspectRatio() > ASPECT_RATIO_THRESHOLD) {
			width = DESKTOP_WIDTH;
			height = DESKTOP_HEIGHT;
		} else {
			width = MOBILE_WIDTH;
			height = MOBILE_HEIGHT;
		}
		Scene scene = new Scene(root, width, height);
		scene.getStylesheets().add(getClass().getResource("ui/style.css").toExternalForm());
		stage.setTitle(WINDOW_TITLE);
		stage.setScene(scene);
		stage.show();
	}
	public static double getAspectRatio() {
		double width = Screen.getPrimary().getBounds().getWidth();
		double height = Screen.getPrimary().getBounds().getHeight();
		double aspectRatio = width / height;
		return aspectRatio;
	}
	public static void main(String[] args) {
		launch(args);
	}
}