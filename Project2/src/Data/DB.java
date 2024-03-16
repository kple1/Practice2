package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
	
	public static String url = "jdbc:mysql://localhost:3306/?allowLoadLocalInfile=true";
	public static Connection conn;
	public static Statement st;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "1234");
			st = conn.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<byte[]> getImage() {
		List<byte[]> list = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT m_img FROM music");
			while (rs.next()) list.add(rs.getBytes("m_img"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getStringData(String wantCol, String table, String haveCol, String where) {
		String save = "";
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT " + wantCol + " FROM " + table + " WHERE " + haveCol + " = '" + where + "'");
			if (rs.next()) save = rs.getString(wantCol); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static int getIntData(String wantCol, String table, String haveCol, String where) {
		int save = 0;
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT " + wantCol + " FROM " + table + " WHERE " + haveCol + " = " + where);
			if (rs.next()) save = rs.getInt(wantCol); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getAllStringData(String wantCol, String table) {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT " + wantCol + " FROM " + table);
			while (rs.next()) save.add(rs.getString(wantCol)); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void insertUser(String u_name, String birth, String id, String pw) {
		try {
			String q = "INSERT INTO user (u_name, birth, id, pw) VALUES (?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(q);
			pstm.setString(1, u_name);
			pstm.setString(2, birth);
			pstm.setString(3, id);
			pstm.setString(4, pw);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
