/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import nl.tudelft.footballmanager.Context;
import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Team;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class NewGameViewController implements Initializable, Observer {
	
	@FXML private Button cancelButton;
	@FXML private Button doneButton;
	@FXML private ListView<League> leagueListView;
	@FXML private ListView<Team> teamListView;
	@FXML private TextField coachNameTextField;
	
	Context instance;

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
		instance = Context.getInstance();
		
		cancelButton.setOnAction((event) -> {
			System.out.println(event.getSource());
		});
		
		doneButton.setOnAction((event) -> {
			League selectedLeague = leagueListView.getSelectionModel().getSelectedItem();
			Team selectedTeam = teamListView.getSelectionModel().getSelectedItem();
			System.out.println(selectedTeam.getTeam() + " (" + selectedLeague.getLeague() + ") was selected.");
		});
		
		leagueListView.setCellFactory(new Callback<ListView<League>, ListCell<League>>() {
			@Override
			public ListCell<League> call(ListView<League> param) {
				ListCell<League> cell = new ListCell<League>() {
					protected void updateItem(League l, boolean empty) {
						super.updateItem(l, empty);
						if (l != null) setText(l.getLeague());
						else setText(null);
					}
				};
				return cell;
			}
		});
		
		leagueListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<League>() {
			@Override
			public void changed(ObservableValue<? extends League> observable, League oldValue, League newValue) {	
				ObservableList<Team> teams = FXCollections.observableArrayList(newValue.getTeams());
				FXCollections.sort(teams, Team.NAME_SORTER);
				
				if (teams != null) teamListView.setItems(teams);
				teamListView.getSelectionModel().clearSelection();
			}
		});
		
		teamListView.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>() {
			@Override
			public ListCell<Team> call(ListView<Team> param) {
				ListCell<Team> cell = new ListCell<Team>() {
					protected void updateItem(Team t, boolean empty) {
						super.updateItem(t, empty);
						if (t != null) setText(t.getTeam());
						else setText(null);
					}
				};
				return cell;
			}
		});
		
		teamListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
			@Override
			public void changed(ObservableValue<? extends Team> observable,	Team oldValue, Team newValue) {
				if (teamListView.getSelectionModel().getSelectedItem() == null) {
					doneButton.setDisable(true);
				} else {
					doneButton.setDisable(false);
				}
			}
		});
		
		ObservableList<League> leagues = FXCollections.observableArrayList(League.readAll());
		FXCollections.sort(leagues, League.NAME_COMPARATOR);
		leagueListView.setItems(leagues);
		leagueListView.getSelectionModel().clearSelection();
		doneButton.setDisable(true);
	}

}
