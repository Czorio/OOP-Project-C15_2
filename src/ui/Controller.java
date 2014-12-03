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

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Controller implements Initializable {
	
	@FXML
	private Button saveGameButton;
	
	@FXML
	private Button loadGameButton;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		saveGameButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			saveGame(event);
		});
	}
	
	@FXML
	public void saveGame(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save game location");
		configureFileChooser(chooser);
		
		//TODO fix stage binding
		File selectedFile = chooser.showOpenDialog(null);
		System.out.println("File: " + selectedFile.getAbsolutePath());
	}
	
	public void configureFileChooser(FileChooser fc) {
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML Game files", ".xml"));
	}

}
