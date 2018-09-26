package ausc.serial;

import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import ausc.data.DataSource;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class ComSerialPort {

	protected SerialPort serialPort;
	protected String dataString;
	protected String dataHexString;

	private final String strRegex = "(?<=\\x02)([\\+|-])(\\d{6})(\\d)(\\w+|\\d+)(?=\\x03)";
	private Pattern p = Pattern.compile(strRegex);
	private Matcher m;

	
	
	public ComSerialPort() {
		
		try {
			serialPort = new SerialPort("COM5"); //inicialization COM1 ... COM3 .. 
			serialPort.openPort();
			serialPort.setParams(
					SerialPort.BAUDRATE_1200, 
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			// serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
			serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
		
		} catch (SerialPortException ex) {
			new Thread(new Runnable() {
				public void run() {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Отсутствует либо занят другим процессом параллельный порт COM5 " , "Ошибка!", JOptionPane.ERROR_MESSAGE ); 
				}
			}).start();
		}
	}

	protected boolean findWeight(String inputText) {
		m = p.matcher(inputText);
		if (m.find()) {
			return true;
		}
		return false;
	}
	
	protected String getWeight() {
		// System.out.println(m.group(0));
		if (m.group(0) == "+") {
			return (m.group(1));
		} else {
			return (m.group(1) + m.group(2));
		}
	}


	
	public class PortReader implements SerialPortEventListener {
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() & event.getEventValue() > 0) {
				try {
					
		if(findWeight(new String(serialPort.readBytes(20,150),("UTF-8"))))
			DataSource.setWeight(getWeight());			
					
				} catch (SerialPortException | UnsupportedEncodingException | SerialPortTimeoutException e) {
				
					e.printStackTrace();
				}
			
			}
		}
	}
}
