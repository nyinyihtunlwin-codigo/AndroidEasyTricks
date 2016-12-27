package com.training.demo.frame.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ServerUtil {
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null)
				if(!conn.isClosed())
					conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn){
		try {
			if(conn!=null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
