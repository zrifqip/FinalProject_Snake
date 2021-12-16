import javax.swing.*;
import java.awt.*;

public class Menu{
	private HighscoreManager highscoreManager;
	private Image startButton;
	private Image aboutButton;
	private Image exitButton;
	private Image level1;
	private Image level2;
	private Image level3;
	private Image gameOver;
	private Image playAgain;
	private Image mainMenu;
	private Image back;
	private Image about;
	
	public Menu() {
		highscoreManager = new HighscoreManager();
	}

	private void loadimage(){
		ImageIcon sb = new ImageIcon("resources/Start.png");
		startButton = sb.getImage();
		ImageIcon ab = new ImageIcon("resources/About.png");
		aboutButton = ab.getImage();
		ImageIcon eb = new ImageIcon("resources/Exit.png");
		exitButton = eb.getImage();
		ImageIcon lv1 = new ImageIcon("resources/Level 1.png");
		level1 = lv1.getImage();
		ImageIcon lv2 = new ImageIcon("resources/Level 2.png");
		level2 = lv2.getImage();
		ImageIcon lv3 = new ImageIcon("resources/Level 3.png");
		level3 = lv3.getImage();
		ImageIcon go = new ImageIcon("resources/GameOver.png");
		gameOver = go.getImage();
		ImageIcon pa = new ImageIcon("resources/PlayAgain.png");
		playAgain = pa.getImage();
		ImageIcon mm = new ImageIcon("resources/MainMenu.png");
		mainMenu = mm.getImage();
		ImageIcon ia = new ImageIcon("resources/InAbout.png");
		about = ia.getImage();
		ImageIcon bck = new ImageIcon("resources/Back.png");
		back = bck.getImage();
		
	}
	public void mainMenu(Graphics g) {
		loadimage();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(startButton,530,200,null);
		g2d.drawImage(aboutButton,530,350,null);
		g2d.drawImage(exitButton,530,500,null);
		
	}
	
	public void levelSelection(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(level1,530,200,null);
		g2d.drawImage(level2,530,350,null);
		g2d.drawImage(level3,530,500,null);
		
		g2d.drawImage(back,50,590,null);

	}
	
	public void about(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(about,0,0,null);
		g2d.drawImage(back,50,590,null);
	}
	
	public void gameOver(Graphics g, int appleEaten, int level) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(gameOver,0,0,null);
		
		Font fnt0 = new Font("Ink Free", Font.BOLD, 70);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 450, 115);
		
		Font fnt1 = new Font("Ink Free", Font.BOLD, 35);
		g.setColor(Color.BLACK);
		g.setFont(fnt1);
		g2d.drawString("Your score is : " + appleEaten, 500, 270);
		
		try {
			Font fnt2 = new Font("Ink Free", Font.BOLD, 30);
			g.setFont(fnt2);
			
			g2d.drawString("Latest highscore is : " + highscoreManager.getHighscore(level), 460, 350);
			
			if(appleEaten > highscoreManager.getHighscore(level)) {
				highscoreManager.setHighscore(level, appleEaten);
				g.setColor(Color.GREEN);
				g2d.drawString("You've set a new highscore", 435, 400);
			} else {
				g.setColor(Color.RED);
				g2d.drawString("Try Again", 550, 400);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		g.drawImage(playAgain,325,500,null);
		g.drawImage(mainMenu,725,500,null);
	}
}
