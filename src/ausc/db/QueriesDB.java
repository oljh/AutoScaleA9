package ausc.db;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueriesDB extends JoinDB {
	String[] arrayAnsw;
	Date d = null;
	private final SimpleDateFormat dMy = new SimpleDateFormat("dd.MM.yyyy");
	private final SimpleDateFormat iso8601DateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


	public QueriesDB() throws ClassNotFoundException, SQLException {
		st = connection.createStatement();
	}

	public void incertWeight(String weight) throws SQLException {
		d = new Date();
		st.execute(
				"INSERT INTO weights (weights, datetime) VALUES ("+ weight +"," + iso8601DateTime.format(d) + ");");
		closeRsSt();
	}



	// convert current List to Array
	String[] getCurrentArray(List<String> list) {
		if (list.size() != 0) {
			arrayAnsw = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				arrayAnsw[i] = list.get(i);
			}
		}
		list.clear();
		return arrayAnsw;
	}

	void closeRsSt() {
		try {
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
