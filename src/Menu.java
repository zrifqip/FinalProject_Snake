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

	private Rectangle rec4 = new Rectangle (325, 500, 200, 75);
	private Rectangle rec5 = new Rectangle (725, 500, 200, 75);


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
	}
	
	public void about(Graphics g) {

	}
	
	public void gameOver(Graphics g, int appleEaten, int level) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Ink Free", Font.BOLD, 75);
		g.setFont(fnt0);
		g.setColor(Color.RED);
		g.drawString("Game Over", 430, 120);
		
		Font fnt1 = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt1);
		g2d.drawString("Your score is : " + appleEaten, 470, 220);
		
		try {
			g2d.drawString("Latest highscore is : " + highscoreManager.getHighscore(level), 430, 320);
			
			if(appleEaten > highscoreManager.getHighscore(level)) {
				highscoreManager.setHighscore(level, appleEaten);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		g.setColor(Color.WHITE);
		g2d.drawString("Play Again", rec4.x + 12, rec4.y + 50);
		g2d.draw(rec4);
		g2d.drawString("Main Menu", rec5.x + 10, rec5.y + 50);
		g2d.draw(rec5);
	}
}
