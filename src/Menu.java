import javax.swing.*;
import java.awt.*;

public class Menu{
	HighscoreManager highscoreManager = new HighscoreManager();
	private Image StartButton;
	private Image AboutButton;
	private Image ExitButton;
	private Image Level1;
	private Image Level2;
	private Image Level3;
	private Image GameOver;
	private Image PlayAgain;
	private Image MainMenu;
	private Image Back;
	private Image about;
	
	Rectangle rec = new Rectangle(50, 1140, 160, 60);

	private void loadimage(){
		ImageIcon sb = new ImageIcon("resources/Start.png");
		StartButton = sb.getImage();
		ImageIcon ab = new ImageIcon("resources/About.png");
		AboutButton = ab.getImage();
		ImageIcon eb = new ImageIcon("resources/Exit.png");
		ExitButton = eb.getImage();
		ImageIcon lv1 = new ImageIcon("resources/Level 1.png");
		Level1 = lv1.getImage();
		ImageIcon lv2 = new ImageIcon("resources/Level 2.png");
		Level2 = lv2.getImage();
		ImageIcon lv3 = new ImageIcon("resources/Level 3.png");
		Level3 = lv3.getImage();
		ImageIcon go = new ImageIcon("resources/GameOver.png");
		GameOver = go.getImage();
		ImageIcon pa = new ImageIcon("resources/PlayAgain.png");
		PlayAgain = pa.getImage();
		ImageIcon mm = new ImageIcon("resources/MainMenu.png");
		MainMenu = mm.getImage();
		ImageIcon ia = new ImageIcon("resources/InAbout.png");
		about = ia.getImage();


		ImageIcon bck = new ImageIcon("resources/Back.png");
		Back = bck.getImage();
		
	}
	public void mainMenu(Graphics g) {
		loadimage();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(StartButton,530,200,null);
		g2d.drawImage(AboutButton,530,350,null);
		g2d.drawImage(ExitButton,530,500,null);
		
	}
	
	public void levelSelection(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Level1,530,200,null);
		g2d.drawImage(Level2,530,350,null);
		g2d.drawImage(Level3,530,500,null);
		
		g2d.drawImage(Back,50,590,null);

	}
	
	public void about(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(about,0,0,null);
		g2d.drawImage(Back,50,590,null);
	}
	
	public void gameOver(Graphics g, int appleEaten, int level) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(GameOver,0,0,null);
		
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
		
		g.drawImage(PlayAgain,325,500,null);
		g.drawImage(MainMenu,725,500,null);
	}
}
