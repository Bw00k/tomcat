package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	String url = "jdbc:oracle:thin:@192.168.0.64:1521:xe";
	String user = "hr";
	String pass = "hr";

	public Connection conn;
	public Statement stmt = null;
	public PreparedStatement psmt = null;
	public ResultSet rs = null;
	public String sql;

	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러 발생.");
			e.printStackTrace();
		}
	}
	
	public void disconn() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

