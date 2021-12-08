import java.awt.*;

public class Menu{
	public Rectangle rec1 = new Rectangle (530, 200, 200, 75);
	public Rectangle rec2 = new Rectangle (530, 350, 200, 75);
	public Rectangle rec3 = new Rectangle (530, 500, 200, 75);
	
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
}