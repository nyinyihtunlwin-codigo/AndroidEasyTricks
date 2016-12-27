package com.training.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.training.demo.dto.Demo;

public class MainSetupDAO {

	public static ArrayList<Demo> getDemoData(Connection conn) {
		// TODO Auto-generated method stub
		String sql = "select * from info";
		Statement statement;
		ArrayList<Demo> demoList = new ArrayList<Demo>();

		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Demo data = new Demo();
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				data.setNrc(rs.getString("nrc"));
				data.setEmail(rs.getString("email"));
				data.setPhno(rs.getString("phno"));
				demoList.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return demoList;
	}

	public static String getSaveData(Connection conn, Demo d) {
		// TODO Auto-generated method stub
		String name = d.getName();
		String email = d.getEmail();
		String nrc = d.getNrc();
		String phone = d.getPhno();
		String result = null;
		String sql = "insert into info values('" + name + "','" + email + "','" + nrc + "','" + phone + "')";
		Statement statement;
		try {
			statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i == 1) {
				result = "success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Demo getData(Connection conn, String name) {
		// TODO Auto-generated method stub
		String sql = "select * from info where name='" + name + "'";
		Statement statement;
		Demo data = new Demo();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				data.setNrc(rs.getString("nrc"));
				data.setEmail(rs.getString("email"));
				data.setPhno(rs.getString("phno"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static String getUpdateData(Connection conn, Demo d) {
		// TODO Auto-generated method stub
		String name = d.getName();
		String email = d.getEmail();
		String nrc = d.getNrc();
		String phone = d.getPhno();
		String result = null;
		int id = d.getId();
		String sql = "update info set name='" + name + "',email='" + email + "',nrc='" + nrc + "',phno='" + phone
				+ "'where id='" + id + "'";
		Statement statement;
		try {
			statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i == 1) {
				result = "success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String getDelData(Connection conn, Demo d) {
		int id = d.getId();
		String result = null;
		String sql = "delete from info where id='" + id + "'";
		Statement statement;
		try {
			statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i == 1) {
				result = "success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
