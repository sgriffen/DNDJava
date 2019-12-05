import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author jrjeff and griffens
 *
 */

public class JFEditGame extends JFrame {
	/**
	 * Amount of rows for the tile grid.
	 */
	public int gridRows;
	/**
	 * Size of the individual tiles of the game grid
	 */
	public int gridSize;
	/**
	 * 2D array of values for position points on the grid. 
	 * To be used by character and enemy classes to know where to be placed on the grid.
	 */
	public int[][] gridArray;
	/**
	 * Used by updateGrid(). 
	 * Is the position of the current line being drawn for the grid.
	 */
	private int gridCurrentScale;
	/**
	 * Used by updateGrid()
	 * Width or height of the screen
	 */
	private int screenWOrH;
	/**
	 * Array list of player objects
	 */
	private ArrayList<Player> players;
	/**
	 * Array list of enemy objects
	 */
	private ArrayList<Enemy> enemies;
	/**
	 * Image of the selected map
	 */
	private Image mapSelected;
	/**
	 * Name of the map used
	 */
	private String mapName;
	/**
	 * Used in edit game window.
	 * Check box for if the user wants a grid.
	 */
	private JPanel boxIsGrid;
	/**
	 * Variable being changed at the time.
	 */
	private JLabel lblTestVariables;
	/**
	 * The JFrame
	 */
	private JFrame frame;
	/**
	 * True if the user wants a grid
	 */
	private boolean useGrid;
	/**
	 * Gets the dimensions of the screen
	 * Only used to get the screen height and/or screen width
	 */
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Height of the screen
	 */
	private double screenHeight = screenSize.getHeight();
	/**
	 * Width of the screen
	 */
	private double screenWidth = screenSize.getWidth();
	/**
	 * Graphics object used
	 */
	private Graphics g;
	
	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFEditGame frame = new JFEditGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public JFEditGame() {
		useGrid = true;
		mapName = "(Default map)";
		gridRows = 20;
		gridArray = new int[gridRows][gridRows];
		screenWOrH = 0;
		ImageIcon m = new ImageIcon(JFEditGame.class.getResource("/dndMap1.jpg"));
		mapSelected = m.getImage();
		frame = new JFrame();
		//g = Graphics();
		
		mapSelected=getScaledImage(mapSelected,190,190);
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int) screenWidth, (int) screenHeight);
		boxIsGrid = new JPanel();
		boxIsGrid.setBackground(Color.PINK);
		boxIsGrid.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(boxIsGrid);
		boxIsGrid.setLayout(null);
		
		
		JButton btnMapSelection = new JButton("Map Selection");
		btnMapSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO file viewer
				//mapName = 
			}
		});
		btnMapSelection.setBounds(21, 11, 120, 23);
		boxIsGrid.add(btnMapSelection);
		
		JLabel lblCurrentMap = new JLabel("Game Preview");
		lblCurrentMap.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblCurrentMap.setBounds(765, 11, 228, 37);
		boxIsGrid.add(lblCurrentMap);
		
		JButton btnPlayers = new JButton("Players");
		btnPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Player selection
			}
		});
		btnPlayers.setBounds(21, 57, 120, 23);
		boxIsGrid.add(btnPlayers);
		
		JButton btnMonsters = new JButton("Monsters");
		btnMonsters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Monster selection
			}
		});
		btnMonsters.setBounds(21, 102, 120, 23);
		boxIsGrid.add(btnMonsters);
		
		JLabel lblMap = new JLabel("Map:");
		lblMap.setBounds(1589, 271, 29, 49);
		boxIsGrid.add(lblMap);
		
		JLabel lblGridSize = new JLabel("Grid Size:");
		lblGridSize.setBounds(1611, 151, 66, 14);
		boxIsGrid.add(lblGridSize);
		
		JCheckBox chckboxUseGrid = new JCheckBox("");
		chckboxUseGrid.setSelected(true);
		chckboxUseGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				useGrid = chckboxUseGrid.isSelected();
				lblTestVariables.setText("useGrid: " + useGrid);
			}
		});
		chckboxUseGrid.setBackground(Color.PINK);
		chckboxUseGrid.setBounds(1589, 147, 21, 23);
		boxIsGrid.add(chckboxUseGrid);
		
		/*JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gridNum =(int)spinner.getValue();
				lblTestVariables.setText("gridSize: " + gridNum);
				updateGrid(g);
			}
		});
		spinner.setBounds(1702, 148, 29, 20);
		boxIsGrid.add(spinner);
		*/
		
		JLabel lblSelectedMap = new JLabel(mapName);
		lblSelectedMap.setBounds(1628, 272, 117, 47);
		boxIsGrid.add(lblSelectedMap);
		
		JSlider xSlider = new JSlider();
		xSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gridRows = (int)(xSlider.getValue()/2);
				lblTestVariables.setText("Grid Size: "+ gridRows);
				updateGrid(g);
			}
		});
		xSlider.setBackground(Color.PINK);
		xSlider.setBounds(1579, 89, 258, 26);
		boxIsGrid.add(xSlider);
		
		lblTestVariables = new JLabel("Use the program to see the variable(s) being changed");
		lblTestVariables.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTestVariables.setVerticalAlignment(SwingConstants.TOP);
		lblTestVariables.setBounds(1460, 24, 434, 33);
		boxIsGrid.add(lblTestVariables);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFStartGame play = new JFStartGame(JFEditGame.this);
				play.run();
			}
		});
		btnPlay.setBounds(21, 147, 120, 23);
		boxIsGrid.add(btnPlay);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(365, 52, 1059, 811);
		lblBackground.setIcon(new ImageIcon("U:\\CprE186\\workspace\\DNDJava-master\\src\\dndMap1.jpg"));
		boxIsGrid.add(lblBackground);
		
		updateGrid(g);
	

	}
	/**
	 * Resizes the image (scrImg) to the new width (w) and new height (h)
	 * @param srcImg
	 * 		The source image to be changed
	 * @param w
	 * 		The new width of the image
	 * @param h
	 * 		The new height of the image
	 * @return
	 * 		The new scaled image
	 */
	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;

	}
	/**
	 * Resize the position array and re-draws the grid
	 * @param g
	 * 		Graphics object
	 */
	private void updateGrid (Graphics g) {
		
		//The width and height of each tile on the grid
		int scale = (int)screenHeight / gridRows;
		int screenW = (int) screenWidth;
		int screenH = (int) screenHeight;
		final LinesComponent comp = new LinesComponent();
		//int screenRatio = screenW / screenH;
		
		//Create line objects in the horizontal direction
		for (gridCurrentScale = scale, screenWOrH = screenW; gridCurrentScale <= screenHeight; gridCurrentScale += scale) {
		
			comp.addLine(0, scale, screenWOrH, scale, Color.BLACK);
		}
		
		//Create line objects in the vertical direction
		for (gridCurrentScale = scale, screenWOrH = screenH; gridCurrentScale <= screenHeight; gridCurrentScale += scale) {
			
			comp.addLine(0, scale, screenWOrH, scale, Color.BLACK);
		}
		//paintComponents(g);
		
		gridArray = new int[gridRows][gridRows];
		
	}
	/**
	 * It represents individual line objects that can be drawn to the screen
	 * @author jassuncao
	 * 
	 */
	public class LinesComponent extends JComponent{

		private class Line{
		    final int x1; 
		    final int y1;
		    final int x2;
		    final int y2;   
		    final Color color;

		    public Line(int x1, int y1, int x2, int y2, Color color) {
		        this.x1 = x1;
		        this.y1 = y1;
		        this.x2 = x2;
		        this.y2 = y2;
		        this.color = color;
		    }               
		}

		private final LinkedList<Line> lines = new LinkedList<Line>();

		public void addLine(int x1, int x2, int x3, int x4) {
		    addLine(x1, x2, x3, x4, Color.black);
		}

		public void addLine(int x1, int x2, int x3, int x4, Color color) {
		    lines.add(new Line(x1,x2,x3,x4, color));        
		    repaint();
		}

		public void clearLines() {
		    lines.clear();
		    repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    for (Line line : lines) {
		        g.setColor(line.color);
		        g.drawLine(line.x1, line.y1, line.x2, line.y2);
		    }
		}
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
