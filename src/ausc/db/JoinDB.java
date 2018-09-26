package ausc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class JoinDB {

	public Connection connection;
	public Statement st;
	public ResultSet rs;

	// Connecting to DataBase
	public JoinDB() throws ClassNotFoundException {
		connection = null;
		Class.forName("org.sqlite.JDBC");
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:ausc.db");

			CreateDB();

		} catch (SQLException e) {
			System.err.println(e);
		}
		System.out.println("Connection to database \"ausc.db\" successfully!");
	}

	// Creating DB
	public void CreateDB() throws ClassNotFoundException, SQLException {
		st = connection.createStatement();
		st.execute("CREATE TABLE weights (\r\n" + "    id_w        INTEGER  PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "    weights INTEGER,\r\n" + "    datetime    DATETIME\r\n" + ");");

	}
}
