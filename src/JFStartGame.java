import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * JFrame that contains the running game
 * @author jrjeff, griffens
 *
 */
public class JFStartGame extends JFrame {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//Get the dimensions of the screen
	private double screenHeight = screenSize.getHeight();
	private double screenWidth = screenSize.getWidth();
	private JPanel contentPane;
	private Image background;
	private JFEditGame board;
	private ImageIcon grid;
	private double iconResizeMult = 0.03;
	
	/**
	 * runs the JFRame
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFStartGame frame = new JFStartGame(board);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *TODO Any information needed for the players and monsters
	 * @param gW
	 * The width of the grid
	 * @param gH
	 * The height of the grid
	 * @param gX
	 * The x position of the grid
	 * @param gY
	 * The y position of the grid
	 * @param i
	 * A Image object representing the selected image for the background
	 */
	public JFStartGame(JFEditGame b) {
		board = b;
		background = getScaledImage(new ImageIcon(JFStartGame.class.getResource("/dndMap1.jpg")).getImage(), (int)screenWidth, (int)screenHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int) screenWidth, (int) screenHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<Player> players = board.getPlayers();
		ArrayList<Enemy> enemies = board.getEnemies();


		grid = new ImageIcon(getScaledImage(board.grid.getImage(), (int)screenWidth, (int)screenHeight));

		
		
		JLabel gridLayer = new JLabel("");
		gridLayer.setIcon(grid);
		gridLayer.setBounds(0, 0, (int) screenWidth, (int) screenHeight);
		contentPane.add(gridLayer);

		JLabel mapLayer = new JLabel("");
		mapLayer.setBounds(0, 0, (int) screenWidth, (int) screenHeight);
		mapLayer.setBackground(Color.WHITE);
		mapLayer.setIcon(new ImageIcon(background));
		contentPane.add(mapLayer);
		
		JLabel[] characters = new JLabel[players.size() + enemies.size() + 20];
		int counter = 0;
		for(Player player : players) {
			characters[counter] = new JLabel(player.getName());
			characters[counter].setSize((int) (screenWidth*iconResizeMult), (int) (screenHeight*iconResizeMult));
			characters[counter].setIcon(new ImageIcon(player.getImage()));
			counter++;
		}
		
		for(Enemy enemy : enemies) {
			
			counter++;
		}
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}
