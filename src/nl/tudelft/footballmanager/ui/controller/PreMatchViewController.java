/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Player;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class PreMatchViewController implements Initializable, Observer {
	
	private static final String preMatchViewFileName = "ui/view/PreMatchView.fxml";
	
	@FXML private TableView<Player> playerTable;
	@FXML private TableColumn<Player, String> nameCol;
	@FXML private TableColumn<Player, Integer> offCol;
	@FXML private TableColumn<Player, Integer> defCol;
	@FXML private TableColumn<Player, Integer> staminaCol;
	@FXML private TableColumn<Player, String> positionCol;
	
	@FXML private Button playButton;
	@FXML private Button cancelButton;
	
	private static GameState gameState = null;

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
		playerTable.setItems(FXCollections.observableList(gameState.getMyTeam().getPlayers()));
		
//		positionCol.setCellFactory(new Callback() {
//
//			@Override
//			public Object call(Object param) {
//				List<String> choices = new ArrayList<String>();
//				choices.add("Attacker");
//				choices.add("Midfielder");
//				choices.add("Defender");
//				choices.add("Goalkeeper");
//				return new ChoiceBoxTableCell(choices);
//			}
//			
//		});
	}

	/**
	 * 
	 */
	public static void show(GameState gs) {
		gameState = gs;

		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(preMatchViewFileName));
		try {
			AnchorPane preMatchView = (AnchorPane) l.load();
			FootballManager.getStage().setScene(new Scene(preMatchView));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
