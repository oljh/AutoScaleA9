package ausc.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InicialData {

	private FileInputStream fis;
	private Properties property = new Properties();
	private String minWeight;
	private String destinationDB;

	public InicialData() {
		try {
			fis = new FileInputStream("res\\config.properties");
			property.load(fis);
			this.minWeight = property.getProperty("minWeight");
			this.destinationDB = property.getProperty("destinationDB");


		} catch (IOException e) {
			System.err.println(" Error! config file not find!\n ОШИБКА: Файл свойств отсуствует!");
		}
	}


	public String getMinWeight() {
		return minWeight;
	}


	public void setMinWeight(String minWeight) {
		this.minWeight = minWeight;
	}


	public String getDestinationDB() {
		return destinationDB;
	}


	public void setDestinationDB(String destinationDB) {
		this.destinationDB = destinationDB;
	}


}