//Import all the tools we will use.
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author pbibus
 */

public class StartBoard extends JPanel {
	private JFEditGame board;
	private static double widthRatio = .25; // Ratio for the width of the the start menu to the screen, Bigger number =
											// smaller start menu

	private static double heightRatio = .40; // Ratio for the height of the start menu screen, Bigger number = smaller
												// start menu
	private static double heightToFontSizeRatio = 36; // Ratio used to obtain the Font size to use based on the height
														// of the start menu

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double screenHeight = screenSize.getHeight();
	private double screenWidth = screenSize.getWidth();

	/**
	 * Listener that is run when the 'start' btn is pressed
	 * 
	 * @author pbibus
	 *
	 */
	class startGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			board = new JFEditGame();
			JFStartGame game = new JFStartGame(board);
			game.run();
		}
	}
	/**
	 * Listener that is run when the edit game button is pressed
	 * Opens a JFrame containing editing tools
	 * @author pbibus
	 *
	 */
	class editGameListener implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) {
			board = new JFEditGame();
			board.run();
		}
	}
	/**
	 *Listener that is run when the view game button is pressed
	 *Final view of the game
	 * @author pbibus
	 *
	 */
	class viewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Listener that waits for the exit btn
	 * Closes out of the application
	 * @author pbibus
	 *
	 */
	class exitGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Sets up the screen components of the JPanel: the bounds, the background
	 * color, and the four option btn's
	 */
	public void setUpComponents() { // Setups the start menu
		JFrame window = new JFrame("DUNGEONS AND DRAGONS START MENU"); // Title
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // What to do on close

		int numButtons = 4; // Number of buttons that will be on the screen, used later for the layout

		window.setBounds((int) (screenWidth / 2 - screenWidth * widthRatio / 2),
				(int) (screenHeight / 2 - screenHeight * heightRatio / 2), (int) (screenWidth * widthRatio),
				(int) (screenHeight * heightRatio)); // Uses math to set the size of the screen

		// Creates the output panel in the form of a grid layout
		JPanel outP = new JPanel(new GridLayout(numButtons, 1, 4, 4));
		// Sets up the start game button
		JButton startGameBtn = new JButton("Start Game");
		startGameBtn.setFont(new Font("Arial", Font.PLAIN, (int) (screenHeight / heightToFontSizeRatio)));
		startGameBtn.addActionListener(new startGameListener());
		// Sets up the Edit game button
		JButton editGameBtn = new JButton("Edit Game");
		editGameBtn.setFont(new Font("Arial", Font.PLAIN, (int) (screenHeight / heightToFontSizeRatio)));
		editGameBtn.addActionListener(new editGameListener());
		// Sets up the view game button
		JButton viewGameBtn = new JButton("View Game");
		viewGameBtn.setFont(new Font("Arial", Font.PLAIN, (int) (screenHeight / heightToFontSizeRatio)));
		viewGameBtn.addActionListener(new viewGameListener());
		// Sets up the exit game button
		JButton exitGameBtn = new JButton("Quit");
		exitGameBtn.setFont(new Font("Arial", Font.PLAIN, (int) (screenHeight / heightToFontSizeRatio)));
		exitGameBtn.addActionListener(new exitGameListener());

		// Creates and sets the background color
		Color myColor;
		myColor = new Color(30, 30, 30);
		outP.setBackground(myColor);

		// Adds the buttons to the output screen
		outP.add(startGameBtn);
		outP.add(editGameBtn);
		outP.add(viewGameBtn);
		outP.add(exitGameBtn);

		// Creates the container and adds the output panel
		Container c = window.getContentPane();
		c.add(outP);

		window.setVisible(true);
	}

	public static void main(String[] args) {
		// Creates and starts the panel
		StartBoard panel = new StartBoard();
		panel.setUpComponents();
	}

	/**
	 * Private function used to scale images. NOT MADE BY US, MADE BY......//TODO
	 * 
	 * @param srcImg
	 *            The image that you want to scale. Must be an object Image
	 * @param w
	 *            The new width of type int
	 * @param h
	 *            The new height of type int
	 * @return New Image object that has been scaled to the new Width and Height
	 */
	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

}