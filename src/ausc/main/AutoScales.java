package ausc.main;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ausc.main.Frame;

public class AutoScales {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					Frame f = new Frame();
					f.createGUI();
					f.setVisible(true);
					f.listeners();
				} catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

	

	}

}
