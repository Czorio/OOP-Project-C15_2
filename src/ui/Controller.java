package ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import dataModel.GameState;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Controller implements Initializable {
	
	@FXML
	private Button saveGameButton;
	
	@FXML
	private Button loadGameButton;
	
	private GameState state;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saveGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(event, state);
			if (!result) {
				System.out.println("Game not saved!");
			}
		});
		
		loadGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			this.state = loadGame(event);
		});
	}
	
	public boolean saveGame(ActionEvent event, GameState state) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save game");
		configureFileChooser(chooser);
		
		File selectedFile = FootballManager.getFile(chooser);
		if (selectedFile != null) {
			System.out.println("File: " + selectedFile.getAbsolutePath());
			state.saveGameState(selectedFile);
			return true;
		} else {
			System.out.println("No file selected!");
			return false;
		}

	}
	
	public GameState loadGame(ActionEvent event) {
		GameState state = null;
		
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Load game");
		configureFileChooser(chooser);
		
		File selectedFile = FootballManager.getFile(chooser);
		if (selectedFile != null) {
			System.out.println("File: " + selectedFile.getAbsolutePath());

			state = new GameState(selectedFile);
			System.out.println("GameState: " + state.toString());
		} else {
			System.out.println("No file selected!");
		}
		
		return state;
	}
	
	public void configureFileChooser(FileChooser fc) {
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML Game files", ".xml"));
	}

}
