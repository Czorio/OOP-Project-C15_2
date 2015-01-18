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

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class PostLeagueViewController implements Initializable, Observer {
	
	@FXML private Button doneButton;
	@FXML private Label teamPosLabel;

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
