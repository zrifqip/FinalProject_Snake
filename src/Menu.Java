import java.awt.*;

public class Menu{
	public Rectangle playButton = new Rectangle (530, 200, 200, 75);
	public Rectangle settingButton = new Rectangle (530, 350, 200, 75);
	public Rectangle quitButton = new Rectangle (530, 500, 200, 75);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 70);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SNAKE GAME", 400, 120);
		
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g2d.drawString("Play", playButton.x + 50, playButton.y + 54);
		g2d.draw(playButton);
		g2d.drawString("Setting", settingButton.x + 15, settingButton.y + 54);
		g2d.draw(settingButton);
		g2d.drawString("Quit", quitButton.x + 50, quitButton.y + 54);
		g2d.draw(quitButton);		
	}
}