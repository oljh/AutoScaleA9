package ausc.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ausc.data.DataSource;
import ausc.data.InicialData;
import ausc.db.QueriesDB;
import ausc.serial.COMSerialPort;


@SuppressWarnings("serial")
public class Frame extends JFrame{
	private JPanel panel;
	private JLabel lable;
	private Font font;
	private COMSerialPort comP;
	private QueriesDB qdb;
	private InicialData id;
	
	
	Frame (){
		super("AutoScale");
		comP = new COMSerialPort();
	
		panel = new JPanel();
		try {
			qdb = new QueriesDB();
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\7segment.ttf"));
		} catch (IOException e) {
			System.err.println("Ўрифт 7segment.ttf в каталоге fonts не найден!");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createGUI() {

		
		lable = new JLabel("8888888");
		lable.setFont(font.deriveFont(146.0f));
		lable.setHorizontalAlignment(SwingConstants.CENTER);
		
		getContentPane().add(lable);
		setIconImage(new ImageIcon("images\\logo.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 200));
		pack();
		//setResizable(false);
		setLocationRelativeTo(null);
		}
	
	public void listeners() {
		DataSource.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "weight") {
					lable.setText(DataSource.getWeightString());
					try {
						if (Integer.valueOf(DataSource.getWeightString()) >= Integer.valueOf(id.getMinWeight()))
						qdb.incertWeight(DataSource.getWeightString());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
	});
	
	
}
}