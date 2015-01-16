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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.logic.GameLogic;

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

	@FXML private Button continueButton;

	private static GameState gameState = null;

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initializing " + this.getClass());
		
		int gameRound = gameState.getGameRound();
		List<Match> matches = gameState.getMatchScheme().getMatchdays().get(gameRound).getMatches();

		continueButton.setOnAction((event) -> {
			RootViewController.show(gameState);
			System.out.println(event);
		});

		playedMatchesTeam1TableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getHome().getName());
			}
		});
		
		playedMatchesTeam2TableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getAway().getName());
			}
		});
		
		playedMatchesScoreTableColumn.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
			public ObservableStringValue call(CellDataFeatures<Match, String> p) {
				return new SimpleStringProperty(p.getValue().getMatchResult().getReadableScore());
			}
		});

		playedMatchesTableView.setItems(FXCollections.observableList(matches));
		
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
