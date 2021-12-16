import javax.swing.*;
import javax.swing.event.MouseInputListener;

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
	final int [] x = new int[GAME_UNITS];
	final int [] y = new int[GAME_UNITS];
	private int bodyParts;
	private int level;
	private int applesEaten;
	private int appleX;
	private int appleY;
	private char direction = 'R';
	private boolean running = false;
	private Timer timer;
	private final Random random;
	private final Menu menu;
	private Image ball;
	private Image apple;
	private Image head;
	private Image level1;
	private Image level2;
	private Image level3;
	private Sound eat;
	private Sound buttonClick;
	private Sound hit;
	private Sound gameOver;
	private Sound backsound;
	private Image background;
	private Image levelSelection;
	
	private enum STATE{
		MENU,
		ABOUT,
		LEVEL,
		GAME,
		GAMEOVER
	}
	

	private static STATE state = STATE.MENU;
	
	public GamePanel(){
		random = new Random();
		menu = new Menu();
		
		loadImages();
		loadSounds();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MouseInput());
		startGame();
		
		backsound.playLoop();
	}

	private void startGame(){
		bodyParts = 3;
		applesEaten = 0;
		newApple();
		x[0] = 1000;
		y[0] = 350;
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	private void loadImages() {

		ImageIcon bg = new ImageIcon("resources/background.png");
		background = bg.getImage();

		ImageIcon iid = new ImageIcon("resources/body.png");
		ball = iid.getImage();

		ImageIcon iia = new ImageIcon("resources/apple.png");
		apple = iia.getImage();

		ImageIcon iih = new ImageIcon("resources/head.png");
		head = iih.getImage();

		ImageIcon ii1 = new ImageIcon("resources/level1.png");
		level1 = ii1.getImage();

		ImageIcon ii2 = new ImageIcon("resources/level2.png");
		level2 = ii2.getImage();
		
		ImageIcon ii3 = new ImageIcon("resources/level3.png");
		level3 = ii3.getImage();

		ImageIcon ls = new ImageIcon("resources/LevelSelection.png");
		levelSelection = ls.getImage();
	}
	
	private void loadSounds() {
		hit = new Sound();
		hit.setFile("sound/hit.wav");
		
		eat = new Sound();
		eat.setFile("sound/eat.wav");
		
		buttonClick = new Sound();
		buttonClick.setFile("sound/buttonClick.wav");
		
		gameOver= new Sound();
		gameOver.setFile("sound/gameOver.wav");
		
		backsound = new Sound();
		backsound.setFile("sound/backsound.wav");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(state == STATE.MENU) {
			g.drawImage(background,0,0,null);
			menu.mainMenu(g);
			
		} else if (state == STATE.ABOUT) {
			menu.about(g);
		} else if(state == STATE.LEVEL) {
			g.drawImage(levelSelection,0,0,null);
			menu.levelSelection(g);
			
		} else if(state == STATE.GAME) {
			draw(g);
		} 
	}
	
	private void draw(Graphics g) {
		
		if(state == STATE.GAME) {
		if(running) {
			
			if(level == 1){
				g.drawImage(level1,0,0,null);
			} else if(level == 2) {
				g.drawImage(level2,0,0,null);
			} else if(level == 3) {
				g.drawImage(level3,0,0,null);
			}

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
			GamePanel.state = GamePanel.STATE.GAMEOVER;
			menu.gameOver(g, applesEaten, level);
			gameOver.play();
			backsound.pauseLoop();
		}
		}
		
	}
	
	private void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		if(level==2) {
			while((appleX>=45 && appleX<=1195 && appleY <= 45)
					||(appleX>=45 && appleX<=1195 && appleY >= 645)
					||(appleX<=45 && appleY>=45 && appleY <= 645)
					||(appleX>=1195 && appleY>=45 && appleY <= 645)) {
				appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
				appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
			}
		} if(level==3) {
			while((appleX>=400 && appleX<=845) && (appleY>=250 && appleY<=445)) {
				appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
				appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
			}
		}
	}

	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}

		switch (direction) {
			case 'U' -> y[0] = y[0] - UNIT_SIZE;
			case 'D' -> y[0] = y[0] + UNIT_SIZE;
			case 'L' -> x[0] = x[0] - UNIT_SIZE;
			case 'R' -> x[0] = x[0] + UNIT_SIZE;
		}
		
	}
	
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
			eat.play();
		}
	}
	
	private void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
				break;
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
		
		//check collission for level 2
		if(level == 2) {
			if((x[0]>=45 && x[0]<=1195 && y[0] <= 45)
					||(x[0]>=45 && x[0]<=1195 && y[0] >= 645)
					||(x[0]<=45 && y[0]>=45 && y[0] <= 645)
					||(x[0]>=1195 && y[0]>=45 && y[0] <= 645))
				running = false;
		} else if (level == 3)
			if((x[0]>=400 && x[0]<=845) && (y[0]>=250 && y[0]<=445)) {
				running = false;
			}
				

		if(!running) {
			timer.stop();
			hit.play();
		}
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
	
	private class MyKeyAdapter extends KeyAdapter{
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
					buttonClick.play();
					GamePanel.state = GamePanel.STATE.LEVEL;
					repaint();
				}
				// About button
				if(my >= 350 && my <= 425) {
					buttonClick.play();
					GamePanel.state = GamePanel.STATE.ABOUT;
					repaint();
				}
				// quit Button
				if(my >= 500 && my <= 575) {
					buttonClick.play();
					System.exit(1);
				}
			} 
			} else if(state == STATE.ABOUT) {
			if(mx >= 50 && mx <= 210) {
				if(my >= 590 && my <= 650) {
					buttonClick.play();
					GamePanel.state = GamePanel.STATE.MENU;
					repaint();
				}
			} 
			} else if(state == STATE.LEVEL) {
			if(mx >= 530 && mx <= 730) {
				// level 1
				if(my >= 200 && my <= 275) {
					buttonClick.play();
					level = 1;
					GamePanel.state = GamePanel.STATE.GAME;
					repaint();
				}
				// level 2
				if(my >= 350 && my <= 425) {
					buttonClick.play();
					level = 2;
					GamePanel.state = GamePanel.STATE.GAME;
					repaint();
				}
				// level 3
				if(my >= 500 && my <= 575) {
					buttonClick.play();
					level = 3;
					GamePanel.state = GamePanel.STATE.GAME;
					repaint();
				}
				
			} else if(mx >= 50 && mx <= 210) {
				if(my >= 590 && my <= 650) {
					buttonClick.play();
					GamePanel.state = GamePanel.STATE.MENU;
					repaint();
				}
			}
			}
			if(state == STATE.GAMEOVER) {
			if(my >=500 && my <= 575) {
				// play again
				if(mx >= 325 && mx <= 525) {
					buttonClick.play();
					backsound.playLoop();
					GamePanel.state = GamePanel.STATE.GAME;
					startGame();
				}
				// main menu
				if(mx >= 725 && mx <= 925){
					buttonClick.play();
					backsound.playLoop();
					GamePanel.state = GamePanel.STATE.MENU;
					startGame();
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
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}