package com.sokoban.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInterface extends JFrame{
	
	private JPanel mainPanel;
	private Toolkit toolkit;
	private int [][] mapArray;
	private Random random;
	private LevelReader lvlReader;
	private com.sokoban.game.Level level;
	private Contents content;
	private int levelSize;
	
	private Icon boxIcon;
	private Icon wallIcon;
	private Icon charIcon;
	private Icon blankIcon;
	private Icon holeIcon;
	private Icon outsideIcon;
	
	public GameInterface() {
		
		/*
		 * set size of the game and fetch the application icon
		 * from classpath
		 */
		setResizable(false);
		setTitle("Sokoban Game");
		
		try {
			setIconImage(ImageIO.read(this.getClass().getResource("character.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[DEBUG] : Problem while setting game icon");
			e.printStackTrace();
		}
		
		lvlReader = new LevelReader();
		levelSize = lvlReader.readLevels("levels.txt");
		random = new Random();
		level = lvlReader.levels.get(random.nextInt(lvlReader.levels.size()));
		System.out.println("Desc :"+level.getDescription());
		
		setSize((level.getWidth()+1)*43 + 50, (level.getHeight()+1)*43 + 50);
		System.out.println("Width : "+(level.getWidth()*43 + 80));
		System.out.println("Height : "+ (level.getHeight()*43 + 80));
		mapArray = new int[level.getHeight()][level.getWidth()];
		
		/*
		 * set the frame to the center of the screen
		 * no magic just get the screen size dynamically 
		 * and set according to that 
		 */
		toolkit = getToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
		
		/*
		 * images of the game to load
		 * while game starting
		 */
		boxIcon = new ImageIcon(getClass().getResource("box.png"));
		wallIcon = new ImageIcon(getClass().getResource("wall.png"));
		charIcon = new ImageIcon(getClass().getResource("character.png"));
		blankIcon = new ImageIcon(getClass().getResource("blank.png"));
		holeIcon = new ImageIcon(getClass().getResource("hole.png"));
		outsideIcon = new ImageIcon(getClass().getResource("free.png"));
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setLayout(null);
		
		setupLevelMap();
		add(mainPanel);
	}
	
	public void setupLevelMap(){
		
		JLabel label;
		int x=40;
		int y=40;
		for (int i = 0; i < level.rows.size(); i++) {
			
			char [] tempRow = level.rows.get(i).toCharArray();
			System.out.println("Row character size : "+ tempRow.length );
			for (int j = 0; j < tempRow.length; j++) {
				
				content = lvlReader.convert(tempRow[j]);
				
				switch (content) {
				case WALL:
					label = new JLabel(wallIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case EMPTY:
					label = new JLabel(outsideIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case GOAL:
					label = new JLabel(holeIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case BOX:
					label = new JLabel(boxIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case PLAYER:
					label = new JLabel(charIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case PLAYERONGOAL:
					label = new JLabel(outsideIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				case BOXONGOAL:
					label = new JLabel(outsideIcon);
					label.setBounds(x, y, 43, 43);
					mainPanel.add(label);
					break;
				default:
					break;
				}
				x+=43;
			}
			x=43;
			y+=43;
			
		}
	}

	public static void main(String[] args) {
		
		GameInterface sokoban = new GameInterface();
		sokoban.setDefaultCloseOperation(EXIT_ON_CLOSE);
		sokoban.setVisible(true);
	}
}
