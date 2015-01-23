package nl.tudelft.footballmanager.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import nl.tudelft.footballmanager.FootballManager;
import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Team;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class RootViewController implements Initializable, Observer {

	public final static String rootViewFileName = "ui/view/RootView.fxml";

	@FXML private Button saveGameButton;
	@FXML private Button loadGameButton;

	@FXML private Label yourTeamNameLabel;
	@FXML private Label leagueNameLabel;
	
	
	// TODO make gamesWonLabel, gamesLostLabel, gamesDrawLabel and teamPosLabel work
	@FXML private Label gamesPlayedLabel;
	@FXML private Label gamesWonLabel;
	@FXML private Label gamesLostLabel;
	@FXML private Label gamesDrawLabel;
	@FXML private Label leaguePointsLabel;
	@FXML private Label teamPosLabel;

	@FXML private Label gamesPlayed;
	@FXML private Label teamBalanceLabel;
	@FXML private Button nextRoundButton;
	@FXML private MenuItem saveAndQuitDesktopMenuItem;
	@FXML private MenuItem saveAndQuitMenuItem;
	@FXML private MenuItem quitMenuMenuItem;
	@FXML private MenuItem quitDesktopMenuItem;
	@FXML private TableView<Team> leagueScoreboardTableView;
	//	@FXML private TableColumn<Team, Integer> leaguePosTableColumn;
	@FXML private TableColumn<Team, String> leagueTeamTableColumn;
	@FXML private TableColumn<Team, Integer> leagueScoreTableColumn;
	@FXML private Accordion sidebarAccordion;

	private static GameState gameState = null;
	
	private static Callback<TableColumn<Team, String>, TableCell<Team, String>> highlightMyTeam = new Callback<TableColumn<Team, String>, TableCell<Team, String>>() {
		@Override
		public TableCell<Team, String> call(TableColumn<Team, String> param) {
			return new TableCell<Team, String>() {
				@Override
				protected void updateItem(String teamName, boolean empty) {
					if (teamName == null || empty) {
						this.setText(null);
						this.setTextFill(Color.WHITE);
					} else if (teamName.equals(gameState.getMyTeamName())) {
						this.setText(teamName);
						this.setTextFill(Color.BLUE);
						this.setStyle("-fx-font-weight: bold");
					} else {
						this.setText(teamName);
						this.setTextFill(Color.BLACK);
					}
				}
			};
		}
	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		gameState.addObserver(this);

		SimpleIntegerProperty round = new SimpleIntegerProperty(gameState.getGameRound());
		gamesPlayed.textProperty().bind(round.asString());
		//gamesPlayedLabel.textProperty().bind(round.asString());

		SimpleIntegerProperty balance = new SimpleIntegerProperty(gameState.getMyTeam().getBudget());
		teamBalanceLabel.textProperty().bind(balance.asString());

		Map<Team, Integer> scores = gameState.getOverallScores();
		System.out.println("Scores: " + scores);
		leagueScoreboardTableView.setItems(FXCollections.observableList(gameState.getLeague().getTeams()));
		
		leagueNameLabel.setText(gameState.getLeagueName());
		yourTeamNameLabel.setText(gameState.getMyTeamName());
		
		//int scoreInt = (scores.get(gameState.getMyTeam()) != null ? scores.get(gameState.getMyTeam()) : 0);
		//leaguePointsLabel.textProperty().bind(new SimpleIntegerProperty(scoreInt).asString());
		//sidebarAccordion.setExpandedPane(sidebarAccordion.getPanes().get(2));
		leagueTeamTableColumn.setCellFactory(highlightMyTeam);

		saveGameButton.setOnAction((event) -> {
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			}
		});

		loadGameButton.setOnAction((event) -> {
			gameState = loadGame();
		});

		nextRoundButton.setOnAction((event) -> {
			if (gameState.getMatchScheme().matchDayExists(gameState.getGameRound() + 1)) {
				System.out.println(gameState.getMatchScheme().getMatchdays());
				PostMatchViewController.show(gameState);
			} else {
				PostLeagueViewController.show(gameState);
			}
		});

		// Save and Quit to Menu
		saveAndQuitMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			} else {
				TitleScreenController.show();
				System.out.println("Saved and Returned to Menu!");
			}
		});

		// Save and Quit to Desktop
		saveAndQuitDesktopMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			boolean result = saveGame(gameState);
			if (!result) {
				System.err.println("Game not saved!");
			} else {
				Platform.exit();
				System.out.println("Saved and Quit!");
			}
		});


		// Quit to Menu
		quitMenuMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			TitleScreenController.show();
			System.out.println("Returned to Menu!");
		});


		// Quit to Desktop
		quitDesktopMenuItem.setOnAction((event) -> {
			System.out.println(event.getSource());
			Platform.exit();
			System.out.println("Quit to Desktop!");
		});

		leagueScoreTableColumn.setCellValueFactory(new Callback<CellDataFeatures<Team, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Team, Integer> param) {
				Integer score = scores.get(param.getValue());
				if (score == null)
					return new SimpleIntegerProperty(0).asObject();
				return new SimpleIntegerProperty(score).asObject();
			}
		});

		leagueScoreboardTableView.sortPolicyProperty().set(new Callback<TableView<Team>, Boolean>() {
			@Override
			public Boolean call(TableView<Team> param) {
				Comparator<Team> comparator = new Comparator<Team>() {
					@Override
					public int compare(Team t1, Team t2) {
						int score1 = (scores.get(t1) != null ? scores.get(t1) : 0);
						int score2 = (scores.get(t2) != null ? scores.get(t2) : 0);

						if (score1 > score2) {
							return -1;
						} else if (score1 < score2) {
							return 1;
						} else if (t1.getName().equals(gameState.getMyTeamName())) {
							return -1;
						} else if (t2.getName().equals(gameState.getMyTeamName())) {
							return 1;
						} else {
							return 0;
						}
					}
				};
				FXCollections.sort(leagueScoreboardTableView.getItems(), comparator);
				return true;
			}
		});
	}

	public static void show(GameState gs) {
		gameState = gs;

		FXMLLoader l = new FXMLLoader();
		l.setLocation(FootballManager.class.getResource(rootViewFileName));
		try {
			BorderPane rootLayout = (BorderPane) l.load();
			FootballManager.getStage().setScene(new Scene(rootLayout));
			FootballManager.getStage().show();

			TeamOverviewController.show(rootLayout, gameState);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean saveGame(GameState gs) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save game");
		configureFileChooser(chooser);

		// file type filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML", "*.xml");
		chooser.getExtensionFilters().add(extFilter);

		File selectedFile = FootballManager.getSaveFile(chooser);
		if (selectedFile != null) {
			System.out.println("Save file: " + selectedFile.getAbsolutePath());
			return GameState.save(gs, selectedFile);
		} else {
			System.err.println("No file selected!");
			return false;
		}

	}

	public static GameState loadGame() {
		FileChooser chooser = new FileChooser();
		GameState gs = new GameState();
		chooser.setTitle("Load game");
		configureFileChooser(chooser);

		File selectedFile = FootballManager.getOpenFile(chooser);
		if (selectedFile != null) {
			System.out.println("Load file: " + selectedFile.getAbsolutePath());

			gs = GameState.load(selectedFile);
			RootViewController.show(gs);
		} else {
			System.err.println("No file selected!");
		}

		return gs;
	}

	private static void configureFileChooser(FileChooser fc) {
		// Standard dir is working dir of application
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML", "*.xml"));
	}

	@Override
	public void update(Observable o, Object arg) {

		// TODO check if last game has been played, if so, goto PostLeagueView
		if(gameState.getGameRound() <= gameState.getLeague().getMaxGamesToPlay()) {
			PostLeagueViewController.show(gameState);
		}
	}


}
