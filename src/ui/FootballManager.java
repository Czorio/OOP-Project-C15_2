package ui;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import dataModel.GameState;

public class FootballManager extends Application {
	
	private static Stage stage;
	private BorderPane rootLayout;
	private static Controller c;

	@Override
	public void start(Stage stage) {
		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager");
		
		try {
			initRootLayout();
		} catch (IOException e) {
			System.err.println("Error loading UI root!");
			e.printStackTrace();
		}
		
		try {
			showTeamOverview();
		} catch (IOException e) {
			System.err.println("Error loading UI Team overview");
			e.printStackTrace();
		}
		
		c.setGamestate(new GameState(null, 0, null, null));
		c.getGameState().addObserver(c);
		
		if (GameState.isUseless(c.getGameState())) {
			System.out.println("GameState is empty, asking to load one.");
			c.setGamestate(c.loadGame(c.getGameState()));
		}
		
		System.out.println("Start finished!");
		
	}
	
	public void initRootLayout() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/rootView.fxml"));
		rootLayout = (BorderPane) l.load();
		stage.setScene(new Scene(rootLayout));
		stage.show();
		
		c = l.getController();
	}
	
	public void showTeamOverview() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/TeamOverview.fxml"));
		AnchorPane teamOverview = (AnchorPane) l.load();
		rootLayout.setCenter(teamOverview);
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
		String now = new Date().toGMTString();
		System.out.println(now);
//		chooser.setInitialFileName(value);
		return chooser.showSaveDialog(stage);
	}

}
