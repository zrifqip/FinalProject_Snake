import java.awt.*;

public class Menu{
	HighscoreManager highscoreManager = new HighscoreManager();
	
	public Rectangle rec1 = new Rectangle (530, 200, 200, 75);
	public Rectangle rec2 = new Rectangle (530, 350, 200, 75);
	public Rectangle rec3 = new Rectangle (530, 500, 200, 75);
	public Rectangle rec4 = new Rectangle (325, 500, 200, 75);
	public Rectangle rec5 = new Rectangle (725, 500, 200, 75);
	
	public void mainMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 70);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SNAKE GAME", 400, 120);
		
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g2d.drawString("Play", rec1.x + 50, rec1.y + 54);
		g2d.draw(rec1);
		g2d.drawString("Setting", rec2.x + 15, rec2.y + 54);
		g2d.draw(rec2);
		g2d.drawString("Quit", rec3.x + 50, rec3.y + 54);
		g2d.draw(rec3);		
	}
	
	public void levelSelection(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 60);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Select Level :", 460, 120);
		
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g2d.drawString("Level 1", rec1.x + 15, rec1.y + 54);
		g2d.draw(rec1);
		g2d.drawString("Level 2", rec2.x + 15, rec2.y + 54);
		g2d.draw(rec2);
		g2d.drawString("Level 3", rec3.x + 15, rec3.y + 54);
		g2d.draw(rec3);
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
