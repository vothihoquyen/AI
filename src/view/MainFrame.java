package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainFrame mainFrame = new MainFrame();

	public static void startActivity(JPanel panel) {
		mainFrame.getContentPane().removeAll();
		mainFrame.add(panel);
		mainFrame.setSize(980, 690);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		startActivity(new MenuGame());
	}
}
