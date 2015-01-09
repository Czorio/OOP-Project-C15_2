package nl.tudelft.footballmanager.ui.controller;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import nl.tudelft.footballmanager.Context;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class RootController implements Initializable, Observer {

	@FXML private Button saveGameButton;
	@FXML private Button loadGameButton;
	@FXML private Label gamesPlayed;
	@FXML private Button nextRoundButton;
	
	@FXML private MenuItem quitMenuButton;
	@FXML private MenuItem saveAndQuitMenuButton;
	
	Context instance;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = Context.getInstance();

		saveGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(instance.getGameState());
			if (!result) {
				System.err.println("Game not saved!");
			}
		});

		loadGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			instance.setGameState(loadGame(instance.getGameState()));
		});
		
		nextRoundButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			instance.getGameState().nextRound();
			
			//showMatchView();
		});
		
		// Save and Quit application
		saveAndQuitMenuButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(instance.getGameState());
			if (!result) {
				System.err.println("Game not saved!");
			} else {
				Platform.exit();
			}
		});
		
		
		// Quit application
		quitMenuButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			Platform.exit();
			System.out.println("Game quit!");
		});
		
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
		
		// file type filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.nl.tudelft.footballmanager.model.xml)", "*.nl.tudelft.footballmanager.model.xml");
		chooser.getExtensionFilters().add(extFilter);

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
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML Game files", ".nl.tudelft.footballmanager.model.xml"));
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("An instance of " + o.getClass().toString() + " has changed (Root)!");
		
		if (o == instance.getGameState()) {
			this.gamesPlayed.setText(String.valueOf(instance.getGameState().getGameRound()));
		}
		
		System.out.println("o CHANGED " + o);
		System.out.println("arg CHANGED " + arg);
	}

	/**
	 * @return the saveGameButton
	 */
	public Button getSaveGameButton() {
		return saveGameButton;
	}

	/**
	 * @param saveGameButton the saveGameButton to set
	 */
	public void setSaveGameButton(Button saveGameButton) {
		this.saveGameButton = saveGameButton;
	}

	/**
	 * @return the loadGameButton
	 */
	public Button getLoadGameButton() {
		return loadGameButton;
	}

	/**
	 * @param loadGameButton the loadGameButton to set
	 */
	public void setLoadGameButton(Button loadGameButton) {
		this.loadGameButton = loadGameButton;
	}

	/**
	 * @return the gamesPlayed
	 */
	public Label getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * @param gamesPlayed the gamesPlayed to set
	 */
	public void setGamesPlayed(Label gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	/**
	 * @return the nextRoundButton
	 */
	public Button getNextRoundButton() {
		return nextRoundButton;
	}

	/**
	 * @param nextRoundButton the nextRoundButton to set
	 */
	public void setNextRoundButton(Button nextRoundButton) {
		this.nextRoundButton = nextRoundButton;
	}

	/**
	 * @return the quitMenuButton
	 */
	public MenuItem getQuitMenuButton() {
		return quitMenuButton;
	}

	/**
	 * @param quitMenuButton the quitMenuButton to set
	 */
	public void setQuitMenuButton(MenuItem quitMenuButton) {
		this.quitMenuButton = quitMenuButton;
	}

	/**
	 * @return the saveAndQuitMenuButton
	 */
	public MenuItem getSaveAndQuitMenuButton() {
		return saveAndQuitMenuButton;
	}

	/**
	 * @param saveAndQuitMenuButton the saveAndQuitMenuButton to set
	 */
	public void setSaveAndQuitMenuButton(MenuItem saveAndQuitMenuButton) {
		this.saveAndQuitMenuButton = saveAndQuitMenuButton;
	}
}
