/**
 * 
 */
package ui;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import dataModel.GameState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class TitleScreenController implements Initializable, Observer {
	
	@FXML private Button newGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button quitGameButton;

	Context instance;
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loadGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			instance.setGameState(loadGame(instance.getGameState()));
		});
		
		// Quit application
		quitGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			Platform.exit();
			System.out.println("Game quit!");
		});

	}
	
	public GameState loadGame(GameState gamestate) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Load game");
		configureFileChooser(chooser);
		
		// file type filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
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
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML Game files", ".xml"));
	}

}
