package ausc.db;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueriesDB extends JoinDB {
	String[] arrayAnsw;
	Date d = null;
	private final SimpleDateFormat iso8601DateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


	public QueriesDB() throws ClassNotFoundException, SQLException {
		st = connection.createStatement();
	}
	

	public void incertWeight(String weight) throws SQLException {
		d = new Date();
		st.execute(
				"INSERT INTO weights (weights, datetime) VALUES ('"+ weight +"','" + iso8601DateTime.format(d) + "');");
		st.close();
	}
}
