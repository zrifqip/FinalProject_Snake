import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class HighscoreManager {
	private int highScore;
	
	public int getHighscore(int level) throws Exception {
		FileReader fileInput = new FileReader("save/highscore.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);
		
        String data = bufferInput.readLine();
        Integer nomorData;
        Integer score;
        while(data != null){
        	StringTokenizer stringToken = new StringTokenizer(data, "_");
        	nomorData = Integer.parseInt(stringToken.nextToken());
        	score = Integer.parseInt(stringToken.nextToken());
        	
        	if(nomorData == level) {
        		highScore = score;
        	}
        		
        	data = bufferInput.readLine();
        }
		return highScore;
	}
	
	public void setHighscore(int level, int gameScore) throws Exception {
		File database = new File("save/highscore.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);
        
        String data = bufferedInput.readLine();
        Integer nomorData;
        String[] score = new String[3];
        while (data != null){
        	StringTokenizer stringToken = new StringTokenizer(data, "_");
        	nomorData = Integer.parseInt(stringToken.nextToken());
        	score[nomorData-1] = stringToken.nextToken();
        	
        	if(nomorData == level) {
        		score[nomorData-1] = String.valueOf(gameScore);
        	}
        	
        	data = bufferedInput.readLine();
        }
        
        FileWriter fileOutput = new FileWriter(database, false);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);
        for(int i=0; i<3; i++) {
        	bufferedOutput.write(String.valueOf(i+1) + "_" + score[i]);
        	bufferedOutput.newLine();
        }
        
        bufferedOutput.flush();
        
	}

}
