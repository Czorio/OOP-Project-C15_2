/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class PostLeagueViewController implements Initializable, Observer {
	
	public final static String PostLeaugeViewFileName = "ui/view/PostLeagueView.fxml";
	private static GameState gameState = new GameState();
	
	@FXML private Button doneButton;
	@FXML private Label teamPosLabel;

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Quit to Menu
		doneButton.setOnAction((event) -> {
			System.out.println(event.getSource());
			TitleScreenController.show();
			System.out.println("Returned to Menu!");
		});
		
	}
}
