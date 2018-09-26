package ausc.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ausc.data.DataSource;
import ausc.data.InicialData;
import ausc.db.QueriesDB;
import ausc.serial.ComSerialPort;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class Frame extends JFrame{
	protected JPanel panel;
	private JLabel lable;
	private Font font;
	protected ComSerialPort comP;
	private QueriesDB qdb;
	private InicialData id;
	private JButton btnNewButton;
	private JLabel infLbl;
	
	
	Frame (){
		super("AutoScale");
		
		btnNewButton = new JButton("Random Weight");
		btnNewButton.setBounds(0, 500, 150, 20);
		getContentPane().add(btnNewButton);
		
		infLbl = new JLabel("");
		infLbl.setBounds(155, 500, 450, 20);
		getContentPane().add(infLbl);
		
		
		comP = new ComSerialPort();
	
		panel = new JPanel();
		try {
			qdb = new QueriesDB();
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\7segment.ttf"));
		} catch (IOException e) {
			System.err.println("Шрифт 7segment.ttf в каталоге fonts не найден!");
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createGUI() {

		id = new InicialData();
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
					
					System.out.println(DataSource.getWeightString());
					
					
					try {
							if (DataSource.getWeightInt() >= Integer.parseInt(id.getMinWeight())){
								qdb.incertWeight(DataSource.getWeightString());
								infLbl.setText("Вес добавлен в БД!");
							} else {
								System.err.println("Вес меньше порогового значения!");
								infLbl.setText("Вес меньше порогового значения!");
							}
					} catch (SQLException e) {
						new Thread(new Runnable() {
							public void run() {
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null, "Ошибка записи веса в ausc.db" , "Ошибка!", JOptionPane.ERROR_MESSAGE ); 
							}
						}).start();
					} catch (NumberFormatException e) {
						System.err.println("Ошибка !! Нулевое значение  " + e.getLocalizedMessage());
					}
				}
			}
		
	});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = (int) (Math.random()*10000);
				DataSource.setWeight(a);
			}
		});

	}
}