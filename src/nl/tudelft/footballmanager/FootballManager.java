package nl.tudelft.footballmanager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.tudelft.footballmanager.ui.controller.SplashScreen;
import nl.tudelft.footballmanager.ui.controller.TitleScreenController;

public class FootballManager extends Application {
	private static Stage stage;

	private static boolean bShowSplash = false;

	@Override
	public void start(Stage stage) {
		System.getProperties().list(System.out);
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager 2142");

		FootballManager.stage.setHeight(screenDimensions.getHeight() - 100);
		FootballManager.stage.setWidth(screenDimensions.getWidth() - 50);

		FootballManager.stage.centerOnScreen();
		FootballManager.stage.show();

		// Gewoon lekker niet op een Mac
		if(bShowSplash && !System.getProperty("os.name").equals("Mac OS X")) {		
			// TODO don't forget to re-enable this for the demo, if the intro video is done.
			SplashScreen ss = null;
			try {
				ss = new SplashScreen(stage, new File("vid/intro.mp4").toURI().toURL().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ss.show();
		}
		else {
			TitleScreenController.show();
		}	

	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @param chooser
	 * @return
	 */
	public static File getOpenFile(FileChooser chooser) {
		return chooser.showOpenDialog(stage);
	}

	/**
	 * @param chooser
	 * @return
	 */
	public static File getSaveFile(FileChooser chooser) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String now = sdf.format(new Date());
		String fileName = now + ".xml";
		System.out.println("Filename: " + fileName);
		chooser.setInitialFileName(fileName);
		return chooser.showSaveDialog(stage);
	}

	/**
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}
}
