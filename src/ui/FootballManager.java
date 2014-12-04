package ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import dataModel.GameState;
import dataModel.League;

public class FootballManager extends Application {
	
	public final static File PLAYER_DATABASE = new File("GameData/Eredivisie.xml");
	
	private static Stage stage;
	private BorderPane rootLayout;
	private static Controller c;

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
		
		c.setGameState(new GameState(null, 0, null, null));
		c.getGameState().addObserver(c);
		c.getGameState().setCoachName("Dummy Coach");
		c.getGameState().setLeague("Eredivisie");
		c.getGameState().setRound(8);
		c.getGameState().setTeam("SC Cambuur");
		
		if (GameState.isUseless(c.getGameState())) {
			System.out.println("GameState is empty, asking to load one.");
			c.setGameState(c.loadGame(c.getGameState()));
		}
		
		c.setLeague(League.readFromFile(PLAYER_DATABASE));
		System.out.println(c.getLeague().getTeam(c.getGameState().getTeam()).getPlayers());
		
//		String team = c.getGameState().getTeam();
//		League l = c.getLeague();
//		Team t = l.getTeam(team);
////		ObservableList<Player> players = FXCollections.observableList(t.getPlayers());
//		TableView<Player> view = c.getPlayerTable();
//		
//		if (view != null) {
//			System.out.println("Filling table with data...");
//			view.getItems().setAll(t.getPlayers());
//		}
//		else
//			System.out.println("view == null");
//		
	}
	
	public void initRootLayout() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/rootView.fxml"));
		rootLayout = (BorderPane) l.load();
		stage.setScene(new Scene(rootLayout));
		stage.show();
		
		c = l.getController();
	}
	
	public void showTeamOverview() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource("view/TeamOverview.fxml"));
		AnchorPane teamOverview = (AnchorPane) l.load();
		
		rootLayout.setCenter(teamOverview);
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
