/**
 * 
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Controller implements Initializable {

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void press(ActionEvent event) {
		System.out.println("Button was pressed!");
	}

}
