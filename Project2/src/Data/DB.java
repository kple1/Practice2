package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.ImageIcon;

import Form.Login;
import Utils.MainPlugin;

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

	public static void main(String[] args) {
		
	}

	public static void insertPlayList(String u_no, String m_no) {
		String q = "INSERT INTO playlist (u_no, m_no) VALUES (?, ?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(q);
			pstm.setString(1, u_no);
			pstm.setString(2, m_no);
			pstm.executeUpdate();
		} catch (SQLException e) {
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
	
	public static List<byte[]> getReverseImage() {
		List<byte[]> list = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT m_img FROM music ORDER BY m_img DESC");
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
	
	public static List<Integer> getAllIntData(String wantCol, String table) {
		List<Integer> save = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT " + wantCol + " FROM " + table);
			while (rs.next()) save.add(rs.getInt(wantCol)); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void updateUser(String id, String pw, String u_name, String birth) {
		String q = "UPDATE oldpopsong.user SET id = ?, pw = ?, u_name = ?, birth = ? WHERE id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(q);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, u_name);
			pstm.setString(4, birth);
			pstm.setString(5, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getMnayData(String getCol, String table, String haveCol, String where) {
		List<String> save = new ArrayList<>();
		try {
			ResultSet rs = st.executeQuery("SELECT " + getCol + " FROM " + table + " WHERE " + haveCol + "='" + where + "'");
			while (rs.next()) save.add(rs.getString(getCol));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static int getTwoWhereData(String getCol, String table, String haveCol1, String where1, String haveCol2, String where2) {
		int save = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT " + getCol + " FROM " + table + " WHERE " + haveCol1 + "='" + where1 + "' AND " + haveCol2 + "='" + where2 + "'");
			while (rs.next()) save = rs.getInt(getCol);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}

	public static List<String> getCountData(int max, int min) {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("select COUNT(p.m_no) as count from playlist p "
					+ "join listenlist l on p.p_no = l.p_no and l.play_ox = 1 "
					+ "join user u on p.u_no = u.u_no and ( u.birth > DATE_SUB(CURDATE(), INTERVAL " + max + " YEAR) AND u.birth < DATE_SUB(CURDATE(), INTERVAL " + min + " YEAR) ) "
					+ "group by p.m_no "
					+ "order by count DESC "
					+ "LIMIT 5");
			while (rs.next()) save.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getCountData70Over(int max) {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("select COUNT(p.m_no) as count from playlist p "
					+ "join listenlist l on p.p_no = l.p_no and l.play_ox = 1 "
					+ "join user u on p.u_no = u.u_no and ( u.birth > DATE_SUB(CURDATE(), INTERVAL " + max + " YEAR) ) "
					+ "group by p.m_no "
					+ "order by count DESC "
					+ "LIMIT 5");
			while (rs.next()) save.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getMusicNo(String u_no) {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE oldpopsong");
			ResultSet rs = st.executeQuery("SELECT p.m_no FROM playlist p "
					+ "join listenlist l on l.p_no = p.p_no and l.play_ox = 1 "
					+ "join user u on u.u_no = p.u_no and u.u_no = " + u_no
					+ "group by p.m_no");
			while(rs.next()) save.add(rs.getString(1));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void userInfoInsert(String u_id, String u_pw, String birth, String u_name) {
		String q = "INSERT INTO oldpopsong.userInfo (u_id, u_pw, birth, u_name) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(q);
			pstm.setString(1, u_id);
			pstm.setString(2, u_pw);
			pstm.setString(3, birth);
			pstm.setString(4, u_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public static void updateUserInfo(String u_name, String birth, String id, String pw) {
		try {
			String q = "UPDATE olpopsong.user SET u_nmae = ?, birth = ?, u_id = ?, u_pw = ?";
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
