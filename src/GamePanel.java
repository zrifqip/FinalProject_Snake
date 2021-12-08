import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 1250;
	static final int SCREEN_HEIGHT = 700;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 175;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	private Timer timer;
	private Image ball;
	private Image apple;
	private Image head;
	int bodyParts = 3;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Random random;
	private Menu menu;
	
	public static enum STATE{
		MENU,
		GAME,
		LEVEL
	}
	
	public static STATE state = STATE.MENU;
	
	public GamePanel(){
		random = new Random();
		menu = new Menu();
		loadImages();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MouseInput());
		startGame();
		
	}
	
	public void startGame(){
		newApple();
		x[0] = 600;
		y[0] = 350;
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	private void loadImages() {

		ImageIcon iid = new ImageIcon("src/resources/body.png");
		ball = iid.getImage();

		ImageIcon iia = new ImageIcon("src/resources/apple.png");
		apple = iia.getImage();

		ImageIcon iih = new ImageIcon("src/resources/head.png");
		head = iih.getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(state == STATE.MENU) {
			menu.mainMenu(g);
			
		} else if(state == STATE.LEVEL) {
			menu.levelSelection(g);
			
		} else if(state == STATE.GAME) {
			draw(g);
		}
	}
	public void draw(Graphics g) {
		
		if(running) {
			/*
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/


			g.drawImage(apple, appleX, appleY, this);
			for (int z = 0; z < bodyParts; z++) {
				if (z == 0) {
					g.drawImage(head, x[z], y[z], this);
				} else {
					g.drawImage(ball, x[z], y[z], this);
				}
			}
			Toolkit.getDefaultToolkit().sync();

			g.setColor(Color.red);
			g.setFont( new Font("Arial",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		} else {
			gameOver(g);
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}

	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}

		//check if head touches left border
		if(x[0] < 0) {
			for(int i = bodyParts;i>=0;i--)
				y[i + 1] = y[i];
			for(int i = bodyParts;i>=0;i--){
				if(i == 0)
					x[i] = x[i] - bodyParts;
				else
					x[i] = x[i-1];
				if(x[i] < 0)
					x[i] = SCREEN_WIDTH;
			}
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			for(int i = bodyParts;i>=0;i--)
				y[i + 1] = y[i];
			for(int i = bodyParts;i>=0;i--){
				if(i == 0)
					x[i] = x[i] + bodyParts;
				else
					x[i] = x[i-1];
				if(x[i] > SCREEN_WIDTH)
					x[i] = 0;
			}
		}
		//check if head touches top border
		if(y[0] < 0) {
			for(int i = bodyParts;i>=0;i--)
				x[i + 1] = x[i];
			for(int i = bodyParts;i>=0;i--){
				if(i == 0)
					y[i] = y[i] - bodyParts;
				else
					y[i] = y[i-1];
				if(y[i] < 0)
					y[i] = SCREEN_HEIGHT;
			}
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			for(int i = bodyParts;i>=0;i--)
				x[i + 1] = x[i];
			for(int i = bodyParts;i>=0;i--){
				if(i == 0)
					y[i] = y[i] + bodyParts;
				else
					y[i] = y[i-1];
				if(y[i] > SCREEN_HEIGHT)
					y[i] = 0;
			}
		}

		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont( new Font("Arial",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		g.setFont(new Font("Arial",Font.BOLD,40));
		g.drawString("               " + "Press space to back to main menu", (SCREEN_WIDTH - metrics2.stringWidth(
															"Press space to back to main menu")
																)
				                    					, 		SCREEN_HEIGHT/(3));
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(state == STATE.GAME) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
			}
		}
	}
	
	private class MouseInput implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(state == STATE.MENU) {
			if(mx >= 530 && mx <= 730) {
				// play Button
				if(my >= 200 && my <= 275) {
					GamePanel.state = GamePanel.STATE.LEVEL;
				}
				// quit Button
				if(my >= 500 && my <= 575) {
					System.exit(1);
				}
			}
			} else if(state == STATE.LEVEL) {
			if(mx >= 530 && mx <= 730) {
				// play Button
				if(my >= 200 && my <= 275) {
					GamePanel.state = GamePanel.STATE.GAME;
				}
				// quit Button
				if(my >= 500 && my <= 575) {
					System.exit(1);
				}
			}
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}