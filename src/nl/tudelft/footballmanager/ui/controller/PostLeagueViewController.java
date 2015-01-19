/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nl.tudelft.footballmanager.model.GameState;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class PostLeagueViewController implements Initializable, Observer {
	
	public final static String PostLeaugeViewFileName = "ui/view/PostLeagueView.fxml";
	
	@FXML private Button doneButton;
	@FXML private Label teamPosLabel;
	
	private static GameState gameState = null;

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

	/**
	 * @param gameState2
	 */
	public static void show(GameState gs) {
		gameState = gs;
	}
}
