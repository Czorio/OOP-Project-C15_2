/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.logic.MarketplaceLogic;
import nl.tudelft.footballmanager.model.logic.TeamLogic;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class TeamOverviewController implements Initializable, Observer {
	
	private int fielded;

	public final static String teamOverviewFileName = "ui/view/TeamOverview.fxml";

	@FXML private TableView<Player> yourPlayerTableView;
	@FXML private TableColumn<Player, String> yourPlayerFirstNameCol;
	@FXML private TableColumn<Player, String> yourPlayerLastNameCol;
	@FXML private TableColumn<Player, String> yourPlayerPositionCol;
	@FXML private TableColumn<Player, String> yourPlayerCurPositionCol;
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
	@FXML private Label transferWindowLabel;
	@FXML private Button sellYourPlayerButton;

	SimpleStringProperty name;
	SimpleStringProperty position;
	SimpleIntegerProperty off;
	SimpleIntegerProperty def;
	SimpleIntegerProperty stamina;
	SimpleIntegerProperty price;

	@FXML private ChoiceBox<String> curPosChoiceBox;
	@FXML private Label placedPlayersLabel;

	@FXML private TableView<Player> otherPlayersTableView;
	@FXML private TableColumn<Player, String> otherPlayersFirstNameCol;
	@FXML private TableColumn<Player, String> otherPlayersLastNameCol;
	@FXML private TableColumn<Player, String> otherPlayersPositionCol;
	@FXML private TableColumn<Player, Integer> otherPlayersOffCol;
	@FXML private TableColumn<Player, Integer> otherPlayersDefCol;
	@FXML private TableColumn<Player, Integer> otherPlayersStaminaCol;
	@FXML private TableColumn<Player, Integer> otherPlayersPriceCol;
	@FXML private TableColumn<Player, String> otherPlayersTeamCol;

	@FXML private Label otherPlayerNameLabel;
	@FXML private Label otherPlayerPositionLabel;
	@FXML private Label otherPlayerOffensiveLabel;
	@FXML private Label otherPlayerDefensiveLabel;
	@FXML private Label otherPlayerStaminaLabel;
	@FXML private Label otherPlayerPriceLabel;
	@FXML private Label otherPlayerTeamLabel;
	@FXML private Label transferWindowLabel1;
	@FXML private Button buyOtherPlayerButton;

	SimpleStringProperty otherName;
	SimpleStringProperty otherPosition;
	SimpleIntegerProperty otherOff;
	SimpleIntegerProperty otherDef;
	SimpleIntegerProperty otherStamina;
	SimpleIntegerProperty otherPrice;
	SimpleStringProperty otherTeam;	

	private Player yourSelectedPlayer = new Player();
	private Player otherSelectedPlayer = new Player();
	private static GameState gameState = new GameState();
	
	SimpleListProperty<Player> yourPlayers = null;

	@Override
	public void update(Observable o, Object arg) {
		
		// TODO update your own fielded players upon change
		fielded = TeamLogic.getPlayingPlayersPerTeam(gameState.getMyTeam()).size();
		
		SimpleIntegerProperty inField = new SimpleIntegerProperty(fielded);
		placedPlayersLabel.textProperty().bind(inField.asString());

		if (yourSelectedPlayer == o) {
			try {
				name.set(yourSelectedPlayer.getFirstName().concat(" ").concat(yourSelectedPlayer.getLastName()));
				position.set(yourSelectedPlayer.getReadablePosition());
				off.set(yourSelectedPlayer.getOffensive());
				def.set(yourSelectedPlayer.getDefensive());
				stamina.set(yourSelectedPlayer.getStamina());
				price.set(yourSelectedPlayer.getPrice());
			} catch (NullPointerException e) {
				name.set(null);
				position.set(null);
				off.set(0);
				def.set(0);
				stamina.set(0);
				price.set(0);
			}
		} else if (otherSelectedPlayer == o) {
			try {
				otherName.set(otherSelectedPlayer.getFirstName().concat(" ").concat(otherSelectedPlayer.getLastName()));
				otherPosition.set(otherSelectedPlayer.getReadablePosition());
				otherOff.set(otherSelectedPlayer.getOffensive());
				otherDef.set(otherSelectedPlayer.getDefensive());
				otherStamina.set(otherSelectedPlayer.getStamina());
				otherPrice.set(otherSelectedPlayer.getPrice());
				otherTeam.set(otherSelectedPlayer.getClub());
			} catch (NullPointerException e) {
				otherName.set(null);
				otherPosition.set(null);
				otherOff.set(0);
				otherDef.set(0);
				otherStamina.set(0);
				otherPrice.set(0);
				otherTeam.set(null);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Get the amount of players of your team that are currently playing
		fielded = TeamLogic.getPlayingPlayersPerTeam(gameState.getMyTeam()).size();
		
		SimpleIntegerProperty inField = new SimpleIntegerProperty(fielded);
		placedPlayersLabel.textProperty().bind(inField.asString());
		
		
		SimpleStringProperty isTransfer = null;
		if(MarketplaceLogic.isTransferWindow(gameState.getGameRound())) {
			isTransfer = new SimpleStringProperty("Open");
		} else {
			isTransfer = new SimpleStringProperty("Closed");
		}
		
		transferWindowLabel.textProperty().bind(isTransfer);
		transferWindowLabel1.textProperty().bind(isTransfer);
		
		
		sellYourPlayerButton.setDisable(!MarketplaceLogic.isTransferWindow(gameState.getGameRound()));
		buyOtherPlayerButton.setDisable(!MarketplaceLogic.isTransferWindow(gameState.getGameRound()));
		
		yourPlayers = new SimpleListProperty<Player>(FXCollections.observableList(gameState.getMyTeam().getPlayers()));

		curPosChoiceBox.setItems(FXCollections.observableArrayList(
				"None",
				"Goalkeeper",
				"Attacker",
				"Midfielder",
				"Defender"));
		
		// TODO Selecting none should remove player from playingPlayers, update when selecting different player
		curPosChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println(yourPlayerTableView.getSelectionModel().getSelectedItem());
				yourPlayerTableView.getSelectionModel().getSelectedItem().setCurPosition(newValue);
				System.out.println(yourPlayerTableView.getSelectionModel().getSelectedItem());
				
				/*if(newValue == "None") {
					decreaseFielded();
				} else {
					increaseFielded();
				}*/
			}
		});

		sellYourPlayerButton.setOnAction((event) -> {
			String p = yourSelectedPlayer.getFirstName() + " " + yourSelectedPlayer.getLastName();
			
			// TODO implement selling player, sells to 3rd team in league now
			boolean res = MarketplaceLogic.transferPlayer(yourSelectedPlayer.getTeam(), gameState.getLeague().getTeams().get(3), yourSelectedPlayer, 1);
			System.out.println("SELLING " + p + (res == true ? " succeeded!" : " failed"));
		});

		yourPlayerTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
			@Override
			public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {
				yourSelectedPlayer = newValue;
				update(yourSelectedPlayer, null);
			}
		});

		otherPlayersTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
			@Override
			public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {
				otherSelectedPlayer = newValue;
				update(otherSelectedPlayer, null);
			}
		});
		
		otherPlayersTeamCol.setCellValueFactory((param) -> {
			return new SimpleStringProperty(param.getValue().getClub());
		}); 

		bindYourPlayerStats();
		bindOtherPlayerStats();

		yourPlayerTableView.itemsProperty().bind(yourPlayers);
		yourPlayerTableView.getSelectionModel().select(0);

		gameState.addObserver(this);
		yourSelectedPlayer.addObserver(this);

		ObservableList<Player> otherPlayers = FXCollections.observableList(new ArrayList<Player>());
		for (Team t : gameState.getLeague().getTeams()) {
			if (t == gameState.getMyTeam()) continue;
			otherPlayers.addAll(t.getPlayers());
		}

		otherPlayersTableView.setItems(otherPlayers);
		//		System.out.println(otherPlayers);
	}

	/**
	 * 
	 */
	private void bindOtherPlayerStats() {
		otherName = new SimpleStringProperty();
		otherPlayerNameLabel.textProperty().bind(otherName);
		otherPosition = new SimpleStringProperty();
		otherPlayerPositionLabel.textProperty().bind(otherPosition);
		otherOff = new SimpleIntegerProperty();
		otherPlayerOffensiveLabel.textProperty().bind(otherOff.asString());
		otherDef = new SimpleIntegerProperty();
		otherPlayerDefensiveLabel.textProperty().bind(otherDef.asString());
		otherStamina = new SimpleIntegerProperty();
		otherPlayerStaminaLabel.textProperty().bind(otherStamina.asString());
		otherPrice = new SimpleIntegerProperty();
		otherPlayerPriceLabel.textProperty().bind(otherPrice.asString());
		otherTeam = new SimpleStringProperty();
		otherPlayerTeamLabel.textProperty().bind(otherTeam);
	}

	/**
	 * 
	 */
	private void bindYourPlayerStats() {
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
	}

	public static void show(Pane rootLayout, GameState gs) {
		gameState = gs;

		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(teamOverviewFileName));
		try {
			AnchorPane teamOverview = (AnchorPane) l.load();
			((BorderPane) rootLayout).setCenter(teamOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the yourSelectedPlayer
	 */
	public Player getSelectedPlayer() {
		return yourSelectedPlayer;
	}

	/**
	 * @return the gameState
	 */
	public static GameState getGameState() {
		return gameState;
	}
	
	private void decreaseFielded() {
		if (fielded > 0) {
			fielded--;
		} else {
			fielded = 0;
		}
	}
	
	private void increaseFielded() {
		if(fielded < 11) {
			fielded++;
		} else {
			fielded = 11;
		}
	}
}