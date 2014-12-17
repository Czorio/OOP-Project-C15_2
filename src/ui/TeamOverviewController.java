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
import javafx.scene.control.Label;
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

	@FXML private TableView<Player> yourPlayerTableView;
	@FXML private TableColumn<Player, String> yourPlayerFirstNameCol;
	@FXML private TableColumn<Player, String> yourPlayerLastNameCol;
	@FXML private TableColumn<Player, String> yourPlayerPositionCol;
	@FXML private TableColumn<Player, Integer> yourPlayerOffCol;
	@FXML private TableColumn<Player, Integer> yourPlayerDefCol;
	@FXML private TableColumn<Player, Integer> yourPlayerStaminaCol;
	@FXML private TableColumn<Player, Integer> yourPlayerPriceCol;
	@FXML private Label yourPlayerNameLabel;
	@FXML private Label yourPlayerPositionLabel;
	@FXML private Label yourPlayerOffensiveLabel;
	@FXML private Label yourPlayerDefensiveLabel;
	@FXML private Label yourPlayerStaminaLabel;
	@FXML private Label yourPlayerPriceLabel;
	@FXML private Label yourPlayerTeamLabel;

	Context instance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("An instance of " + o.getClass().toString() + " has changed! (TeamOverview)");

		if (o == instance.getGameState()) {
			String team = instance.getGameState().getTeam();
			League l = instance.getLeague();
			Team t;
			if (l != null && null != (t = l.getTeam(team))) {
				ObservableList<Player> players = FXCollections.observableList(t.getPlayers());
				yourPlayerTableView.getItems().setAll(players);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = Context.getInstance();
		
		getYourPlayerTableView().setOnMouseClicked((event) -> {
			System.out.println(event);
			Player clickedPlayer = getYourPlayerTableView().getSelectionModel().getSelectedItem();
			
//			System.out.println(getPlayerNameLabel());
//			System.out.println(getPlayerPositionLabel());
//			System.out.println(getPlayerOffensiveLabel());
//			System.out.println(getPlayerDefensiveLabel());
//			System.out.println(getPlayerStaminaLabel());
//			System.out.println(getPlayerPriceLabel());
//			System.out.println(getPlayerTeamLabel());
			
			getYourPlayerNameLabel().setText(clickedPlayer.getLastName() + ", " + clickedPlayer.getFirstName());
			getYourPlayerPositionLabel().setText(clickedPlayer.getPosition());
			getYourPlayerOffensiveLabel().setText(String.valueOf(clickedPlayer.getOffensive()));
			getYourPlayerDefensiveLabel().setText(String.valueOf(clickedPlayer.getDefensive()));
			getYourPlayerStaminaLabel().setText(String.valueOf(clickedPlayer.getStamina()));
			getYourPlayerPriceLabel().setText(String.valueOf(clickedPlayer.getPrice()));
			getYourPlayerTeamLabel().setText(clickedPlayer.getClub());
		});
	}

	/**
	 * @return the yourPlayerTableView
	 */
	public TableView<Player> getYourPlayerTableView() {
		return yourPlayerTableView;
	}

	/**
	 * @param yourPlayerTableView the yourPlayerTableView to set
	 */
	public void setYourPlayerTableView(TableView<Player> yourPlayerTableView) {
		this.yourPlayerTableView = yourPlayerTableView;
	}

	/**
	 * @return the yourPlayerFirstNameCol
	 */
	public TableColumn<Player, String> getYourPlayerFirstNameCol() {
		return yourPlayerFirstNameCol;
	}

	/**
	 * @param yourPlayerFirstNameCol the yourPlayerFirstNameCol to set
	 */
	public void setYourPlayerFirstNameCol(
			TableColumn<Player, String> yourPlayerFirstNameCol) {
		this.yourPlayerFirstNameCol = yourPlayerFirstNameCol;
	}

	/**
	 * @return the yourPlayerLastNameCol
	 */
	public TableColumn<Player, String> getYourPlayerLastNameCol() {
		return yourPlayerLastNameCol;
	}

	/**
	 * @param yourPlayerLastNameCol the yourPlayerLastNameCol to set
	 */
	public void setYourPlayerLastNameCol(
			TableColumn<Player, String> yourPlayerLastNameCol) {
		this.yourPlayerLastNameCol = yourPlayerLastNameCol;
	}

	/**
	 * @return the yourPlayerPositionCol
	 */
	public TableColumn<Player, String> getYourPlayerPositionCol() {
		return yourPlayerPositionCol;
	}

	/**
	 * @param yourPlayerPositionCol the yourPlayerPositionCol to set
	 */
	public void setYourPlayerPositionCol(
			TableColumn<Player, String> yourPlayerPositionCol) {
		this.yourPlayerPositionCol = yourPlayerPositionCol;
	}

	/**
	 * @return the yourPlayerOffCol
	 */
	public TableColumn<Player, Integer> getYourPlayerOffCol() {
		return yourPlayerOffCol;
	}

	/**
	 * @param yourPlayerOffCol the yourPlayerOffCol to set
	 */
	public void setYourPlayerOffCol(TableColumn<Player, Integer> yourPlayerOffCol) {
		this.yourPlayerOffCol = yourPlayerOffCol;
	}

	/**
	 * @return the yourPlayerDefCol
	 */
	public TableColumn<Player, Integer> getYourPlayerDefCol() {
		return yourPlayerDefCol;
	}

	/**
	 * @param yourPlayerDefCol the yourPlayerDefCol to set
	 */
	public void setYourPlayerDefCol(TableColumn<Player, Integer> yourPlayerDefCol) {
		this.yourPlayerDefCol = yourPlayerDefCol;
	}

	/**
	 * @return the yourPlayerStaminaCol
	 */
	public TableColumn<Player, Integer> getYourPlayerStaminaCol() {
		return yourPlayerStaminaCol;
	}

	/**
	 * @param yourPlayerStaminaCol the yourPlayerStaminaCol to set
	 */
	public void setYourPlayerStaminaCol(
			TableColumn<Player, Integer> yourPlayerStaminaCol) {
		this.yourPlayerStaminaCol = yourPlayerStaminaCol;
	}

	/**
	 * @return the yourPlayerPriceCol
	 */
	public TableColumn<Player, Integer> getYourPlayerPriceCol() {
		return yourPlayerPriceCol;
	}

	/**
	 * @param yourPlayerPriceCol the yourPlayerPriceCol to set
	 */
	public void setYourPlayerPriceCol(
			TableColumn<Player, Integer> yourPlayerPriceCol) {
		this.yourPlayerPriceCol = yourPlayerPriceCol;
	}

	/**
	 * @return the yourPlayerNameLabel
	 */
	public Label getYourPlayerNameLabel() {
		return yourPlayerNameLabel;
	}

	/**
	 * @param yourPlayerNameLabel the yourPlayerNameLabel to set
	 */
	public void setYourPlayerNameLabel(Label yourPlayerNameLabel) {
		this.yourPlayerNameLabel = yourPlayerNameLabel;
	}

	/**
	 * @return the yourPlayerPositionLabel
	 */
	public Label getYourPlayerPositionLabel() {
		return yourPlayerPositionLabel;
	}

	/**
	 * @param yourPlayerPositionLabel the yourPlayerPositionLabel to set
	 */
	public void setYourPlayerPositionLabel(Label yourPlayerPositionLabel) {
		this.yourPlayerPositionLabel = yourPlayerPositionLabel;
	}

	/**
	 * @return the yourPlayerOffensiveLabel
	 */
	public Label getYourPlayerOffensiveLabel() {
		return yourPlayerOffensiveLabel;
	}

	/**
	 * @param yourPlayerOffensiveLabel the yourPlayerOffensiveLabel to set
	 */
	public void setYourPlayerOffensiveLabel(Label yourPlayerOffensiveLabel) {
		this.yourPlayerOffensiveLabel = yourPlayerOffensiveLabel;
	}

	/**
	 * @return the yourPlayerDefensiveLabel
	 */
	public Label getYourPlayerDefensiveLabel() {
		return yourPlayerDefensiveLabel;
	}

	/**
	 * @param yourPlayerDefensiveLabel the yourPlayerDefensiveLabel to set
	 */
	public void setYourPlayerDefensiveLabel(Label yourPlayerDefensiveLabel) {
		this.yourPlayerDefensiveLabel = yourPlayerDefensiveLabel;
	}

	/**
	 * @return the yourPlayerStaminaLabel
	 */
	public Label getYourPlayerStaminaLabel() {
		return yourPlayerStaminaLabel;
	}

	/**
	 * @param yourPlayerStaminaLabel the yourPlayerStaminaLabel to set
	 */
	public void setYourPlayerStaminaLabel(Label yourPlayerStaminaLabel) {
		this.yourPlayerStaminaLabel = yourPlayerStaminaLabel;
	}

	/**
	 * @return the yourPlayerPriceLabel
	 */
	public Label getYourPlayerPriceLabel() {
		return yourPlayerPriceLabel;
	}

	/**
	 * @param yourPlayerPriceLabel the yourPlayerPriceLabel to set
	 */
	public void setYourPlayerPriceLabel(Label yourPlayerPriceLabel) {
		this.yourPlayerPriceLabel = yourPlayerPriceLabel;
	}

	/**
	 * @return the yourPlayerTeamLabel
	 */
	public Label getYourPlayerTeamLabel() {
		return yourPlayerTeamLabel;
	}

	/**
	 * @param yourPlayerTeamLabel the yourPlayerTeamLabel to set
	 */
	public void setYourPlayerTeamLabel(Label yourPlayerTeamLabel) {
		this.yourPlayerTeamLabel = yourPlayerTeamLabel;
	}
}