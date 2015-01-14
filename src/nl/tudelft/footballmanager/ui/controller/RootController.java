package nl.tudelft.footballmanager.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class RootController implements Initializable, Observer {
	
	public final static String rootViewFileName = "ui/view/RootView.fxml";

	@FXML private Button saveGameButton;
	@FXML private Button loadGameButton;
	@FXML private Label gamesPlayed;
	@FXML private Button nextRoundButton;
	@FXML private MenuItem saveAndQuitDesktopMenuItem;
	@FXML private MenuItem saveAndQuitMenuItem;
	@FXML private MenuItem quitMenuMenuItem;
	@FXML private MenuItem quitDesktopMenuItem;
	
	private static GameState gameState = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saveGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			}
		});

		loadGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			loadGame(gameState);
		});
		
		nextRoundButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			gameState.nextRound();
			
			System.out.println("MATCHVIEW");
			//TODO matchview
//			MatchViewController.show(rootController.rootController);
		});
		
		// Save and Quit to Menu
		saveAndQuitMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			} else {
				TitleScreenController.show();
				System.out.println("Saved and Returned to Menu!");
			}
		});
		
		// Save and Quit to Desktop
		saveAndQuitDesktopMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			} else {
				Platform.exit();
				System.out.println("Saved and Quit!");
			}
		});
		
		
		// Quit to Menu
		quitMenuMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			TitleScreenController.show();
			System.out.println("Returned to Menu!");
		});
		
		
		// Quit to Desktop
		quitDesktopMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			Platform.exit();
			System.out.println("Quit to Desktop!");
		});
	}
	
	public static void show(GameState gs) {
		gameState = gs;
		
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(rootViewFileName));
		try {
			BorderPane rootLayout = (BorderPane) l.load();
			FootballManager.getStage().setScene(new Scene(rootLayout));
//			FootballManager.getStage().show();
			
			TeamOverviewController.show(rootLayout, gameState);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean saveGame(GameState state) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save game");
		configureFileChooser(chooser);
		
		// file type filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.nl.tudelft.footballmanager.model.xml)", "*.nl.tudelft.footballmanager.model.xml");
		chooser.getExtensionFilters().add(extFilter);

		File selectedFile = FootballManager.getSaveFile(chooser);
		if (selectedFile != null) {
			System.out.println("Save file: " + selectedFile.getAbsolutePath());
			state.saveGameState(selectedFile);
			return true;
		} else {
			System.err.println("No file selected!");
			return false;
		}

	}

	public GameState loadGame(GameState gamestate) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Load game");
		configureFileChooser(chooser);

		File selectedFile = FootballManager.getOpenFile(chooser);
		if (selectedFile != null) {
			System.out.println("Load file: " + selectedFile.getAbsolutePath());

			gamestate.loadGameState(selectedFile);
			System.out.println("GameState: " + gamestate.toString());
		} else {
			System.err.println("No file selected!");
		}

		return gamestate;
	}

	public void configureFileChooser(FileChooser fc) {
		// Standard dir is working dir of application
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML", "*.xml"));
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(String.format("%s:\n\t%s\n\t%s", this.getClass(), o, arg));
	}
}
