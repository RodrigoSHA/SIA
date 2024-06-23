import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class Conexion {
	public static void conexion() {
		String clave = "0000";
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/DBRental";
			Connection cn = DriverManager.getConnection(url,"postgres",clave);
			Statement st = cn.createStatement();
			String sql = "SELECT * FROM actor";
			ResultSet rs = st.executeQuery(sql);
			listar(rs);
		} catch(ClassNotFoundException ex) {
			System.out.println("Error en el driver");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void listar(ResultSet rs) throws SQLException {
		System.out.println("");
		ResultSetMetaData meta = rs.getMetaData();
		int n = meta.getColumnCount();
		for (int i=1; i<=n; i++) {
			System.out.println(meta.getColumnName(i)+"\t");
		}
		while (rs.next()) {
			System.out.println("ID actor: "+ rs.getInt(1)+"\t");
			System.out.println("Primer nombre: "+ rs.getString(2)+"\t");
			
		}
	};
	public static void main(String[] args) {
		conexion();
	}

}
