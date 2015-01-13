package nl.tudelft.footballmanager;

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
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.ui.controller.MatchViewController;
import nl.tudelft.footballmanager.ui.controller.NewGameViewController;
import nl.tudelft.footballmanager.ui.controller.RootController;
import nl.tudelft.footballmanager.ui.controller.TeamOverviewController;

public class FootballManager extends Application {
	public final static String rootViewFileName = "ui/view/RootView.fxml";
	public final static String teamOverviewFileName = "ui/view/TeamOverview.fxml";
	public final static String matchViewFileName = "ui/view/MatchView.fxml";
	public final static String newGameViewFileName = "ui/view/NewGameView.fxml";

	private static Stage stage;
	private BorderPane rootLayout;
	private static RootController rootController;
	private static TeamOverviewController teamOverviewController;
	private static MatchViewController matchViewController;
	private static NewGameViewController newGameViewController;

	static Context instance = Context.getInstance();

	@Override
	public void start(Stage stage) {
		FootballManager.stage = stage;
		FootballManager.stage.setTitle("Football Manager 2142");

//		try {
//			initRootLayout();
//		} catch (IOException e) {
//			System.err.println("Error loading UI root!");
//			e.printStackTrace();
//		}

		//		try {
		//			showMatchView();
		//		} catch (IOException e1) {
		//			e1.printStackTrace();
		//		}

		try {
			showNewGameView();
		} catch (IOException e) {
			System.out.println("Error loading New Game screen!");
			e.printStackTrace();
		}

//		try {
//			showTeamOverview();
//		} catch (IOException e) {
//			System.err.println("Error loading UI Team overview");
//			e.printStackTrace();
//		}

//		instance.addObserver(rootController);
//		instance.addObserver(teamOverviewController);

//		try {
//			instance.setLeague(League.readOne("Eredivisie"));
//
//			XMLPlayer xml = new XMLPlayer(new File("GameData/Leagues.xml"));
//			ArrayList<String> names = new ArrayList<String>();
//			names.add("Ajax");
//			names.add("Feyenoord");
//			names.add("SC Cambuur");
//			names.add("SC Cambuur");
//			System.out.println(xml.readFromFile(names));
//		} catch (FileNotFoundException e) {
//			System.err.println("Problem loading the league file. It was not found.");
//		}

		instance.setGameState(new GameState(null, 0, null, null, null));

//		instance.getGameState().addObserver(teamOverviewController);
//		instance.getGameState().addObserver(rootController);

//		if (GameState.isUseless(instance.getGameState())) {
//			System.out.println("GameState is empty, asking to load one...");
//			instance.setGameState(rootController.loadGame(instance.getGameState()));
//		}

		System.out.println(instance.getGameState());
	}

	public void initRootLayout() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(rootViewFileName));
		rootLayout = (BorderPane) l.load();
		rootController = l.getController();
		stage.setScene(new Scene(rootLayout));
		stage.show();
	}

	public void showNewGameView() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(newGameViewFileName));
		AnchorPane newGameLayout = (AnchorPane) l.load();
		newGameViewController = l.getController();
		stage.setScene(new Scene(newGameLayout));
		stage.show();
	}

	public void showTeamOverview() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(teamOverviewFileName));
		AnchorPane teamOverview = (AnchorPane) l.load();

		rootLayout.setCenter(teamOverview);
		teamOverviewController = l.getController();
	}

	public void showMatchView() throws IOException {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(matchViewFileName));
		AnchorPane matchView = (AnchorPane) l.load();

		rootLayout.setCenter(matchView);
		matchViewController = l.getController();
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
	 * @return the matchViewController
	 */
	public static MatchViewController getMatchViewController() {
		return matchViewController;
	}

	/**
	 * @param matchViewController the matchViewController to set
	 */
	public static void setMatchViewController(MatchViewController matchViewController) {
		FootballManager.matchViewController = matchViewController;
	}

}
