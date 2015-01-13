/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.logic.MarketplaceLogic;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class TeamOverviewController implements Initializable, Observer {

	public final static String teamOverviewFileName = "ui/view/TeamOverview.fxml";

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
	@FXML private Button sellYourPlayerButton;

	SimpleStringProperty name;
	SimpleStringProperty position;
	SimpleIntegerProperty off;
	SimpleIntegerProperty def;
	SimpleIntegerProperty stamina;
	SimpleIntegerProperty price;
	SimpleStringProperty team;

	private Player selectedPlayer = new Player();
	private static GameState gameState = new GameState();

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(String.format("%s:\n\t%s\n\t%s", this.getClass(), o, arg));

		if (selectedPlayer == o) {
			try {
				name.set(selectedPlayer.getFirstName().concat(" ").concat(selectedPlayer.getLastName()));
				position.set(selectedPlayer.getReadablePosition());
				off.set(selectedPlayer.getOffensive());
				def.set(selectedPlayer.getDefensive());
				stamina.set(selectedPlayer.getStamina());
				price.set(selectedPlayer.getPrice());
				team.set(selectedPlayer.getClub());
			} catch (NullPointerException e) {}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sellYourPlayerButton.setOnAction((event) -> {
			String p = selectedPlayer.getFirstName() + " " + selectedPlayer.getLastName();
			boolean res = MarketplaceLogic.transferPlayer(selectedPlayer.getTeam(), gameState.getLeague().getTeams().get(3), selectedPlayer, 1);
			System.out.println("SELLING " + p + (res == true ? " succeeded!" : " failed"));
		});

		yourPlayerTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
			@Override
			public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {
				selectedPlayer = newValue;
				update(selectedPlayer, null);
			}
		});

		name = new SimpleStringProperty();
		yourPlayerNameLabel.textProperty().bind(name);
		position = new SimpleStringProperty();
		yourPlayerPositionLabel.textProperty().bind(position);
		off = new SimpleIntegerProperty();
		yourPlayerOffensiveLabel.textProperty().bind(off.asString());
		def = new SimpleIntegerProperty();
		yourPlayerDefensiveLabel.textProperty().bind(def.asString());
		stamina = new SimpleIntegerProperty();
		yourPlayerStaminaLabel.textProperty().bind(stamina.asString());
		price = new SimpleIntegerProperty();
		yourPlayerPriceLabel.textProperty().bind(price.asString());
		team = new SimpleStringProperty();
		yourPlayerTeamLabel.textProperty().bind(team);

		yourPlayerTableView.setItems((ObservableList<Player>) gameState.getMyTeam().getPlayers());
		gameState.addObserver(this);
		selectedPlayer.addObserver(this);
	}

	public static void show(Pane rootLayout, GameState gs) {
		gameState = gs;

		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(teamOverviewFileName));
		try {
			System.out.println(l);
			AnchorPane teamOverview = (AnchorPane) l.load();
			((BorderPane) rootLayout).setCenter(teamOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the selectedPlayer
	 */
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	/**
	 * @return the gameState
	 */
	public static GameState getGameState() {
		return gameState;
	}
}