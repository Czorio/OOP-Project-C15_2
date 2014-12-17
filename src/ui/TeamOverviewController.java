/**
 * 
 */
package ui;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dataModel.League;
import dataModel.Player;
import dataModel.Team;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class TeamOverviewController implements Initializable, Observer {
	
	@FXML private TableView<Player> playerTableView;
	@FXML private TableColumn<Player, String> firstNameCol;
	@FXML private TableColumn<Player, String> lastNameCol;
	@FXML private TableColumn<Player, String> positionCol;
	@FXML private TableColumn<Player, Integer> offCol;
	@FXML private TableColumn<Player, Integer> defCol;
	@FXML private TableColumn<Player, Integer> staminaCol;
	@FXML private TableColumn<Player, Integer> priceCol;
	
	Context instance;

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("An instance of " + o.getClass().toString() + " has changed!");
		
		if (o == instance.getGameState()) {
			String team = instance.getGameState().getTeam();
			League l = instance.getLeague();
			if (l != null) {
				Team t = l.getTeam(team);
				ObservableList<Player> players = FXCollections.observableList(t.getPlayers());
				TableView<Player> view = this.getPlayerTableView();
				
				if (view != null) {
					System.out.println("Filling table with data...");
					view.getItems().setAll(players);
				} else {
					System.out.println("view == null");
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = Context.getInstance();
	}

	/**
	 * @return the playerTableView
	 */
	public TableView<Player> getPlayerTableView() {
		return playerTableView;
	}

	/**
	 * @param playerTableView the playerTableView to set
	 */
	public void setPlayerTableView(TableView<Player> playerTableView) {
		this.playerTableView = playerTableView;
	}

	/**
	 * @return the firstNameCol
	 */
	public TableColumn<Player, String> getFirstNameCol() {
		return firstNameCol;
	}

	/**
	 * @param firstNameCol the firstNameCol to set
	 */
	public void setFirstNameCol(TableColumn<Player, String> firstNameCol) {
		this.firstNameCol = firstNameCol;
	}

	/**
	 * @return the lastNameCol
	 */
	public TableColumn<Player, String> getLastNameCol() {
		return lastNameCol;
	}

	/**
	 * @param lastNameCol the lastNameCol to set
	 */
	public void setLastNameCol(TableColumn<Player, String> lastNameCol) {
		this.lastNameCol = lastNameCol;
	}

	/**
	 * @return the positionCol
	 */
	public TableColumn<Player, String> getPositionCol() {
		return positionCol;
	}

	/**
	 * @param positionCol the positionCol to set
	 */
	public void setPositionCol(TableColumn<Player, String> positionCol) {
		this.positionCol = positionCol;
	}

	/**
	 * @return the offCol
	 */
	public TableColumn<Player, Integer> getOffCol() {
		return offCol;
	}

	/**
	 * @param offCol the offCol to set
	 */
	public void setOffCol(TableColumn<Player, Integer> offCol) {
		this.offCol = offCol;
	}

	/**
	 * @return the defCol
	 */
	public TableColumn<Player, Integer> getDefCol() {
		return defCol;
	}

	/**
	 * @param defCol the defCol to set
	 */
	public void setDefCol(TableColumn<Player, Integer> defCol) {
		this.defCol = defCol;
	}

	/**
	 * @return the staminaCol
	 */
	public TableColumn<Player, Integer> getStaminaCol() {
		return staminaCol;
	}

	/**
	 * @param staminaCol the staminaCol to set
	 */
	public void setStaminaCol(TableColumn<Player, Integer> staminaCol) {
		this.staminaCol = staminaCol;
	}

	/**
	 * @return the priceCol
	 */
	public TableColumn<Player, Integer> getPriceCol() {
		return priceCol;
	}

	/**
	 * @param priceCol the priceCol to set
	 */
	public void setPriceCol(TableColumn<Player, Integer> priceCol) {
		this.priceCol = priceCol;
	}

}
