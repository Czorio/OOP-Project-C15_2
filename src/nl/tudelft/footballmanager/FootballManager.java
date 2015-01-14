package nl.tudelft.footballmanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.tudelft.footballmanager.ui.controller.TitleScreenController;

public class FootballManager extends Application {
	private static Stage stage;

	@Override
	public void start(Stage stage) {
		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager 2142");
		FootballManager.stage.setMinWidth(800);
		FootballManager.stage.setMinHeight(600);
		FootballManager.stage.centerOnScreen();
		FootballManager.stage.show();

		TitleScreenController.show();
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
