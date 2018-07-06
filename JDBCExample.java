
import java.sql.*;

import com.mysql.cj.util.TestUtils;

public class JDBCExample {

	public static void main(String[] args) {
		try {
			System.out.println("Loading JDBC driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC driver successfully loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String url = "jdbc:mysql://localhost:3306/employeeschema?serverTimezone=UTC";
		String username = "root";
		String password = "237106PRin";
		String query = "SELECT * FROM employeeschema.department;";
//		String query = "INSERT into employeeschema.products(name) values(\"Tut ball\"),(\"Put ball\");";
		Connection connection = null;
		Statement stmt = null;
		try {
			System.out.println("Connecting to the MySQL database...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("MySQL Database connected!");
			stmt = connection.createStatement();
//			stmt.executeUpdate(query);
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print("   ");
				System.out.print(rs.getString(2));
				System.out.print("   ");
				System.out.println(rs.getString(3));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			System.out.println("Closing the connection.");
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

}
