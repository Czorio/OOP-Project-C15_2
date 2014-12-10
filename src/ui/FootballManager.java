package ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import dataModel.GameState;
import dataModel.League;
import dataModel.Player;
import dataModel.Team;

public class FootballManager extends Application {
	
	public final static File PLAYER_DATABASE = new File("GameData/Eredivisie.xml");
	
	private static Stage stage;
	private BorderPane rootLayout;
	private static RootController rootController;
	private static TeamOverviewController teamOverviewController;

	@Override
	public void start(Stage stage) {
		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager");
		
		try {
			initRootLayout();
		} catch (IOException e) {
			System.err.println("Error loading UI root!");
			e.printStackTrace();
		}
		
		try {
			showTeamOverview();
		} catch (IOException e) {
			System.err.println("Error loading UI Team overview");
			e.printStackTrace();
		}
		
		rootController.setGameState(new GameState(null, 0, null, null));
		rootController.getGameState().addObserver(rootController);
		rootController.getGameState().setCoachName("Dummy Coach");
		rootController.getGameState().setLeague("Eredivisie");
		rootController.getGameState().setRound(8);
		rootController.getGameState().setTeam("SC Cambuur");
		
		if (GameState.isUseless(rootController.getGameState())) {
			System.out.println("GameState is empty, asking to load one.");
			rootController.setGameState(rootController.loadGame(rootController.getGameState()));
		}
		
		rootController.setLeague(League.readFromFile(PLAYER_DATABASE));
		System.out.println(rootController.getLeague().getTeam(rootController.getGameState().getTeam()).getPlayers());
		
		String team = rootController.getGameState().getTeam();
		League l = rootController.getLeague();
		Team t = l.getTeam(team);
		ObservableList<Player> players = FXCollections.observableList(t.getPlayers());
		TableView<Player> view = teamOverviewController.getPlayerTableView();
		
		if (view != null) {
			System.out.println("Filling table with data...");
			view.getItems().setAll(players);
		} else
			System.out.println("view == null");
	}
	
	public void initRootLayout() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/rootView.fxml"));
		rootLayout = (BorderPane) l.load();
		stage.setScene(new Scene(rootLayout));
		stage.show();
		
		rootController = l.getController();
	}
	
	public void showTeamOverview() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/TeamOverview.fxml"));
		AnchorPane teamOverview = (AnchorPane) l.load();
		
		rootLayout.setCenter(teamOverview);
		teamOverviewController = l.getController();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @param chooser
	 * @return
	 */
	public static File getOpenFile(FileChooser chooser) {
		return chooser.showOpenDialog(stage);
	}
	
	/**
	 * @param chooser
	 * @return
	 */
	public static File getSaveFile(FileChooser chooser) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String now = sdf.format(new Date());
		String fileName = now + ".xml";
		System.out.println("Filename: " + fileName);
		chooser.setInitialFileName(fileName);
		return chooser.showSaveDialog(stage);
	}

}
