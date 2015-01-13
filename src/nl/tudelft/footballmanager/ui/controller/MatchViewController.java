/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.logic.TeamSetUp;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class MatchViewController implements Initializable, Observer {
	
	@FXML private TableView<Player> playerTable;
	@FXML private TableColumn<Player, String> lastNameCol;
	@FXML private TableColumn<Player, String> positionCol;
	@FXML private TableColumn<Player, Integer> offCol;
	@FXML private TableColumn<Player, Integer> defCol;
	@FXML private TableColumn<Player, Integer> staminaCol;
	
	@FXML private ComboBox<TeamSetUp> formationComboBox;
	
	@FXML private Button playButton;
	@FXML private Button cancelButton;

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
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		// TODO show the view
	}

}
