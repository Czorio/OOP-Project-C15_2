package ui;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import dataModel.GameState;
import dataModel.League;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Controller implements Initializable, Observer {

	@FXML
	private Button saveGameButton;

	@FXML
	private Button loadGameButton;

	@FXML
	private Label gamesPlayed;

	private GameState gamestate;
	private League league;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		saveGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(getGameState());
			if (!result) {
				System.out.println("Game not saved!");
			}
		});

		loadGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			setGamestate(loadGame(getGameState()));
		});
	}

	public boolean saveGame(GameState state) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save game");
		configureFileChooser(chooser);

		File selectedFile = FootballManager.getSaveFile(chooser);
		if (selectedFile != null) {
			System.out.println("Save file: " + selectedFile.getAbsolutePath());
			state.saveGameState(selectedFile);
			return true;
		} else {
			System.out.println("No file selected!");
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
			System.out.println("No file selected!");
		}

		return gamestate;
	}

	public void configureFileChooser(FileChooser fc) {
		// Standard dir is working dir of application
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML Game files", ".xml"));
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("An instance of " + o.getClass().toString() + " has changed!");
		
		if (o == gamestate) {
			this.gamesPlayed.setText(String.valueOf(gamestate.getRound()));
		}
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
	 * @return the gamestate
	 */
	public GameState getGameState() {
		return gamestate;
	}

	/**
	 * @param gamestate the gamestate to set
	 */
	public void setGamestate(GameState gamestate) {
		this.gamestate = gamestate;
	}

}
