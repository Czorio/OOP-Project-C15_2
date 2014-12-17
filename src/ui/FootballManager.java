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
	private static RootController rootController;
	private static TeamOverviewController teamOverviewController;
	
	static Context instance;

	@Override
	public void start(Stage stage) {
		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager");
		
		instance.setLeague(League.readFromFile(PLAYER_DATABASE));
		
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
		
		instance.setGameState(new GameState(null, 0, null, null));
		instance.getGameState().addObserver(teamOverviewController);
		instance.getGameState().addObserver(rootController);
		
		instance.getGameState().setCoachName("Dummy Coach");
		instance.getGameState().setLeague("Eredivisie");
		instance.getGameState().setRound(8);
		instance.getGameState().setTeam("SC Cambuur");
		
		if (GameState.isUseless(instance.getGameState())) {
			System.out.println("GameState is empty, asking to load one.");
			instance.setGameState(rootController.loadGame(instance.getGameState()));
		}
		
		// Ik wil wel m'n console kunnen zien ;)
		//sudowoodoSays("Game is loaded");
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
		instance = Context.getInstance();
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
	
	private static void sudowoodoSays(String message) {
        System.out.println("     _              __");
        System.out.println("    / `\\  (~._    ./  )");
        System.out.println("    \\__/ __`-_\\__/ ./");
        System.out.println("   _ \\ \\/  \\  \\ |_   __");
        System.out.println(" (   )  \\__/ -^    \\ /  \\");
        System.out.println("  \\_/ \"  \\  | o  o  |.. /  __");
        System.out.println("       \\\\. --' ====  /  || /  \\");
        System.out.println("         \\   .  .  |---__.\\__/");
        System.out.println("         /  :     /   |   |");
        System.out.println("         /   :   /     \\_/");
        System.out.println("      --/ ::    (");
        System.out.println("     (  |     (  (____");
        System.out.println("   .--  .. ----**.____)");
        System.out.println("   \\___/          ");
        System.out.println("Sudowoodo says: " + message);
	}

	/**
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public static void setStage(Stage stage) {
		FootballManager.stage = stage;
	}

	/**
	 * @return the rootLayout
	 */
	public BorderPane getRootLayout() {
		return rootLayout;
	}

	/**
	 * @param rootLayout the rootLayout to set
	 */
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	/**
	 * @return the rootController
	 */
	public static RootController getRootController() {
		return rootController;
	}

	/**
	 * @param rootController the rootController to set
	 */
	public static void setRootController(RootController rootController) {
		FootballManager.rootController = rootController;
	}

	/**
	 * @return the teamOverviewController
	 */
	public static TeamOverviewController getTeamOverviewController() {
		return teamOverviewController;
	}

	/**
	 * @param teamOverviewController the teamOverviewController to set
	 */
	public static void setTeamOverviewController(
			TeamOverviewController teamOverviewController) {
		FootballManager.teamOverviewController = teamOverviewController;
	}

	/**
	 * @return the instance
	 */
	public static Context getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(Context instance) {
		FootballManager.instance = instance;
	}

	/**
	 * @return the playerDatabase
	 */
	public static File getPlayerDatabase() {
		return PLAYER_DATABASE;
	}

}
