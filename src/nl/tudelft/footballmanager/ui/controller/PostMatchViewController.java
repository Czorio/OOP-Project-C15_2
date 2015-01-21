/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.logic.GameLogic;
import nl.tudelft.footballmanager.model.logic.MarketplaceLogic;


/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class PostMatchViewController implements Initializable, Observer {

	public final static String postMatchViewFileName = "ui/view/PostMatchView.fxml";

	@FXML private TableView<Match> playedMatchesTableView;
	@FXML private TableColumn<Match, String> playedMatchesTeam1TableColumn;
	@FXML private TableColumn<Match, String> playedMatchesTeam2TableColumn;
	@FXML private TableColumn<Match, String> playedMatchesScoreTableColumn;

	@FXML private Label incomeLabel;
	@FXML private Button continueButton;

	private static GameState gameState = null;

	private static Callback<TableColumn<Match, String>, TableCell<Match, String>> highlightMyTeam = new Callback<TableColumn<Match, String>, TableCell<Match, String>>() {
		@Override
		public TableCell<Match, String> call(TableColumn<Match, String> param) {
			return new TableCell<Match, String>() {
				@Override
				protected void updateItem(String teamName, boolean empty) {
					if (teamName == null || empty) {
						this.setText(null);
						this.setTextFill(Color.WHITE);
					} else if (teamName.equals(gameState.getMyTeamName())) {
						this.setText(teamName);
						this.setTextFill(Color.RED);
					} else {
						this.setText(teamName);
						this.setTextFill(Color.BLACK);
					}
				}
			};
		}
	};

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initializing " + this.getClass());
		
		incomeLabel.textProperty().bind(new SimpleIntegerProperty(gameLogic.getMatchIncome()).asString());

		int gameRound = gameState.getGameRound();
		try {
			List<Match> matches = gameState.getMatchScheme().getMatchdays().get(gameRound).getMatches();
			for (int i = 0; i < matches.size(); i++) {
				Match m = matches.get(i);
				if (m.getHome().getName().equals(gameState.getMyTeamName()) || m.getAway().getName().equals(gameState.getMyTeamName())) {
					Match tmp = matches.get(0);
					matches.set(0, m);
					matches.set(i, tmp);
				}
			}
			playedMatchesTableView.setItems(FXCollections.observableList(matches));
		} catch (IndexOutOfBoundsException e) {
			PostLeagueViewController.show(gameState);
		}

		continueButton.setOnAction((event) -> {
			// After the match, players will be trasfered, so they will be visible when the RootViewController is showed.
			MarketplaceLogic.RandomPlayerTransfer(gameState.getGameRound(), gameState.getLeague(), gameState.getMyTeam());

			RootViewController.show(gameState);


		});

		playedMatchesTeam1TableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getHome().getName());
			}
		});

		playedMatchesTeam1TableColumn.setCellFactory(highlightMyTeam);

		playedMatchesTeam2TableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getAway().getName());
			}
		});

		playedMatchesTeam2TableColumn.setCellFactory(highlightMyTeam);

		playedMatchesScoreTableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getMatchResult().getReadableScore());
			}
		});

		new GameLogic(gameState);
		GameLogic.matchDay();

		System.out.println("Initializing " + this.getClass() + " finished");
}

public static void show(GameState gs) {
	gameState = gs;

	FXMLLoader l = new FXMLLoader();
	l.setLocation(FootballManager.class.getResource(postMatchViewFileName));
	try {
		AnchorPane postMatchView = (AnchorPane) l.load();
		FootballManager.getStage().setScene(new Scene(postMatchView));
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
