
import dataModel.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 * 
 * This class is for importing the Player data from CSV.
 */
public class XMLImport {

	public static void main(String[] args) {
		File dir = new File("CSV/");
		readCSV(dir);
	}
	
	private static void readCSV(File dir) {
		BufferedReader br = null;
		String line = "";
		String delimiter = ";";
		League eredivisie = new League("Eredivisie");
		XMLPlayer xmlPlayer = new XMLPlayer(new File("GameData/Eredivisie.xml"));
		
		int id = 0;
		
		for (File csvFile : dir.listFiles()) {
			try {
				br = new BufferedReader(new FileReader(csvFile));
				
				br.readLine();
				while((line = br.readLine()) != null) {
					
					String[] player = line.split(delimiter);
					
					// Create league and players
					if(eredivisie.getTeam(player[9]) == null) {
						eredivisie.addTeam(new Team(player[9]));
					}
					
					Player p = new Player(
							id++,
							player[0],
							player[1],
							player[9],
							"Nationality",
							new Date(),
							player[2],
							Integer.parseInt(player[3]),
							Integer.parseInt(player[4]),
							Integer.parseInt(player[5]),
							Integer.parseInt(player[6]),
							Integer.parseInt(player[7]),
							Integer.parseInt(player[8]));
					
					eredivisie.getTeam(player[9]).addPlayer(p);
//					System.out.println(p);
				}
				System.out.println(csvFile.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		xmlPlayer.writeToFile(eredivisie);
	}
	

}
